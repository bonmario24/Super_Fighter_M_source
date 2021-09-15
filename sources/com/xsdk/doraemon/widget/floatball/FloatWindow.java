package com.xsdk.doraemon.widget.floatball;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import com.xsdk.doraemon.utils.ContextUtil;
import com.xsdk.doraemon.utils.notch.utils.DeviceTools;
import com.xsdk.doraemon.utils.notch.utils.NotchUtil;
import java.util.List;

public class FloatWindow implements View.OnTouchListener {
    public static final int SIDE_BOTTOM = 16;
    public static final int SIDE_LEFT = 2;
    public static final int SIDE_RIGHT = 4;
    public static final int SIDE_TOP = 8;
    private static final String TAG = FloatWindow.class.getSimpleName();
    private View contentView;
    /* access modifiers changed from: private */
    public Context context;
    private int curSide = -1;
    private int curX = Integer.MAX_VALUE;
    private int curY = Integer.MAX_VALUE;
    private Drawable drawableDraging;
    private Drawable drawableNormal;
    /* access modifiers changed from: private */
    public Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                FloatWindow.this.hideInSide();
            }
        }
    };
    private float hideRatio = 0.5f;
    private boolean isAttach = false;
    private boolean isShowing = false;
    private Activity mActivity;
    private long maxTime = 500;
    private List<Rect> rectArray;
    private int side_enable = 30;
    /* access modifiers changed from: private */
    public long stopTime = 2300;
    private int thresholdX = 10;
    private int thresholdY = 10;
    private float touchX;
    private float touchY;
    private float viewX;
    private float viewY;

    public FloatWindow(Context context2, View contentView2) {
        this.contentView = contentView2;
        this.context = context2.getApplicationContext();
        init();
    }

    private void init() {
        if (this.contentView == null) {
            throw new RuntimeException("contentView is null！");
        }
        this.contentView.setOnTouchListener(this);
    }

    public void setDrawableDraging(Drawable drawableDraging2) {
        this.drawableDraging = drawableDraging2;
    }

    public void setDrawableNormal(Drawable drawableNormal2) {
        this.drawableNormal = drawableNormal2;
    }

    public void setStopTime(long stopTime2) {
        this.stopTime = stopTime2;
    }

    public void scrollToStartXY() {
        this.curSide = calSide();
        this.contentView.post(new Runnable() {
            public void run() {
                int[] currentXYLocation = FloatWindow.this.getCurrentXYLocation();
                FloatWindow.this.scrollTo(currentXYLocation[0], currentXYLocation[1]);
            }
        });
    }

    /* access modifiers changed from: private */
    public int[] getCurrentXYLocation() {
        int cy;
        int tempY;
        int cy2 = ((int) (((double) ContextUtil.getInstance().getHeightPixels()) * 0.5d)) - this.contentView.getHeight();
        int cx = (int) (((float) (-this.contentView.getWidth())) * this.hideRatio);
        int statusBarHeight = DeviceTools.getStatusBarHeight(this.context);
        if (this.context.getResources().getConfiguration().orientation == 2) {
            if (Build.VERSION.SDK_INT >= 28) {
                if (this.rectArray != null && this.rectArray.size() > 0) {
                    int viewHeight = this.contentView.getHeight();
                    for (Rect rect : this.rectArray) {
                        if (rect.top != 0 && rect.top > viewHeight) {
                            int locationY = (int) (((double) (rect.top - viewHeight)) * 0.5d);
                            if (locationY > viewHeight) {
                                cy = locationY;
                            } else {
                                cy = (int) (((double) viewHeight) * 0.5d);
                            }
                            if (cy < statusBarHeight && (tempY = (int) (((double) cy) + (((double) statusBarHeight) * 0.5d))) < rect.top) {
                                cy = tempY;
                            }
                            if (rect.right != 0) {
                                if (rect.left == 0) {
                                    cx -= rect.right;
                                } else {
                                    if (DeviceTools.hasNavBar(this.context)) {
                                        DeviceTools.hideBottomUIMenu(getActivity());
                                    }
                                    cx = (int) (((float) rect.right) - (((float) this.contentView.getWidth()) * this.hideRatio));
                                }
                            }
                            return new int[]{cx, cy};
                        } else if ((rect.top == 0 || rect.top < statusBarHeight) && rect.right != 0) {
                            if (rect.left == 0) {
                                cx -= rect.right;
                            } else {
                                if (DeviceTools.hasNavBar(this.context)) {
                                    DeviceTools.hideBottomUIMenu(getActivity());
                                }
                                cx = (int) (((float) rect.right) - (((float) this.contentView.getWidth()) * this.hideRatio));
                            }
                        }
                    }
                }
            } else if (Build.VERSION.SDK_INT >= 26) {
                NotchUtil.getNotchAtSize(getActivity());
            }
        }
        return new int[]{cx, cy2};
    }

    public void setRectArray(List<Rect> rectArray2) {
        this.rectArray = rectArray2;
    }

    public View getContentView() {
        return this.contentView;
    }

    public Activity getActivity() {
        if (this.mActivity == null || this.mActivity.isFinishing()) {
            this.mActivity = ContextUtil.getInstance().getActivity();
        }
        return this.mActivity;
    }

    public void setSideEnable(int side_enable2) {
        if (side_enable2 < 2) {
            throw new RuntimeException("side_enable must select from SIDE_LEFT|SIDE_RIGHT|SIDE_BOTTOM|SIDE_TOP");
        }
        this.side_enable = side_enable2;
    }

    public void attach(Activity activity) {
        if (!this.isAttach && activity != null && !activity.isFinishing()) {
            this.mActivity = activity;
            this.mActivity.getWindow().getWindowManager().addView(this.contentView, generateLayoutParams(0, 0));
            if (DeviceTools.hasNavBar(activity)) {
                DeviceTools.hideBottomUIMenu(activity);
            }
            this.contentView.setVisibility(8);
            this.isAttach = true;
        }
    }

    private WindowManager.LayoutParams generateLayoutParams(int x, int y) {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.type = 2;
        params.format = -3;
        params.width = -2;
        params.height = -2;
        params.flags = 552;
        params.x = x;
        params.y = y;
        params.gravity = 51;
        ViewGroup.LayoutParams lp = this.contentView.getLayoutParams();
        if (lp != null) {
            params.width = lp.width;
            params.height = lp.height;
        }
        return params;
    }

    public boolean isAddToWindow() {
        if (this.contentView == null || this.contentView.getParent() == null) {
            return false;
        }
        return true;
    }

    public boolean isAttach() {
        return (this.contentView == null || getActivity() == null || !this.isAttach) ? false : true;
    }

    public void detach() {
        if (isAttach()) {
            try {
                if (getActivity() != null && !getActivity().isFinishing()) {
                    ContextUtil.getInstance().getActivity().getWindowManager().removeView(this.contentView);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.curX = Integer.MAX_VALUE;
            this.curY = Integer.MAX_VALUE;
            this.isShowing = false;
            this.isAttach = false;
        }
    }

    public void show() {
        if (isAttach() && !isShowing()) {
            this.contentView.setVisibility(0);
            this.isShowing = true;
            if (this.curX == Integer.MAX_VALUE) {
                scrollToStartXY();
            }
        }
    }

    public void dismiss() {
        if (isShowing()) {
            this.contentView.setVisibility(8);
            this.isShowing = false;
        }
    }

    public boolean isShowing() {
        return this.isShowing;
    }

    /* access modifiers changed from: private */
    public int[] getWindowOffset() {
        WindowManager.LayoutParams lp = (WindowManager.LayoutParams) this.contentView.getLayoutParams();
        return new int[]{lp.x, lp.y};
    }

    private int[] getScreenSize() {
        DisplayMetrics displayMetrics = this.context.getResources().getDisplayMetrics();
        try {
            if (getActivity() != null && !getActivity().isFinishing()) {
                View content = ((ViewGroup) getActivity().getWindow().getDecorView()).getChildAt(0);
                return new int[]{content.getMeasuredWidth(), content.getMeasuredHeight() - getStatusBarHeight(this.context)};
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels};
    }

    private int getStatusBarHeight(Context context2) {
        int result = 0;
        if (!getStatusBarVisiable()) {
            return 0;
        }
        int resourceId = context2.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context2.getResources().getDimensionPixelSize(resourceId);
        }
        int i = result;
        return result;
    }

    private boolean getStatusBarVisiable() {
        if (!isAttach()) {
            return false;
        }
        int result = 0;
        if (getActivity() != null && !getActivity().isFinishing()) {
            try {
                result = getActivity().getWindow().getDecorView().getSystemUiVisibility() & 4;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (result == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public int[] getContentViewSize() {
        return new int[]{this.contentView.getMeasuredWidth(), this.contentView.getMeasuredHeight()};
    }

    /* access modifiers changed from: private */
    public int calSide() {
        int[] windowOffset = getWindowOffset();
        int[] screenSizes = getScreenSize();
        float top = (float) windowOffset[1];
        float bottom = (float) (screenSizes[1] - windowOffset[1]);
        float left = (float) windowOffset[0];
        float right = (float) (screenSizes[0] - windowOffset[0]);
        SparseArray<Float> array = new SparseArray<>();
        if ((this.side_enable & 8) != 0) {
            array.append(8, Float.valueOf(top));
        }
        if ((this.side_enable & 16) != 0) {
            array.append(16, Float.valueOf(bottom));
        }
        if ((this.side_enable & 2) != 0) {
            array.append(2, Float.valueOf(left));
        }
        if ((this.side_enable & 4) != 0) {
            array.append(4, Float.valueOf(right));
        }
        int mixKey = 0;
        for (int key = 2; key <= 16; key <<= 1) {
            if (array.get(key, Float.valueOf(Float.MAX_VALUE)).floatValue() < array.get(mixKey, Float.valueOf(Float.MAX_VALUE)).floatValue()) {
                mixKey = key;
            }
        }
        Log.d("shen", "calSide-->" + mixKey);
        return mixKey;
    }

    private void calCurXY() {
        int[] windowOffset = getWindowOffset();
        int[] screenSize = getScreenSize();
        int[] contentViewSize = getContentViewSize();
        int maxX = screenSize[0] - contentViewSize[0];
        int maxY = screenSize[1] - contentViewSize[1];
        if (isAttach()) {
            switch (this.curSide) {
                case 2:
                    this.curX = 0;
                    this.curY = windowOffset[1];
                    this.curY = limitInt(this.curY, 0, maxY);
                    break;
                case 4:
                    this.curX = maxX;
                    this.curY = windowOffset[1];
                    this.curY = limitInt(this.curY, 0, maxY);
                    break;
                case 8:
                    this.curX = windowOffset[0];
                    this.curY = 0;
                    this.curX = limitInt(this.curX, 0, maxX);
                    break;
                case 16:
                    this.curX = windowOffset[0];
                    this.curY = maxY;
                    this.curX = limitInt(this.curX, 0, maxX);
                    break;
            }
            Log.d("shen", "calCurXY-->" + this.curX + "--" + this.curY);
        }
    }

    private int limitInt(int value, int min, int max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    public static ImageView createSimpleImageView(Context context2, Drawable drawable, int size) {
        ImageView imageView = new ImageView(context2);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageDrawable(drawable);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(size, size));
        return imageView;
    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case 0:
                this.handler.removeMessages(1);
                this.touchX = event.getRawX();
                this.touchY = event.getRawY();
                int[] windowOffset = getWindowOffset();
                this.viewX = (float) windowOffset[0];
                this.viewY = (float) windowOffset[1];
                changeImage(true);
                break;
            case 1:
                int[] windowOffset2 = getWindowOffset();
                float vx = (float) windowOffset2[0];
                float vy = (float) windowOffset2[1];
                scrollToSide();
                changeImage(false);
                return !isClick((int) (vx - this.viewX), (int) (vy - this.viewY));
            case 2:
                int dx = (int) (event.getRawX() - this.touchX);
                int dy = (int) (event.getRawY() - this.touchY);
                this.touchX = event.getRawX();
                this.touchY = event.getRawY();
                scrollBy(dx, dy);
                break;
        }
        return false;
    }

    private void changeImage(boolean isDraging) {
        if (isDraging && this.drawableDraging != null) {
            ((ImageView) getContentView()).setImageDrawable(this.drawableDraging);
        } else if (this.drawableNormal != null) {
            ((ImageView) getContentView()).setImageDrawable(this.drawableNormal);
        }
    }

    private boolean isClick(int dx, int dy) {
        Log.d("shen", "isClick-->" + dx + "--" + dy);
        return Math.abs(dx) < this.thresholdX && Math.abs(dy) < this.thresholdY;
    }

    private void scrollBy(int dx, int dy) {
        if (dx != 0 || dy != 0) {
            WindowManager.LayoutParams lp = (WindowManager.LayoutParams) this.contentView.getLayoutParams();
            lp.x += dx;
            lp.y += dy;
            if (getActivity() != null && !getActivity().isFinishing()) {
                try {
                    getActivity().getWindowManager().updateViewLayout(this.contentView, lp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Log.d("shen", "scrollBy-->" + dx + "--" + dy);
        }
    }

    public void scrollTo(int x, int y) {
        WindowManager.LayoutParams lp = (WindowManager.LayoutParams) this.contentView.getLayoutParams();
        lp.x = x;
        lp.y = y;
        if (getActivity() != null && !getActivity().isFinishing()) {
            try {
                getActivity().getWindowManager().updateViewLayout(this.contentView, lp);
                Log.d("shen", "scrollTo-->" + x + "--" + y);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void scrollWithAnima(float x, float y, Animator.AnimatorListener listener) {
        int[] windowOffset = getWindowOffset();
        ValueAnimator ox = ValueAnimator.ofFloat(new float[]{(float) windowOffset[0], x});
        ValueAnimator oy = ValueAnimator.ofFloat(new float[]{(float) windowOffset[1], y});
        float dx = Math.abs(((float) windowOffset[0]) - x);
        float dy = Math.abs(((float) windowOffset[1]) - y);
        DisplayMetrics displayMetrics = this.context.getResources().getDisplayMetrics();
        if (getActivity() != null && !getActivity().isFinishing()) {
            displayMetrics = getActivity().getResources().getDisplayMetrics();
        }
        long time = (long) (((float) this.maxTime) * (Math.max(dx, dy) / ((float) Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels))));
        ox.setDuration(time);
        oy.setDuration(time);
        ox.setInterpolator(new OvershootInterpolator());
        oy.setInterpolator(new OvershootInterpolator());
        ox.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = ((Float) animation.getAnimatedValue()).floatValue();
                float x = animatedValue;
                Context ctext = FloatWindow.this.context;
                if (FloatWindow.this.getActivity() != null && !FloatWindow.this.getActivity().isFinishing()) {
                    ctext = FloatWindow.this.getActivity();
                }
                DisplayMetrics displayMetrics = ctext.getResources().getDisplayMetrics();
                if (ctext.getResources().getConfiguration().orientation == 2 && animatedValue >= ((float) displayMetrics.widthPixels)) {
                    x = (float) displayMetrics.widthPixels;
                }
                if (FloatWindow.this.getActivity() != null && !FloatWindow.this.getActivity().isFinishing()) {
                    DeviceTools.hideBottomUIMenu(FloatWindow.this.getActivity());
                }
                FloatWindow.this.scrollTo((int) x, FloatWindow.this.getWindowOffset()[1]);
            }
        });
        oy.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                float y = ((Float) animation.getAnimatedValue()).floatValue();
                if (FloatWindow.this.getActivity() != null && !FloatWindow.this.getActivity().isFinishing()) {
                    DeviceTools.hideBottomUIMenu(FloatWindow.this.getActivity());
                }
                if (FloatWindow.this.calSide() == 4) {
                    FloatWindow.this.scrollTo(FloatWindow.this.getWindowOffset()[0] - FloatWindow.this.getContentViewSize()[0], (int) y);
                } else {
                    FloatWindow.this.scrollTo(FloatWindow.this.getWindowOffset()[0], (int) y);
                }
            }
        });
        if (listener != null) {
            ox.addListener(listener);
        }
        ox.start();
        oy.start();
        Log.d("shen", "scrollWithAnima time-->" + time);
    }

    /* access modifiers changed from: private */
    public void hideInSide() {
        int barHeight;
        int barHeight2;
        if (isAttach()) {
            int[] contentViewSize = getContentViewSize();
            int[] windowOffset = getWindowOffset();
            this.curSide = calSide();
            int statusBarHeight = DeviceTools.getStatusBarHeight(this.context);
            int widthPixels = ContextUtil.getInstance().getWidthPixels();
            int heightPixels = ContextUtil.getInstance().getHeightPixels();
            int orientation = ContextUtil.getInstance().getOrientation();
            Point screenRealSize = DeviceTools.getScreenRealSize(this.context);
            WindowManager.LayoutParams wmParams = (WindowManager.LayoutParams) this.contentView.getLayoutParams();
            switch (this.curSide) {
                case 2:
                    if (orientation == 2) {
                        if (Build.VERSION.SDK_INT >= 28) {
                            int[] newWindowOffset = compatibilityPSideLocation(this.curSide);
                            if (newWindowOffset != null) {
                                windowOffset = newWindowOffset;
                                break;
                            }
                        } else if (Build.VERSION.SDK_INT < 26 || NotchUtil.getNotchAtSize(getActivity()) != null) {
                        }
                        int currY = wmParams.y;
                        if (currY < statusBarHeight) {
                            windowOffset[1] = currY + statusBarHeight;
                        }
                    }
                    windowOffset[0] = (int) (((float) (-contentViewSize[0])) * this.hideRatio);
                    if (DeviceTools.hasNavBar(this.context) && windowOffset[1] + (contentViewSize[1] * 2) >= (barHeight2 = heightPixels - DeviceTools.getNavigationBarHeight(this.context))) {
                        windowOffset[1] = barHeight2 - (contentViewSize[1] * 2);
                        break;
                    }
                case 4:
                    if (orientation != 2) {
                        windowOffset[0] = (int) (((float) widthPixels) - (((float) contentViewSize[0]) * this.hideRatio));
                        if (DeviceTools.hasNavBar(this.context) && windowOffset[1] + (contentViewSize[1] * 2) >= (barHeight = heightPixels - DeviceTools.getNavigationBarHeight(this.context))) {
                            windowOffset[1] = barHeight - (contentViewSize[1] * 2);
                            break;
                        }
                    } else {
                        if (Build.VERSION.SDK_INT >= 28) {
                            int[] newWindowOffset2 = compatibilityPSideLocation(this.curSide);
                            if (newWindowOffset2 != null) {
                                windowOffset = newWindowOffset2;
                                break;
                            }
                        } else if (Build.VERSION.SDK_INT < 26 || NotchUtil.getNotchAtSize(getActivity()) != null) {
                        }
                        if (Build.VERSION.SDK_INT >= 28) {
                            windowOffset[0] = (int) (((double) DeviceTools.getScreenRealSize(this.context).x) - (((double) contentViewSize[0]) + (((double) contentViewSize[0]) * 0.2d)));
                        } else {
                            windowOffset[0] = (int) (((float) widthPixels) - (((float) contentViewSize[0]) * this.hideRatio));
                        }
                        int currY2 = wmParams.y;
                        if (currY2 < statusBarHeight) {
                            windowOffset[1] = currY2 + statusBarHeight;
                            break;
                        }
                    }
                    break;
                case 8:
                    windowOffset[1] = (int) (((float) (-contentViewSize[1])) * this.hideRatio);
                    break;
                case 16:
                    windowOffset[1] = (int) (((float) windowOffset[1]) + (((float) contentViewSize[1]) * this.hideRatio));
                    break;
            }
            scrollTo(windowOffset[0], windowOffset[1]);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x004c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int[] compatibilityPSideLocation(int r19) {
        /*
            r18 = this;
            int r12 = android.os.Build.VERSION.SDK_INT
            r13 = 28
            if (r12 < r13) goto L_0x01ba
            r0 = r18
            java.util.List<android.graphics.Rect> r12 = r0.rectArray
            if (r12 == 0) goto L_0x01ba
            r0 = r18
            java.util.List<android.graphics.Rect> r12 = r0.rectArray
            int r12 = r12.size()
            if (r12 <= 0) goto L_0x01ba
            int[] r2 = r18.getContentViewSize()
            r0 = r18
            android.content.Context r12 = r0.context
            android.graphics.Point r12 = com.xsdk.doraemon.utils.notch.utils.DeviceTools.getScreenRealSize(r12)
            int r9 = r12.x
            r0 = r18
            android.content.Context r12 = r0.context
            int r6 = com.xsdk.doraemon.utils.notch.utils.DeviceTools.getStatusBarHeight(r12)
            int[] r10 = r18.getWindowOffset()
            r0 = r18
            android.view.View r12 = r0.contentView
            android.view.ViewGroup$LayoutParams r11 = r12.getLayoutParams()
            android.view.WindowManager$LayoutParams r11 = (android.view.WindowManager.LayoutParams) r11
            int r4 = r11.y
            int r8 = r4 + r6
            r0 = r18
            java.util.List<android.graphics.Rect> r12 = r0.rectArray
            java.util.Iterator r12 = r12.iterator()
        L_0x0046:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L_0x01ba
            java.lang.Object r5 = r12.next()
            android.graphics.Rect r5 = (android.graphics.Rect) r5
            r13 = 4
            r0 = r19
            if (r0 == r13) goto L_0x005c
            r13 = 2
            r0 = r19
            if (r0 != r13) goto L_0x0046
        L_0x005c:
            int r12 = r5.top
            if (r4 >= r12) goto L_0x0064
            int r12 = r5.top
            if (r8 < r12) goto L_0x0139
        L_0x0064:
            int r12 = r5.bottom
            if (r4 <= r12) goto L_0x006c
            int r12 = r5.bottom
            if (r8 > r12) goto L_0x0139
        L_0x006c:
            r12 = 4
            r0 = r19
            if (r0 != r12) goto L_0x0075
            int r12 = r5.left
            if (r12 != 0) goto L_0x007e
        L_0x0075:
            r12 = 2
            r0 = r19
            if (r0 != r12) goto L_0x00e6
            int r12 = r5.left
            if (r12 != 0) goto L_0x00e6
        L_0x007e:
            int r12 = r5.top
            double r12 = (double) r12
            r14 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r12 = r12 * r14
            r14 = 1
            r14 = r2[r14]
            double r14 = (double) r14
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 <= 0) goto L_0x00dd
            int r12 = r5.top
            double r12 = (double) r12
            r14 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r12 = r12 * r14
            int r12 = (int) r12
            r11.y = r12
        L_0x0095:
            int r12 = r11.y
            if (r12 >= r6) goto L_0x00b0
            int r12 = r11.y
            int r12 = r12 + r6
            r0 = r18
            android.view.View r13 = r0.contentView
            int r13 = r13.getHeight()
            int r7 = r12 + r13
            int r12 = r5.top
            if (r7 < r12) goto L_0x00ae
            int r12 = r5.top
            if (r12 >= r6) goto L_0x00b0
        L_0x00ae:
            r11.y = r7
        L_0x00b0:
            r12 = 2
            r0 = r19
            if (r0 != r12) goto L_0x00fb
            int r12 = r5.left
            if (r12 != 0) goto L_0x00c5
            int r12 = r5.right
            double r12 = (double) r12
            double r14 = (double) r9
            r16 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r14 = r14 * r16
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 <= 0) goto L_0x00e9
        L_0x00c5:
            r12 = 0
            r12 = r2[r12]
            float r12 = (float) r12
            r0 = r18
            float r13 = r0.hideRatio
            float r12 = r12 * r13
            float r12 = -r12
            int r12 = (int) r12
            r11.x = r12
        L_0x00d2:
            r12 = 0
            int r13 = r11.x
            r10[r12] = r13
            r12 = 1
            int r13 = r11.y
            r10[r12] = r13
        L_0x00dc:
            return r10
        L_0x00dd:
            int r12 = r5.bottom
            r13 = 1
            r13 = r2[r13]
            int r12 = r12 + r13
            r11.y = r12
            goto L_0x0095
        L_0x00e6:
            r11.y = r4
            goto L_0x0095
        L_0x00e9:
            r12 = 0
            r12 = r2[r12]
            int r12 = -r12
            float r12 = (float) r12
            r0 = r18
            float r13 = r0.hideRatio
            float r12 = r12 * r13
            int r13 = r5.right
            float r13 = (float) r13
            float r12 = r12 - r13
            int r12 = (int) r12
            r11.x = r12
            goto L_0x00d2
        L_0x00fb:
            int r12 = r5.right
            if (r12 >= r9) goto L_0x010b
            int r12 = r5.right
            double r12 = (double) r12
            double r14 = (double) r9
            r16 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r14 = r14 * r16
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 <= 0) goto L_0x0121
        L_0x010b:
            int r12 = r5.right
            float r12 = (float) r12
            r0 = r18
            android.view.View r13 = r0.contentView
            int r13 = r13.getWidth()
            float r13 = (float) r13
            r0 = r18
            float r14 = r0.hideRatio
            float r13 = r13 * r14
            float r12 = r12 - r13
            int r12 = (int) r12
            r11.x = r12
            goto L_0x00d2
        L_0x0121:
            int r12 = r5.right
            int r12 = r9 - r12
            float r12 = (float) r12
            r0 = r18
            android.view.View r13 = r0.contentView
            int r13 = r13.getWidth()
            float r13 = (float) r13
            r0 = r18
            float r14 = r0.hideRatio
            float r13 = r13 * r14
            float r12 = r12 - r13
            int r12 = (int) r12
            r11.x = r12
            goto L_0x00d2
        L_0x0139:
            r12 = 2
            r0 = r19
            if (r0 != r12) goto L_0x017a
            int r12 = r5.left
            if (r12 != 0) goto L_0x014e
            int r12 = r5.right
            double r12 = (double) r12
            double r14 = (double) r9
            r16 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r14 = r14 * r16
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 <= 0) goto L_0x0167
        L_0x014e:
            r12 = 0
            r13 = 0
            r13 = r2[r13]
            float r13 = (float) r13
            r0 = r18
            float r14 = r0.hideRatio
            float r13 = r13 * r14
            float r13 = -r13
            int r13 = (int) r13
            r10[r12] = r13
        L_0x015c:
            int r3 = r11.y
            if (r3 >= r6) goto L_0x00dc
            r12 = 1
            int r13 = r3 + r6
            r10[r12] = r13
            goto L_0x00dc
        L_0x0167:
            r12 = 0
            r13 = 0
            r13 = r2[r13]
            float r13 = (float) r13
            r0 = r18
            float r14 = r0.hideRatio
            float r13 = r13 * r14
            int r14 = r5.right
            float r14 = (float) r14
            float r13 = r13 + r14
            float r13 = -r13
            int r13 = (int) r13
            r10[r12] = r13
            goto L_0x015c
        L_0x017a:
            int r12 = r5.right
            if (r12 >= r9) goto L_0x018a
            int r12 = r5.right
            double r12 = (double) r12
            double r14 = (double) r9
            r16 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r14 = r14 * r16
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 <= 0) goto L_0x01a1
        L_0x018a:
            r12 = 0
            int r13 = r5.right
            float r13 = (float) r13
            r0 = r18
            android.view.View r14 = r0.contentView
            int r14 = r14.getWidth()
            float r14 = (float) r14
            r0 = r18
            float r15 = r0.hideRatio
            float r14 = r14 * r15
            float r13 = r13 - r14
            int r13 = (int) r13
            r10[r12] = r13
            goto L_0x015c
        L_0x01a1:
            r12 = 0
            int r13 = r5.right
            int r13 = r9 - r13
            float r13 = (float) r13
            r0 = r18
            android.view.View r14 = r0.contentView
            int r14 = r14.getWidth()
            float r14 = (float) r14
            r0 = r18
            float r15 = r0.hideRatio
            float r14 = r14 * r15
            float r13 = r13 - r14
            int r13 = (int) r13
            r10[r12] = r13
            goto L_0x015c
        L_0x01ba:
            r10 = 0
            goto L_0x00dc
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xsdk.doraemon.widget.floatball.FloatWindow.compatibilityPSideLocation(int):int[]");
    }

    private void scrollToSide() {
        this.curSide = calSide();
        calCurXY();
        scrollWithAnima((float) this.curX, (float) this.curY, new Animator.AnimatorListener() {
            public void onAnimationStart(Animator animation) {
            }

            public void onAnimationEnd(Animator animation) {
                FloatWindow.this.handler.sendEmptyMessageDelayed(1, FloatWindow.this.stopTime);
            }

            public void onAnimationCancel(Animator animation) {
            }

            public void onAnimationRepeat(Animator animation) {
            }
        });
    }
}
