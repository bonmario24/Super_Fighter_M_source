package com.eagle.mixsdk.sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.doraemon.p027eg.CheckUtils;
import java.util.ArrayList;

public class SplashActivity extends Activity {
    /* access modifiers changed from: private */
    public int currentIndex;
    Animation.AnimationListener mAnimationListener = new Animation.AnimationListener() {
        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            if (SplashActivity.this.splashResNameArray != null && SplashActivity.this.splashResNameArray.size() > 0 && SplashActivity.this.splashAnimationTimeArray != null && SplashActivity.this.splashAnimationTimeArray.size() > 0 && SplashActivity.this.currentIndex < SplashActivity.this.splashResNameArray.size()) {
                SplashActivity.this.changeImageLocation();
                int drawableRes = SplashActivity.this.getDrawableResID((String) SplashActivity.this.splashResNameArray.get(SplashActivity.this.currentIndex));
                if (drawableRes > 0) {
                    SplashActivity.this.mSplashImg.setImageResource(drawableRes);
                    AlphaAnimation ani = new AlphaAnimation(0.6f, 1.0f);
                    ani.setRepeatMode(2);
                    ani.setRepeatCount(0);
                    ani.setDuration(((Long) SplashActivity.this.splashAnimationTimeArray.get(SplashActivity.this.currentIndex)).longValue());
                    ani.setAnimationListener(SplashActivity.this.mAnimationListener);
                    SplashActivity.this.mContainsLayout.setAnimation(ani);
                    SplashActivity.access$208(SplashActivity.this);
                    ani.start();
                    return;
                }
            }
            SplashActivity.this.startGameActivity();
        }

        public void onAnimationRepeat(Animation animation) {
        }
    };
    /* access modifiers changed from: private */
    public RelativeLayout mContainsLayout;
    /* access modifiers changed from: private */
    public ImageView mSplashImg;
    /* access modifiers changed from: private */
    public ArrayList<Long> splashAnimationTimeArray;
    /* access modifiers changed from: private */
    public ArrayList<String> splashResNameArray;

    static /* synthetic */ int access$208(SplashActivity x0) {
        int i = x0.currentIndex;
        x0.currentIndex = i + 1;
        return i;
    }

    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(1);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(1024, 1024);
        initData();
        this.mContainsLayout = new RelativeLayout(this);
        this.mContainsLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        setContentView(this.mContainsLayout);
        setupUI();
        hideBottomUIMenu();
    }

    private void setupUI() {
        if (this.splashResNameArray == null || this.splashResNameArray.size() <= 0 || this.splashAnimationTimeArray == null || this.splashAnimationTimeArray.size() <= 0) {
            startGameActivity();
            return;
        }
        this.currentIndex = 0;
        changeImageLocation();
        int drawableRes = getDrawableResID(this.splashResNameArray.get(this.currentIndex));
        if (drawableRes > 0) {
            this.mSplashImg.setImageResource(drawableRes);
            appendAnimation(this.splashAnimationTimeArray.get(this.currentIndex));
            this.currentIndex++;
        }
    }

    /* access modifiers changed from: private */
    public void changeImageLocation() {
        String splashName = this.splashResNameArray.get(this.currentIndex);
        if (this.mSplashImg != null) {
            this.mContainsLayout.clearAnimation();
            this.mContainsLayout.removeAllViews();
            this.mSplashImg = null;
        }
        this.mSplashImg = new ImageView(this);
        if (splashName.equalsIgnoreCase("eagle_dedicated_splash")) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13);
            this.mSplashImg.setLayoutParams(layoutParams);
            this.mContainsLayout.setBackgroundColor(-1);
            int drawableResID = getDrawableResID("eagle_splash_content");
            if (drawableResID > 0) {
                ImageView ivExtImage = new ImageView(this);
                RelativeLayout.LayoutParams layoutParamsExt = new RelativeLayout.LayoutParams(-2, -2);
                layoutParamsExt.addRule(14);
                layoutParamsExt.addRule(12);
                ivExtImage.setLayoutParams(layoutParamsExt);
                ivExtImage.setImageResource(drawableResID);
                this.mContainsLayout.addView(ivExtImage);
            }
        } else {
            this.mSplashImg.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            this.mSplashImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        this.mContainsLayout.addView(this.mSplashImg);
    }

    private void initData() {
        SDKParams sdkParams = EagleSDK.getInstance().getSDKParams();
        if (sdkParams != null) {
            String splashInfo = sdkParams.getString("Eagle_Dedicated_Splash_List");
            if (!CheckUtils.isNullOrEmpty(splashInfo)) {
                String[] split = splashInfo.split(",");
                this.splashResNameArray = new ArrayList<>();
                this.splashAnimationTimeArray = new ArrayList<>();
                for (int i = 0; i < split.length; i++) {
                    String info = split[i];
                    if (i % 2 == 0) {
                        this.splashResNameArray.add(info);
                    } else if (CheckUtils.isNumeric(info)) {
                        this.splashAnimationTimeArray.add(Long.valueOf(Long.parseLong(info)));
                    }
                }
                return;
            }
        }
        startGameActivity();
    }

    /* access modifiers changed from: private */
    public int getDrawableResID(String drawableResName) {
        return getResources().getIdentifier(drawableResName, "drawable", getPackageName());
    }

    private int getAnimResID(String animationName) {
        try {
            int anim = getResources().getIdentifier(animationName, "anim", getPackageName());
            if (anim > 0) {
                return anim;
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    private void appendAnimation(Long duration) {
        AlphaAnimation ani = new AlphaAnimation(0.0f, 1.0f);
        ani.setRepeatMode(2);
        ani.setRepeatCount(0);
        ani.setDuration(duration.longValue());
        ani.setAnimationListener(this.mAnimationListener);
        this.mContainsLayout.setAnimation(ani);
    }

    /* access modifiers changed from: private */
    public void startGameActivity() {
        if (this.mSplashImg != null) {
            this.mSplashImg.clearAnimation();
        }
        try {
            Intent intent = new Intent(this, Class.forName("{EagleSDK_Game_Activity}"));
            if (Build.VERSION.SDK_INT <= 19) {
                finish();
                startActivity(intent);
                return;
            }
            startActivity(intent);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(getAnimResID("anim_no"), getAnimResID("anim_no"));
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        hideBottomUIMenu();
    }

    /* access modifiers changed from: protected */
    public void hideBottomUIMenu() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            getWindow().getDecorView().setSystemUiVisibility(8);
        } else if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(4102);
        }
    }
}
