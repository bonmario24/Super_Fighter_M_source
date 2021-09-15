package com.xsdk.doraemon.widget.circular;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.doraemon.util.SizeUtils;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HorizontalScrollCircularView extends HorizontalScrollView {
    private LinearLayout mImageContains;
    private int mItemMargin;
    /* access modifiers changed from: private */
    public OnCircularViewItemClick mOnCircularViewItemClick;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (HorizontalScrollCircularView.this.mOnCircularViewItemClick != null) {
                HorizontalScrollCircularView.this.mOnCircularViewItemClick.onItemClick(v.getId());
            }
        }
    };
    private LinkedHashMap<Integer, CircularWithShadowImageView> mResImgMap;

    public interface OnCircularViewItemClick {
        void onItemClick(int i);
    }

    public HorizontalScrollCircularView(Context context) {
        super(context);
        initView();
    }

    public HorizontalScrollCircularView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public HorizontalScrollCircularView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        this.mResImgMap = new LinkedHashMap<>();
        this.mImageContains = new LinearLayout(getContext());
        changeLayoutParams(17);
        this.mImageContains.setOrientation(0);
        this.mItemMargin = SizeUtils.dp2px(10.0f);
        this.mImageContains.setDescendantFocusability(393216);
        addView(this.mImageContains);
    }

    private void changeLayoutParams(int gravity) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = gravity;
        this.mImageContains.setLayoutParams(layoutParams);
        this.mImageContains.setGravity(gravity);
    }

    public HorizontalScrollCircularView addImage(CircularModel circularModel) {
        if (circularModel != null) {
            this.mResImgMap.put(Integer.valueOf(circularModel.getResID()), createImageView(circularModel));
        }
        return this;
    }

    public HorizontalScrollCircularView addImages(List<CircularModel> circularList) {
        for (CircularModel circularModel : circularList) {
            addImage(circularModel);
        }
        return this;
    }

    public void commit() {
        if (this.mResImgMap != null && this.mResImgMap.size() > 0) {
            for (Map.Entry<Integer, CircularWithShadowImageView> viewEntry : this.mResImgMap.entrySet()) {
                this.mImageContains.addView(viewEntry.getValue());
            }
            if (this.mResImgMap.size() < 4) {
                return;
            }
            if (isOldSmallPhone() || this.mResImgMap.size() > 4) {
                changeLayoutParams(16);
            }
        }
    }

    private boolean isOldSmallPhone() {
        return getContext().getResources().getDisplayMetrics().densityDpi <= 240;
    }

    public HorizontalScrollCircularView setOnClickListener(OnCircularViewItemClick onClickListener) {
        this.mOnCircularViewItemClick = onClickListener;
        return this;
    }

    private CircularWithShadowImageView createImageView(CircularModel circularModel) {
        Drawable drawable = circularModel.getDrawable();
        CircularWithShadowImageView imageView = new CircularWithShadowImageView(getContext());
        imageView.setImageDrawable(drawable);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (((float) drawable.getIntrinsicWidth()) * circularModel.getScale()), (int) (((float) drawable.getIntrinsicHeight()) * circularModel.getScale()));
        params.gravity = 17;
        params.rightMargin = this.mItemMargin;
        params.leftMargin = this.mItemMargin;
        imageView.setLayoutParams(params);
        int viewID = circularModel.getResID();
        if (viewID > 0) {
            imageView.setId(viewID);
            imageView.setOnClickListener(this.mOnClickListener);
        }
        return imageView;
    }
}
