package com.facebook.login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.app.DialogFragment;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookActivity;
import com.facebook.FacebookException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.InternalAppEventsLogger;
import com.facebook.common.C1278R;
import com.facebook.devicerequests.internal.DeviceRequestsHelper;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.SmartLoginOption;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.login.LoginClient;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceAuthDialog extends DialogFragment {
    private static final String DEVICE_LOGIN_ENDPOINT = "device/login";
    private static final String DEVICE_LOGIN_STATUS_ENDPOINT = "device/login_status";
    private static final int LOGIN_ERROR_SUBCODE_AUTHORIZATION_DECLINED = 1349173;
    private static final int LOGIN_ERROR_SUBCODE_AUTHORIZATION_PENDING = 1349174;
    private static final int LOGIN_ERROR_SUBCODE_CODE_EXPIRED = 1349152;
    private static final int LOGIN_ERROR_SUBCODE_EXCESSIVE_POLLING = 1349172;
    private static final String REQUEST_STATE_KEY = "request_state";
    /* access modifiers changed from: private */
    public AtomicBoolean completed = new AtomicBoolean();
    private TextView confirmationCode;
    private volatile GraphRequestAsyncTask currentGraphRequestPoll;
    /* access modifiers changed from: private */
    public volatile RequestState currentRequestState;
    private DeviceAuthMethodHandler deviceAuthMethodHandler;
    /* access modifiers changed from: private */
    public Dialog dialog;
    private TextView instructions;
    /* access modifiers changed from: private */
    public boolean isBeingDestroyed = false;
    /* access modifiers changed from: private */
    public boolean isRetry = false;
    /* access modifiers changed from: private */
    public LoginClient.Request mRequest = null;
    private View progressBar;
    private volatile ScheduledFuture scheduledPoll;

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RequestState requestState;
        View view = super.onCreateView(inflater, container, savedInstanceState);
        this.deviceAuthMethodHandler = (DeviceAuthMethodHandler) ((LoginFragment) ((FacebookActivity) getActivity()).getCurrentFragment()).getLoginClient().getCurrentHandler();
        if (!(savedInstanceState == null || (requestState = (RequestState) savedInstanceState.getParcelable(REQUEST_STATE_KEY)) == null)) {
            setCurrentRequestState(requestState);
        }
        return view;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.dialog = new Dialog(getActivity(), C1278R.style.com_facebook_auth_dialog);
        this.dialog.setContentView(initializeContentView(DeviceRequestsHelper.isAvailable() && !this.isRetry));
        return this.dialog;
    }

    public void onDismiss(DialogInterface dialog2) {
        super.onDismiss(dialog2);
        if (!this.isBeingDestroyed) {
            onCancel();
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.currentRequestState != null) {
            outState.putParcelable(REQUEST_STATE_KEY, this.currentRequestState);
        }
    }

    public void onDestroy() {
        this.isBeingDestroyed = true;
        this.completed.set(true);
        super.onDestroy();
        if (this.currentGraphRequestPoll != null) {
            this.currentGraphRequestPoll.cancel(true);
        }
        if (this.scheduledPoll != null) {
            this.scheduledPoll.cancel(true);
        }
    }

    public void startLogin(LoginClient.Request request) {
        this.mRequest = request;
        Bundle parameters = new Bundle();
        parameters.putString("scope", TextUtils.join(",", request.getPermissions()));
        String redirectUriString = request.getDeviceRedirectUriString();
        if (redirectUriString != null) {
            parameters.putString(ServerProtocol.DIALOG_PARAM_REDIRECT_URI, redirectUriString);
        }
        String targetUserId = request.getDeviceAuthTargetUserId();
        if (targetUserId != null) {
            parameters.putString(DeviceRequestsHelper.DEVICE_TARGET_USER_ID, targetUserId);
        }
        parameters.putString("access_token", Validate.hasAppID() + "|" + Validate.hasClientToken());
        parameters.putString(DeviceRequestsHelper.DEVICE_INFO_PARAM, DeviceRequestsHelper.getDeviceInfo());
        new GraphRequest((AccessToken) null, DEVICE_LOGIN_ENDPOINT, parameters, HttpMethod.POST, new GraphRequest.Callback() {
            public void onCompleted(GraphResponse response) {
                if (!DeviceAuthDialog.this.isBeingDestroyed) {
                    if (response.getError() != null) {
                        DeviceAuthDialog.this.onError(response.getError().getException());
                        return;
                    }
                    JSONObject jsonObject = response.getJSONObject();
                    RequestState requestState = new RequestState();
                    try {
                        requestState.setUserCode(jsonObject.getString("user_code"));
                        requestState.setRequestCode(jsonObject.getString("code"));
                        requestState.setInterval(jsonObject.getLong("interval"));
                        DeviceAuthDialog.this.setCurrentRequestState(requestState);
                    } catch (JSONException ex) {
                        DeviceAuthDialog.this.onError(new FacebookException((Throwable) ex));
                    }
                }
            }
        }).executeAsync();
    }

    /* access modifiers changed from: private */
    public void setCurrentRequestState(RequestState currentRequestState2) {
        this.currentRequestState = currentRequestState2;
        this.confirmationCode.setText(currentRequestState2.getUserCode());
        this.instructions.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, new BitmapDrawable(getResources(), DeviceRequestsHelper.generateQRCode(currentRequestState2.getAuthorizationUri())), (Drawable) null, (Drawable) null);
        this.confirmationCode.setVisibility(0);
        this.progressBar.setVisibility(8);
        if (!this.isRetry && DeviceRequestsHelper.startAdvertisementService(currentRequestState2.getUserCode())) {
            new InternalAppEventsLogger(getContext()).logEventImplicitly(AnalyticsEvents.EVENT_SMART_LOGIN_SERVICE);
        }
        if (currentRequestState2.withinLastRefreshWindow()) {
            schedulePoll();
        } else {
            poll();
        }
    }

    /* access modifiers changed from: protected */
    public View initializeContentView(boolean isSmartLogin) {
        View view = getActivity().getLayoutInflater().inflate(getLayoutResId(isSmartLogin), (ViewGroup) null);
        this.progressBar = view.findViewById(C1278R.C1281id.progress_bar);
        this.confirmationCode = (TextView) view.findViewById(C1278R.C1281id.confirmation_code);
        ((Button) view.findViewById(C1278R.C1281id.cancel_button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DeviceAuthDialog.this.onCancel();
            }
        });
        this.instructions = (TextView) view.findViewById(C1278R.C1281id.com_facebook_device_auth_instructions);
        this.instructions.setText(Html.fromHtml(getString(C1278R.string.com_facebook_device_auth_instructions)));
        return view;
    }

    /* access modifiers changed from: protected */
    @LayoutRes
    public int getLayoutResId(boolean isSmartLogin) {
        return isSmartLogin ? C1278R.layout.com_facebook_smart_device_dialog_fragment : C1278R.layout.com_facebook_device_auth_dialog_fragment;
    }

    /* access modifiers changed from: private */
    public void poll() {
        this.currentRequestState.setLastPoll(new Date().getTime());
        this.currentGraphRequestPoll = getPollRequest().executeAsync();
    }

    /* access modifiers changed from: private */
    public void schedulePoll() {
        this.scheduledPoll = DeviceAuthMethodHandler.getBackgroundExecutor().schedule(new Runnable() {
            public void run() {
                DeviceAuthDialog.this.poll();
            }
        }, this.currentRequestState.getInterval(), TimeUnit.SECONDS);
    }

    private GraphRequest getPollRequest() {
        Bundle parameters = new Bundle();
        parameters.putString("code", this.currentRequestState.getRequestCode());
        return new GraphRequest((AccessToken) null, DEVICE_LOGIN_STATUS_ENDPOINT, parameters, HttpMethod.POST, new GraphRequest.Callback() {
            public void onCompleted(GraphResponse response) {
                if (!DeviceAuthDialog.this.completed.get()) {
                    FacebookRequestError error = response.getError();
                    if (error != null) {
                        switch (error.getSubErrorCode()) {
                            case DeviceAuthDialog.LOGIN_ERROR_SUBCODE_CODE_EXPIRED /*1349152*/:
                                if (DeviceAuthDialog.this.currentRequestState != null) {
                                    DeviceRequestsHelper.cleanUpAdvertisementService(DeviceAuthDialog.this.currentRequestState.getUserCode());
                                }
                                if (DeviceAuthDialog.this.mRequest != null) {
                                    DeviceAuthDialog.this.startLogin(DeviceAuthDialog.this.mRequest);
                                    return;
                                } else {
                                    DeviceAuthDialog.this.onCancel();
                                    return;
                                }
                            case DeviceAuthDialog.LOGIN_ERROR_SUBCODE_EXCESSIVE_POLLING /*1349172*/:
                            case DeviceAuthDialog.LOGIN_ERROR_SUBCODE_AUTHORIZATION_PENDING /*1349174*/:
                                DeviceAuthDialog.this.schedulePoll();
                                return;
                            case DeviceAuthDialog.LOGIN_ERROR_SUBCODE_AUTHORIZATION_DECLINED /*1349173*/:
                                DeviceAuthDialog.this.onCancel();
                                return;
                            default:
                                DeviceAuthDialog.this.onError(response.getError().getException());
                                return;
                        }
                    } else {
                        try {
                            JSONObject resultObject = response.getJSONObject();
                            DeviceAuthDialog.this.onSuccess(resultObject.getString("access_token"), Long.valueOf(resultObject.getLong(AccessToken.EXPIRES_IN_KEY)), Long.valueOf(resultObject.optLong(AccessToken.DATA_ACCESS_EXPIRATION_TIME)));
                        } catch (JSONException ex) {
                            DeviceAuthDialog.this.onError(new FacebookException((Throwable) ex));
                        }
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void presentConfirmation(String userId, Utility.PermissionsLists permissions, String accessToken, String name, Date expirationTime, Date dataAccessExpirationTime) {
        String message = getResources().getString(C1278R.string.com_facebook_smart_login_confirmation_title);
        String continueFormat = getResources().getString(C1278R.string.com_facebook_smart_login_confirmation_continue_as);
        String cancel = getResources().getString(C1278R.string.com_facebook_smart_login_confirmation_cancel);
        String continueText = String.format(continueFormat, new Object[]{name});
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final String str = userId;
        final Utility.PermissionsLists permissionsLists = permissions;
        final String str2 = accessToken;
        final Date date = expirationTime;
        final Date date2 = dataAccessExpirationTime;
        builder.setMessage(message).setCancelable(true).setNegativeButton(continueText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface alertDialog, int which) {
                DeviceAuthDialog.this.completeLogin(str, permissionsLists, str2, date, date2);
            }
        }).setPositiveButton(cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface alertDialog, int which) {
                DeviceAuthDialog.this.dialog.setContentView(DeviceAuthDialog.this.initializeContentView(false));
                DeviceAuthDialog.this.startLogin(DeviceAuthDialog.this.mRequest);
            }
        });
        builder.create().show();
    }

    /* access modifiers changed from: private */
    public void onSuccess(final String accessToken, Long expiresIn, Long dataAccessExpirationTime) {
        Bundle parameters = new Bundle();
        parameters.putString(GraphRequest.FIELDS_PARAM, "id,permissions,name");
        final Date expirationTime = expiresIn.longValue() != 0 ? new Date(new Date().getTime() + (expiresIn.longValue() * 1000)) : null;
        final Date dataAccessExpirationTimeDate = (dataAccessExpirationTime.longValue() == 0 || dataAccessExpirationTime == null) ? null : new Date(dataAccessExpirationTime.longValue() * 1000);
        new GraphRequest(new AccessToken(accessToken, FacebookSdk.getApplicationId(), AppEventsConstants.EVENT_PARAM_VALUE_NO, (Collection<String>) null, (Collection<String>) null, (Collection<String>) null, (AccessTokenSource) null, expirationTime, (Date) null, dataAccessExpirationTimeDate), "me", parameters, HttpMethod.GET, new GraphRequest.Callback() {
            public void onCompleted(GraphResponse response) {
                if (!DeviceAuthDialog.this.completed.get()) {
                    if (response.getError() != null) {
                        DeviceAuthDialog.this.onError(response.getError().getException());
                        return;
                    }
                    try {
                        JSONObject jsonObject = response.getJSONObject();
                        String userId = jsonObject.getString("id");
                        Utility.PermissionsLists permissions = Utility.handlePermissionResponse(jsonObject);
                        String name = jsonObject.getString("name");
                        DeviceRequestsHelper.cleanUpAdvertisementService(DeviceAuthDialog.this.currentRequestState.getUserCode());
                        if (!FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId()).getSmartLoginOptions().contains(SmartLoginOption.RequireConfirm) || DeviceAuthDialog.this.isRetry) {
                            DeviceAuthDialog.this.completeLogin(userId, permissions, accessToken, expirationTime, dataAccessExpirationTimeDate);
                            return;
                        }
                        boolean unused = DeviceAuthDialog.this.isRetry = true;
                        DeviceAuthDialog.this.presentConfirmation(userId, permissions, accessToken, name, expirationTime, dataAccessExpirationTimeDate);
                    } catch (JSONException ex) {
                        DeviceAuthDialog.this.onError(new FacebookException((Throwable) ex));
                    }
                }
            }
        }).executeAsync();
    }

    /* access modifiers changed from: private */
    public void completeLogin(String userId, Utility.PermissionsLists permissions, String accessToken, Date expirationTime, Date dataAccessExpirationTime) {
        this.deviceAuthMethodHandler.onSuccess(accessToken, FacebookSdk.getApplicationId(), userId, permissions.getGrantedPermissions(), permissions.getDeclinedPermissions(), permissions.getExpiredPermissions(), AccessTokenSource.DEVICE_AUTH, expirationTime, (Date) null, dataAccessExpirationTime);
        this.dialog.dismiss();
    }

    /* access modifiers changed from: protected */
    public void onError(FacebookException ex) {
        if (this.completed.compareAndSet(false, true)) {
            if (this.currentRequestState != null) {
                DeviceRequestsHelper.cleanUpAdvertisementService(this.currentRequestState.getUserCode());
            }
            this.deviceAuthMethodHandler.onError(ex);
            this.dialog.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public void onCancel() {
        if (this.completed.compareAndSet(false, true)) {
            if (this.currentRequestState != null) {
                DeviceRequestsHelper.cleanUpAdvertisementService(this.currentRequestState.getUserCode());
            }
            if (this.deviceAuthMethodHandler != null) {
                this.deviceAuthMethodHandler.onCancel();
            }
            this.dialog.dismiss();
        }
    }

    private static class RequestState implements Parcelable {
        public static final Parcelable.Creator<RequestState> CREATOR = new Parcelable.Creator<RequestState>() {
            public RequestState createFromParcel(Parcel in) {
                return new RequestState(in);
            }

            public RequestState[] newArray(int size) {
                return new RequestState[size];
            }
        };
        private String authorizationUri;
        private long interval;
        private long lastPoll;
        private String requestCode;
        private String userCode;

        RequestState() {
        }

        public String getAuthorizationUri() {
            return this.authorizationUri;
        }

        public String getUserCode() {
            return this.userCode;
        }

        public void setUserCode(String userCode2) {
            this.userCode = userCode2;
            this.authorizationUri = String.format(Locale.ENGLISH, "https://facebook.com/device?user_code=%1$s&qr=1", new Object[]{userCode2});
        }

        public String getRequestCode() {
            return this.requestCode;
        }

        public void setRequestCode(String requestCode2) {
            this.requestCode = requestCode2;
        }

        public long getInterval() {
            return this.interval;
        }

        public void setInterval(long interval2) {
            this.interval = interval2;
        }

        public void setLastPoll(long lastPoll2) {
            this.lastPoll = lastPoll2;
        }

        protected RequestState(Parcel in) {
            this.authorizationUri = in.readString();
            this.userCode = in.readString();
            this.requestCode = in.readString();
            this.interval = in.readLong();
            this.lastPoll = in.readLong();
        }

        public boolean withinLastRefreshWindow() {
            if (this.lastPoll != 0 && (new Date().getTime() - this.lastPoll) - (this.interval * 1000) < 0) {
                return true;
            }
            return false;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.authorizationUri);
            dest.writeString(this.userCode);
            dest.writeString(this.requestCode);
            dest.writeLong(this.interval);
            dest.writeLong(this.lastPoll);
        }
    }
}
