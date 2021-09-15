package com.star.libtrack.core;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import com.star.libtrack.obserable.ClickObserable;
import com.star.libtrack.obserable.OkHttpObserable;
import com.star.libtrack.obserable.PageObserable;
import com.star.libtrack.observer.ClickObserver;
import com.star.libtrack.observer.OkHttpObserver;
import com.star.libtrack.observer.PageObserver;
import com.star.libtrack.util.Utility;
import java.util.HashMap;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class TrackCore {
    private static String LOG_TAG = "xsdk_track";
    private static TrackCore instance = new TrackCore();
    private ICloudLadder cloudLadder;
    private boolean isInit = false;
    private String mChannelID;
    private Context mContext;
    private String mGameID;
    private String mVersion;
    private OkHttpObserable okHttpObserable;
    private Map<String, Pair<Integer, Long>> pageCache = new HashMap();
    private PageObserable pageObserable;
    private PageRecorder pageRecorder;
    private String uid = "";

    private TrackCore() {
    }

    public static TrackCore getInstance() {
        return instance;
    }

    /* access modifiers changed from: private */
    public void debugLog(String msg) {
        Log.d(LOG_TAG, msg);
    }

    public Context getContext() {
        return this.mContext;
    }

    public void setUid(String new_uid) {
        this.uid = new_uid;
    }

    public String getUid() {
        return Utility.isEmpty(this.uid) ? "" : this.uid;
    }

    public String getVersion() {
        return this.mVersion;
    }

    public String getChannelID() {
        return this.mChannelID;
    }

    public String getGameID() {
        return this.mGameID;
    }

    public boolean isInit() {
        if (this.isInit && this.cloudLadder != null) {
            return true;
        }
        Log.e(LOG_TAG, "ladder or track init error ");
        return false;
    }

    public ICloudLadder getCloudLadder() {
        return this.cloudLadder;
    }

    public void init(Context context, String version, String channelID, String gameID, boolean isDebug, String who, String host, String salt) {
        this.mContext = context;
        this.mChannelID = channelID;
        this.mVersion = version;
        this.mGameID = gameID;
        this.isInit = TrackConfigManager.loadConfig(context);
        if (!this.isInit) {
            debugLog("load track config fail");
        } else {
            createCloudLadder(isDebug, who, host, salt);
        }
    }

    private void createCloudLadder(boolean isDebug, String who, String host, String salt) {
        if (this.cloudLadder == null) {
            this.cloudLadder = new CloudLadderImpl();
            this.cloudLadder.init(isDebug, who, host, salt);
            debugLog("cloudladder init ok");
        }
    }

    public void initPageTrack(Activity activity) {
        this.pageRecorder = new PageRecorder();
        this.pageObserable = new PageObserable();
        this.pageObserable.addObserver(new PageObserver() {
            public void onActivityCreate(Activity activity) {
                TrackCore.this.pageEnter(TrackCore.this.readActivityId(activity));
            }

            public void onActivityDestroy(Activity activity) {
                TrackCore.this.pageExit(TrackCore.this.readActivityId(activity));
            }

            public void onViewAdded(View view) {
                TrackCore.this.debugLog("onViewAdded  " + view.getTag());
                TrackCore.this.pageEnter(TrackCore.this.readViewId(view));
            }

            public void onViewRemoved(View view) {
                TrackCore.this.debugLog("onViewRemoved  " + view.getTag());
                TrackCore.this.pageExit(TrackCore.this.readViewId(view));
            }
        });
        trackActivity(activity.getApplication());
    }

    public void trackActivity(Application application) {
        if (this.pageObserable == null) {
            debugLog("未调用initPageTrack");
        } else {
            this.pageObserable.observerActivity(application);
        }
    }

    /* access modifiers changed from: private */
    public String readActivityId(Activity activity) {
        if (activity == null) {
            return "";
        }
        String tag = (String) activity.getWindow().getDecorView().getTag();
        if (TextUtils.isEmpty(tag) || !tag.startsWith("tag=")) {
            return activity.getClass().getSimpleName();
        }
        return tag.replaceFirst("tag=", "");
    }

    /* access modifiers changed from: private */
    public String readViewId(View view) {
        Object tag = view.getTag();
        return tag == null ? "" : tag.toString();
    }

    /* access modifiers changed from: private */
    public void pageEnter(String name) {
        String tmpName = name;
        if (name.startsWith("normal_login_")) {
            name = "normal_login";
        }
        if (!this.pageCache.containsKey(name)) {
            this.pageCache.put(name, new Pair(1, Long.valueOf(System.currentTimeMillis())));
        } else {
            this.pageCache.put(name, new Pair(Integer.valueOf(((Integer) this.pageCache.get(name).first).intValue() + 1), Long.valueOf(System.currentTimeMillis())));
        }
        String name2 = tmpName;
        debugLog("pageEnter--->" + name2);
        this.pageRecorder.add(name2);
        this.pageRecorder.printPageStack();
        submitPageEvent(name2, true);
    }

    /* access modifiers changed from: private */
    public void pageExit(String name) {
        debugLog("pageExit--->" + name);
        this.pageRecorder.removeLast(name);
        this.pageRecorder.printPageStack();
        submitPageEvent(name, false);
    }

    public void trackView(ViewGroup vp) {
        if (this.pageObserable == null) {
            debugLog("未调用initPageTrack");
        } else {
            this.pageObserable.observerView(vp);
        }
    }

    public boolean submitPageEvent(String id, boolean isEnter) {
        JSONObject obj;
        if (TextUtils.isEmpty(id) || (obj = TrackConfigManager.queryPageEvent(id, isEnter)) == null) {
            return false;
        }
        try {
            String action = obj.getString(NativeProtocol.WEB_DIALOG_ACTION);
            Map<String, Object> extMap = TrackConfigManager.getExtMap(obj);
            String tmpId = id;
            if (id.startsWith("normal_login_")) {
                id = "normal_login";
            }
            if (extMap == null || !this.pageCache.containsKey(id)) {
                return false;
            }
            String id2 = tmpId;
            return submitCustomEvent(action, extMap);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String readRelId(android.view.View r7) {
        /*
            r6 = this;
            r3 = 0
            if (r7 != 0) goto L_0x0005
            r1 = r3
        L_0x0004:
            return r1
        L_0x0005:
            r1 = 0
            java.lang.Object r4 = r7.getTag()
            if (r4 == 0) goto L_0x002c
            java.lang.Object r4 = r7.getTag()
            java.lang.String r2 = r4.toString()
            java.lang.String r4 = "tag="
            boolean r4 = r2.startsWith(r4)
            if (r4 == 0) goto L_0x002c
            java.lang.String r4 = "tag="
            java.lang.String r5 = ""
            java.lang.String r1 = r2.replaceFirst(r4, r5)
        L_0x0024:
            boolean r4 = android.text.TextUtils.isEmpty(r1)
            if (r4 == 0) goto L_0x0044
            r1 = r3
            goto L_0x0004
        L_0x002c:
            int r4 = r7.getId()
            r5 = -1
            if (r4 == r5) goto L_0x0024
            android.content.Context r4 = r7.getContext()
            android.content.res.Resources r4 = r4.getResources()
            int r5 = r7.getId()
            java.lang.String r1 = r4.getResourceEntryName(r5)
            goto L_0x0024
        L_0x0044:
            boolean r4 = r7 instanceof android.widget.CheckBox
            if (r4 == 0) goto L_0x0068
            r0 = r7
            android.widget.CheckBox r0 = (android.widget.CheckBox) r0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.StringBuilder r4 = r3.append(r1)
            boolean r3 = r0.isChecked()
            if (r3 == 0) goto L_0x0065
            java.lang.String r3 = "#yes"
        L_0x005c:
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.String r1 = r3.toString()
            goto L_0x0004
        L_0x0065:
            java.lang.String r3 = "#no"
            goto L_0x005c
        L_0x0068:
            boolean r4 = r7 instanceof android.widget.ListView
            if (r4 == 0) goto L_0x0004
            android.widget.ListView r7 = (android.widget.ListView) r7
            r6.changeListViewListener(r7)
            r1 = r3
            goto L_0x0004
        */
        throw new UnsupportedOperationException("Method not decompiled: com.star.libtrack.core.TrackCore.readRelId(android.view.View):java.lang.String");
    }

    private void changeListViewListener(ListView lv) {
        AdapterView.OnItemClickListener onItemClickListener = lv.getOnItemClickListener();
        if (!(onItemClickListener instanceof HookOnItemClickListener)) {
            lv.setOnItemClickListener(new HookOnItemClickListener(onItemClickListener));
        }
    }

    private String getCurPage() {
        if (this.pageRecorder == null) {
            return "";
        }
        return this.pageRecorder.getLast();
    }

    public OkHttpClient initOkHttpTrack(OkHttpClient client) {
        this.okHttpObserable = new OkHttpObserable();
        this.okHttpObserable.addObserver(new OkHttpObserver() {
            public void onHttpRequest(Request request) {
            }

            public void onHttpResponse(Response response) {
                String tag = response.header(OkHttpObserable.OKHTTP_TAG);
                if (TextUtils.isEmpty(tag)) {
                    boolean unused = TrackCore.this.submitOkHttpEvent(response.request().url().toString(), true, response);
                } else {
                    boolean unused2 = TrackCore.this.submitOkHttpEvent(tag, false, response);
                }
            }
        });
        return this.okHttpObserable.observer(client);
    }

    /* access modifiers changed from: private */
    public boolean submitOkHttpEvent(String id, boolean isUrl, Response response) {
        JSONObject obj;
        if (TextUtils.isEmpty(id) || (obj = TrackConfigManager.queryOkHttpEvent(id, isUrl)) == null) {
            return false;
        }
        try {
            String action = obj.getString(NativeProtocol.WEB_DIALOG_ACTION);
            Map<String, Object> extMap = TrackConfigManager.getExtMap(obj);
            JSONObject contentobj = new JSONObject(response.peekBody(Long.MAX_VALUE).string());
            if (contentobj.has("code")) {
                int code = contentobj.getInt("code");
                if (code == 1) {
                    extMap.put("result", 1);
                }
                if (code != 1 && contentobj.has(ShareConstants.WEB_DIALOG_PARAM_MESSAGE)) {
                    extMap.put("result", 0);
                    extMap.put("cause", contentobj.getString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE));
                }
            }
            if (extMap != null) {
                return submitCustomEvent(action, extMap);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ClickObserable trackClick(Activity activity) {
        ClickObserable clickObserable = ClickObserable.observer(activity);
        clickObserable.addObserver(new ClickObserver() {
            public void onViewClicked(View view) {
                TrackCore.this.debugLog("点击---" + view.toString());
                TrackCore.this.submitClickEvent(view);
            }
        });
        return clickObserable;
    }

    private class HookOnItemClickListener implements AdapterView.OnItemClickListener {
        private AdapterView.OnItemClickListener base;

        public HookOnItemClickListener(AdapterView.OnItemClickListener base2) {
            this.base = base2;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if (this.base != null) {
                this.base.onItemClick(adapterView, view, i, l);
                String lvid = adapterView.getContext().getResources().getResourceEntryName(adapterView.getId());
                if (!TextUtils.isEmpty(lvid)) {
                    view.setTag("tag=" + lvid + "[" + i + "]");
                    TrackCore.this.submitClickEvent(view);
                }
            }
        }
    }

    public boolean submitClickEvent(View view) {
        JSONObject obj;
        String id = readRelId(view);
        if (TextUtils.isEmpty(id) || (obj = TrackConfigManager.queryClickEvent(getCurPage(), id)) == null) {
            return false;
        }
        try {
            String action = obj.getString(NativeProtocol.WEB_DIALOG_ACTION);
            Map<String, Object> extMap = TrackConfigManager.getExtMap(obj);
            if (extMap != null) {
                return submitCustomEvent(action, extMap);
            }
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean submitCustomEvent(String action, Map<String, Object> ext) {
        if (!isInit()) {
            return false;
        }
        this.cloudLadder.submitCustomEvent(action, this.uid, ext);
        return true;
    }

    public void submitStartupEvent() {
        if (isInit()) {
            this.cloudLadder.submitStartupEvent();
        }
    }
}
