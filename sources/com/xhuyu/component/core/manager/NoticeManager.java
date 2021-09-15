package com.xhuyu.component.core.manager;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import com.alibaba.fastjson.JSON;
import com.doraemon.p027eg.CheckUtils;
import com.xhuyu.component.core.api.GameSDKListener;
import com.xhuyu.component.core.api.OnDownloadListener;
import com.xhuyu.component.core.config.SuperTool;
import com.xhuyu.component.mvp.model.LoginResult;
import com.xhuyu.component.mvp.model.NoticeBean;
import com.xhuyu.component.network.NetBasicUtil;
import com.xhuyu.component.p036ui.activity.NoticeActivity;
import com.xhuyu.component.p036ui.fragment.ExitDialogFragment;
import com.xhuyu.component.p036ui.fragment.NoticeExitDialogFragment;
import com.xsdk.doraemon.event.SDKEventBus;
import com.xsdk.doraemon.event.Subscribe;
import com.xsdk.doraemon.utils.ContextUtil;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;
import java.util.List;
import org.json.JSONObject;

public class NoticeManager {
    private static final int NOTICE_REQUEST_CODE = 141389;
    public static final int NOTICE_RESULT_CODE = 141125;
    private static NoticeManager mInstance;
    private int currentAfterIndex;
    private int currentBeforeIndex;
    private Activity mActivity;
    private List<NoticeBean.NoticeData> mBeforeExitNoticeArr;
    private List<NoticeBean.NoticeData> mBeforeLoginNoticeArr;
    /* access modifiers changed from: private */
    public Bitmap mCurrentExitBitmap;
    private List<NoticeBean.NoticeData> mLoginedNoticeArr;
    private boolean needOpenNotice;

    private NoticeManager() {
        SDKEventBus.getDefault().register(this);
    }

    public static NoticeManager getInstance() {
        if (mInstance == null) {
            synchronized (NoticeManager.class) {
                if (mInstance == null) {
                    mInstance = new NoticeManager();
                }
            }
        }
        return mInstance;
    }

    public void initNotice(Activity activity) {
        this.mActivity = activity;
        NetBasicUtil.postNotice(new GameSDKListener() {
            public void onSuccess(JSONObject dataJson, String message, Object... args) {
                if (dataJson != null) {
                    try {
                        NoticeBean noticeBean = (NoticeBean) JSON.parseObject(dataJson.toString(), NoticeBean.class);
                        if (noticeBean != null) {
                            NoticeManager.this.openNotice(noticeBean);
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                SDKLoggerUtil.getLogger().mo19502e("notice fail: dataJson is null", new Object[0]);
            }

            public void onFail(String message, int code) {
                SDKLoggerUtil.getLogger().mo19502e("notice fail: " + message, new Object[0]);
            }
        });
    }

    public void openNotice(NoticeBean noticeBean) {
        this.needOpenNotice = false;
        if (noticeBean.getInit() != null && noticeBean.getInit().size() > 0) {
            this.mBeforeLoginNoticeArr = noticeBean.getInit();
            this.currentBeforeIndex = 0;
            beginOpenNotice(this.mBeforeLoginNoticeArr.get(0));
        }
        if (noticeBean.getLogined() != null && noticeBean.getLogined().size() > 0) {
            this.mLoginedNoticeArr = noticeBean.getLogined();
        }
        if (noticeBean.getExit() != null && noticeBean.getExit().size() > 0) {
            this.mBeforeExitNoticeArr = noticeBean.getExit();
            NoticeBean.NoticeData noticeData = this.mBeforeExitNoticeArr.get(0);
            if (!CheckUtils.isNullOrEmpty(noticeData.getImage_url())) {
                NetBasicUtil.downloadImage(noticeData.getImage_url(), new OnDownloadListener() {
                    public void onResult(Object object) {
                        if (object instanceof Bitmap) {
                            if (NoticeManager.this.mCurrentExitBitmap != null) {
                                NoticeManager.this.mCurrentExitBitmap.recycle();
                                Bitmap unused = NoticeManager.this.mCurrentExitBitmap = null;
                            }
                            Bitmap unused2 = NoticeManager.this.mCurrentExitBitmap = (Bitmap) object;
                        }
                    }

                    public void onError(String failMessage) {
                    }
                });
            }
        }
    }

    @Subscribe(event = {3})
    public void onLoginResult(LoginResult result) {
        if (result.getResultCode() == 1 && this.mLoginedNoticeArr != null && this.mLoginedNoticeArr.size() > 0) {
            this.needOpenNotice = true;
            this.currentAfterIndex = 0;
            beginOpenNotice(this.mLoginedNoticeArr.get(0));
        }
    }

    @Subscribe(event = {9})
    public void onStartExitApp() {
        FragmentTransaction mFragTransaction = ContextUtil.getInstance().getActivity().getFragmentManager().beginTransaction();
        Fragment fragment = ContextUtil.getInstance().getActivity().getFragmentManager().findFragmentByTag("dialogFragment");
        if (fragment != null) {
            mFragTransaction.remove(fragment);
        }
        if (this.mBeforeExitNoticeArr == null || this.mBeforeExitNoticeArr.size() <= 0 || this.mCurrentExitBitmap == null) {
            new ExitDialogFragment().show(mFragTransaction, "dialogFragment");
        } else {
            NoticeExitDialogFragment.newInstance(this.mBeforeExitNoticeArr.get(0), this.mCurrentExitBitmap).show(mFragTransaction, "dialogFragment");
        }
    }

    private void beginOpenNotice(NoticeBean.NoticeData noticeData) {
        if (noticeData == null) {
            SDKLoggerUtil.getLogger().mo19502e("notice params is null", new Object[0]);
        } else if (this.mActivity == null || this.mActivity.isFinishing()) {
            SDKLoggerUtil.getLogger().mo19502e("Activity is null", new Object[0]);
        } else {
            SuperTool.getInstance().setActivityResultType(4);
            NoticeActivity.start(this.mActivity, NOTICE_REQUEST_CODE, noticeData.getNotice_title(), noticeData);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != 141125 || requestCode != NOTICE_REQUEST_CODE) {
            return;
        }
        if (this.needOpenNotice) {
            this.currentAfterIndex++;
            if (this.currentAfterIndex < this.mLoginedNoticeArr.size()) {
                beginOpenNotice(this.mLoginedNoticeArr.get(this.currentAfterIndex));
                return;
            }
            return;
        }
        this.currentBeforeIndex++;
        if (this.currentBeforeIndex < this.mBeforeLoginNoticeArr.size()) {
            beginOpenNotice(this.mBeforeLoginNoticeArr.get(this.currentBeforeIndex));
        }
    }
}
