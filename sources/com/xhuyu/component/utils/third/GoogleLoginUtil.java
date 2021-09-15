package com.xhuyu.component.utils.third;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.Nullable;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.thinkfly.star.utils.CheckUtils;
import com.xhuyu.component.core.config.SuperTool;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;

public class GoogleLoginUtil {
    private static final int REQUEST_CODE_GOOGLE_LOGIN = 9001;
    private static GoogleLoginUtil instance = null;
    private Activity mActivity;
    /* access modifiers changed from: private */
    public FirebaseUser mFirebaseUser;
    private GoogleSignInClient mGoogleSignInClient;
    /* access modifiers changed from: private */
    public GoogleLoginListener mLoginListener;

    public interface GoogleLoginListener {
        void onError(int i, String str);

        void onSuccessWithAuthFirebase(String str, FirebaseUser firebaseUser);
    }

    private GoogleLoginUtil() {
    }

    public static GoogleLoginUtil getInstance() {
        if (instance == null) {
            synchronized (GoogleLoginUtil.class) {
                if (instance == null) {
                    instance = new GoogleLoginUtil();
                }
            }
        }
        return instance;
    }

    public void initGoogleSignInClient(Activity activity, GoogleLoginListener loginListener) {
        this.mActivity = activity;
        this.mLoginListener = loginListener;
        this.mGoogleSignInClient = GoogleSignIn.getClient(activity, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestIdToken(activity.getString(activity.getResources().getIdentifier("server_client_id", "string", activity.getPackageName()))).build());
    }

    public void doGoogleLogin() {
        SDKLoggerUtil.getLogger().mo19504i("googleLogin", new Object[0]);
        if (this.mActivity == null || this.mActivity.isFinishing()) {
            SDKLoggerUtil.getLogger().mo19502e("login fail : activity is null or  finishing ?", new Object[0]);
            return;
        }
        this.mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this.mActivity);
        if (account == null || CheckUtils.isNullOrEmpty(account.getIdToken())) {
            Intent signInIntent = this.mGoogleSignInClient.getSignInIntent();
            SuperTool.getInstance().setActivityResultType(2);
            this.mActivity.startActivityForResult(signInIntent, REQUEST_CODE_GOOGLE_LOGIN);
        } else if (this.mFirebaseUser != null) {
            this.mLoginListener.onSuccessWithAuthFirebase(account.getIdToken(), this.mFirebaseUser);
        } else {
            firebaseAuthWithGoogle(account);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_GOOGLE_LOGIN) {
            try {
                GoogleSignInAccount account = GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException.class);
                if (account != null) {
                    firebaseAuthWithGoogle(account);
                    return;
                }
                this.mFirebaseUser = null;
                this.mLoginListener.onError(-1, "login fail account is empty");
            } catch (ApiException e) {
                e.printStackTrace();
                int statusCode = e.getStatusCode();
                String statusCodeString = GoogleSignInStatusCodes.getStatusCodeString(statusCode);
                this.mFirebaseUser = null;
                this.mLoginListener.onError(statusCode, statusCodeString);
            }
        }
    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount acct) {
        SDKLoggerUtil.getLogger().mo19501d("firebaseAuthWithGoogle:" + acct.getId(), new Object[0]);
        FirebaseAuth.getInstance().signInWithCredential(GoogleAuthProvider.getCredential(acct.getIdToken(), (String) null)).addOnCompleteListener(this.mActivity, new OnCompleteListener<AuthResult>() {
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    SDKLoggerUtil.getLogger().mo19501d("signInWithCredential:success", new Object[0]);
                    if (task.getResult() != null) {
                        FirebaseUser unused = GoogleLoginUtil.this.mFirebaseUser = task.getResult().getUser();
                        if (GoogleLoginUtil.this.mFirebaseUser != null) {
                            GoogleLoginUtil.this.mLoginListener.onSuccessWithAuthFirebase(acct.getIdToken(), GoogleLoginUtil.this.mFirebaseUser);
                        } else {
                            GoogleLoginUtil.this.mLoginListener.onError(-1, "credential verify fail");
                        }
                    } else {
                        FirebaseUser unused2 = GoogleLoginUtil.this.mFirebaseUser = null;
                        GoogleLoginUtil.this.mLoginListener.onError(-1, "credential verify fail");
                    }
                } else {
                    FirebaseUser unused3 = GoogleLoginUtil.this.mFirebaseUser = null;
                    SDKLoggerUtil.getLogger().mo19509w("signInWithCredential:failure", task.getException());
                    GoogleLoginUtil.this.mLoginListener.onError(-1, task.getException() != null ? task.getException().toString() : "");
                }
            }
        });
    }

    public FirebaseUser getFirebaseUser() {
        return this.mFirebaseUser;
    }

    public void signOut() {
        try {
            FirebaseAuth.getInstance().signOut();
            if (this.mGoogleSignInClient != null) {
                this.mGoogleSignInClient.signOut();
            }
            this.mFirebaseUser = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
