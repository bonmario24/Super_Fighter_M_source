package com.xsdk.doraemon.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import java.util.Locale;

public class TabPageIndicator extends HorizontalScrollView {
    private static final int[] ATTRS = {16842901, 16842904};
    private boolean checkedTabWidths;
    private IndicatorMode currentIndicatorMode;
    /* access modifiers changed from: private */
    public int currentPosition;
    /* access modifiers changed from: private */
    public float currentPositionOffset;
    public ViewPager.OnPageChangeListener delegatePageListener;
    private int dividerColor;
    private int dividerPadding;
    private Paint dividerPaint;
    private int dividerWidth;
    private int indicatorColor;
    private int indicatorHeight;
    private int indicatorPaddingLeft;
    private int indicatorPaddingRight;
    private boolean isExpand;
    private boolean isExpandSameLine;
    private boolean isSameLine;
    private int lastScrollX;
    private Locale locale;
    private Context mContext;
    private final PageListener pageListener;
    /* access modifiers changed from: private */
    public ViewPager pager;
    private Paint rectPaint;
    private int scrollOffset;
    private int tabBackgroundResId;
    /* access modifiers changed from: private */
    public int tabCount;
    private int tabPadding;
    /* access modifiers changed from: private */
    public int tabTextColor;
    /* access modifiers changed from: private */
    public int tabTextColorSelected;
    private int tabTextSize;
    /* access modifiers changed from: private */
    public LinearLayout tabsContainer;
    private boolean textAllCaps;
    private int underlineColor;
    private int underlineHeight;
    private LinearLayout.LayoutParams weightTabLayoutParams;
    private int[] widths;
    private LinearLayout.LayoutParams wrapTabLayoutParams;

    public enum IndicatorMode {
        MODE_WEIGHT_NOEXPAND_SAME(0),
        MODE_WEIGHT_NOEXPAND_NOSAME(1),
        MODE_NOWEIGHT_NOEXPAND_SAME(2),
        MODE_NOWEIGHT_NOEXPAND_NOSAME(3),
        MODE_NOWEIGHT_EXPAND_SAME(4),
        MODE_NOWEIGHT_EXPAND_NOSAME(5);
        
        private int value;

        private IndicatorMode(int value2) {
            this.value = value2;
        }

        public int getValue() {
            return this.value;
        }
    }

    public TabPageIndicator(Context context) {
        this(context, (AttributeSet) null);
        this.mContext = context;
    }

    public TabPageIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.mContext = context;
    }

    public TabPageIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.pageListener = new PageListener();
        this.currentPosition = 0;
        this.currentPositionOffset = 0.0f;
        this.checkedTabWidths = false;
        this.indicatorColor = Color.parseColor("#ffffff");
        this.underlineColor = -2302756;
        this.dividerColor = 0;
        this.scrollOffset = 10;
        this.indicatorHeight = 2;
        this.underlineHeight = 1;
        this.dividerPadding = 0;
        this.dividerWidth = 0;
        this.indicatorPaddingLeft = 0;
        this.indicatorPaddingRight = 0;
        this.tabTextSize = 16;
        this.tabTextColor = -10066330;
        this.tabTextColorSelected = Color.parseColor("#ffffff");
        this.lastScrollX = 0;
        this.currentIndicatorMode = IndicatorMode.MODE_WEIGHT_NOEXPAND_SAME;
        this.mContext = context;
        setFillViewport(true);
        setWillNotDraw(false);
        this.tabsContainer = new LinearLayout(context);
        this.tabsContainer.setOrientation(0);
        this.tabsContainer.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(this.tabsContainer);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        this.scrollOffset = (int) TypedValue.applyDimension(1, (float) this.scrollOffset, dm);
        this.indicatorHeight = (int) TypedValue.applyDimension(1, (float) this.indicatorHeight, dm);
        this.underlineHeight = (int) TypedValue.applyDimension(1, (float) this.underlineHeight, dm);
        this.dividerPadding = (int) TypedValue.applyDimension(1, (float) this.dividerPadding, dm);
        this.tabPadding = (int) TypedValue.applyDimension(1, (float) this.tabPadding, dm);
        this.dividerWidth = (int) TypedValue.applyDimension(1, (float) this.dividerWidth, dm);
        this.tabTextSize = (int) TypedValue.applyDimension(1, (float) this.tabTextSize, dm);
        TypedArray a = context.obtainStyledAttributes(attrs, ATTRS);
        this.tabTextSize = a.getDimensionPixelSize(0, this.tabTextSize);
        a.recycle();
        this.rectPaint = new Paint();
        this.rectPaint.setAntiAlias(true);
        this.rectPaint.setStyle(Paint.Style.FILL);
        this.dividerPaint = new Paint();
        this.dividerPaint.setAntiAlias(true);
        this.dividerPaint.setStrokeWidth((float) this.dividerWidth);
        this.wrapTabLayoutParams = new LinearLayout.LayoutParams(-2, -1);
        this.weightTabLayoutParams = new LinearLayout.LayoutParams(0, -1, 1.0f);
        if (this.locale == null) {
            this.locale = getResources().getConfiguration().locale;
        }
    }

    public void setIndicatorMode(IndicatorMode indicatorMode) {
        switch (indicatorMode) {
            case MODE_WEIGHT_NOEXPAND_SAME:
                this.isExpand = false;
                this.isSameLine = true;
                break;
            case MODE_WEIGHT_NOEXPAND_NOSAME:
                this.isExpand = false;
                this.isSameLine = false;
                break;
            case MODE_NOWEIGHT_NOEXPAND_SAME:
            case MODE_NOWEIGHT_NOEXPAND_NOSAME:
                this.isExpand = false;
                this.isSameLine = true;
                this.isExpandSameLine = true;
                this.tabPadding = dip2px(10.0f);
                break;
            case MODE_NOWEIGHT_EXPAND_SAME:
                this.isExpand = true;
                this.isExpandSameLine = true;
                this.tabPadding = dip2px(10.0f);
                break;
            case MODE_NOWEIGHT_EXPAND_NOSAME:
                this.isExpand = true;
                this.isExpandSameLine = false;
                this.tabPadding = dip2px(10.0f);
                break;
        }
        this.currentIndicatorMode = indicatorMode;
        notifyDataSetChanged();
    }

    public void setViewPager(ViewPager pager2) {
        this.pager = pager2;
        if (pager2.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        pager2.addOnPageChangeListener(this.pageListener);
        notifyDataSetChanged();
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        this.delegatePageListener = listener;
    }

    public void notifyDataSetChanged() {
        this.tabsContainer.removeAllViews();
        this.tabCount = this.pager.getAdapter().getCount();
        for (int i = 0; i < this.tabCount; i++) {
            addTextTab(i, this.pager.getAdapter().getPageTitle(i).toString());
        }
        updateTabStyles();
        this.checkedTabWidths = false;
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressLint({"NewApi"})
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < 16) {
                    TabPageIndicator.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    TabPageIndicator.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                int unused = TabPageIndicator.this.currentPosition = TabPageIndicator.this.pager.getCurrentItem();
                TabPageIndicator.this.scrollToChild(TabPageIndicator.this.currentPosition, 0);
            }
        });
    }

    private void addTextTab(final int position, String title) {
        TextView tab = new TextView(getContext());
        tab.setText(title);
        tab.setFocusable(true);
        tab.setGravity(17);
        tab.setSingleLine();
        tab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TabPageIndicator.this.pager.setCurrentItem(position);
            }
        });
        if (!this.isExpand || this.isExpandSameLine) {
            this.wrapTabLayoutParams.setMargins(this.tabPadding, 0, this.tabPadding, 0);
            this.weightTabLayoutParams.setMargins(this.tabPadding, 0, this.tabPadding, 0);
        } else {
            tab.setPadding(this.tabPadding, 0, this.tabPadding, 0);
        }
        this.tabsContainer.addView(tab, position, this.isSameLine ? this.wrapTabLayoutParams : this.weightTabLayoutParams);
    }

    private void updateTabStyles() {
        this.widths = new int[this.tabCount];
        int i = 0;
        while (i < this.tabCount) {
            View v = this.tabsContainer.getChildAt(i);
            if (v instanceof TextView) {
                TextView tab = (TextView) v;
                tab.setTextSize(0, (float) this.tabTextSize);
                tab.setTextColor(i == 0 ? this.tabTextColorSelected : this.tabTextColor);
                if (this.textAllCaps) {
                    if (Build.VERSION.SDK_INT >= 14) {
                        tab.setAllCaps(true);
                    } else {
                        tab.setText(tab.getText().toString().toUpperCase(this.locale));
                    }
                }
            }
            i++;
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!this.isExpand) {
            int myWidth = getMeasuredWidth();
            int childWidth = 0;
            for (int i = 0; i < this.tabCount; i++) {
                childWidth += this.tabsContainer.getChildAt(i).getMeasuredWidth();
                if (this.widths[i] == 0) {
                    this.widths[i] = this.tabsContainer.getChildAt(i).getMeasuredWidth();
                }
            }
            if (this.currentIndicatorMode == IndicatorMode.MODE_NOWEIGHT_NOEXPAND_SAME) {
                setIndicatorPaddingRight((myWidth - childWidth) - ((this.tabPadding * 2) * this.tabCount));
                this.tabsContainer.setPadding(this.indicatorPaddingLeft, 0, this.indicatorPaddingRight, 0);
            }
            if (this.currentIndicatorMode == IndicatorMode.MODE_NOWEIGHT_NOEXPAND_NOSAME) {
                setIndicatorPaddingRight((myWidth - childWidth) - ((this.tabPadding * 2) * this.tabCount));
                this.tabsContainer.setPadding(this.indicatorPaddingLeft, 0, this.indicatorPaddingRight, 0);
            }
            if (!this.checkedTabWidths && childWidth > 0 && myWidth > 0) {
                if (childWidth <= myWidth) {
                    for (int i2 = 0; i2 < this.tabCount; i2++) {
                        this.tabsContainer.getChildAt(i2).setLayoutParams(this.weightTabLayoutParams);
                    }
                } else {
                    for (int i3 = 0; i3 < this.tabCount; i3++) {
                        this.tabsContainer.getChildAt(i3).setLayoutParams(this.wrapTabLayoutParams);
                    }
                }
                this.checkedTabWidths = true;
            }
        }
    }

    /* access modifiers changed from: private */
    public void scrollToChild(int position, int offset) {
        if (this.tabCount != 0 && offset != 0) {
            int newScrollX = this.tabsContainer.getChildAt(position).getLeft() + offset;
            if (position > 0 || offset > 0) {
                newScrollX -= this.scrollOffset;
            }
            if (newScrollX != this.lastScrollX) {
                this.lastScrollX = newScrollX;
                scrollTo(newScrollX, 0);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float currentOffWid;
        float nextOffWid;
        super.onDraw(canvas);
        if (!isInEditMode() && this.tabCount != 0) {
            int height = getHeight();
            this.rectPaint.setColor(this.indicatorColor);
            View currentTab = this.tabsContainer.getChildAt(this.currentPosition);
            if (this.isExpand) {
                currentOffWid = 0.0f;
            } else {
                currentOffWid = (float) ((currentTab.getWidth() - this.widths[this.currentPosition]) / 2);
            }
            float lineLeft = ((float) currentTab.getLeft()) + currentOffWid;
            float lineRight = ((float) currentTab.getRight()) - currentOffWid;
            if (this.currentPositionOffset > 0.0f && this.currentPosition < this.tabCount - 1) {
                View nextTab = this.tabsContainer.getChildAt(this.currentPosition + 1);
                if (this.isExpand) {
                    nextOffWid = 0.0f;
                } else {
                    nextOffWid = (float) ((nextTab.getWidth() - this.widths[this.currentPosition + 1]) / 2);
                }
                float nextTabLeft = ((float) nextTab.getLeft()) + nextOffWid;
                float nextTabRight = ((float) nextTab.getRight()) - nextOffWid;
                lineLeft = (this.currentPositionOffset * nextTabLeft) + ((1.0f - this.currentPositionOffset) * lineLeft);
                lineRight = (this.currentPositionOffset * nextTabRight) + ((1.0f - this.currentPositionOffset) * lineRight);
            }
            if (this.currentIndicatorMode == IndicatorMode.MODE_NOWEIGHT_NOEXPAND_NOSAME) {
                canvas.drawRect(lineLeft - ((float) this.tabPadding), (float) (height - this.indicatorHeight), lineRight + ((float) this.tabPadding), (float) height, this.rectPaint);
            } else {
                canvas.drawRect(lineLeft, (float) (height - this.indicatorHeight), lineRight, (float) height, this.rectPaint);
            }
            this.rectPaint.setColor(this.underlineColor);
            canvas.drawRect(0.0f, (float) (height - this.underlineHeight), (float) this.tabsContainer.getWidth(), (float) height, this.rectPaint);
            this.dividerPaint.setColor(this.dividerColor);
            for (int i = 0; i < this.tabCount - 1; i++) {
                View tab = this.tabsContainer.getChildAt(i);
                if (!this.isExpandSameLine) {
                    canvas.drawLine((float) tab.getRight(), (float) this.dividerPadding, (float) tab.getRight(), (float) (height - this.dividerPadding), this.dividerPaint);
                } else {
                    canvas.drawLine((float) (tab.getRight() + this.tabPadding), (float) this.dividerPadding, (float) (tab.getRight() + this.tabPadding), (float) (height - this.dividerPadding), this.dividerPaint);
                }
            }
        }
    }

    private class PageListener implements ViewPager.OnPageChangeListener {
        private PageListener() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int unused = TabPageIndicator.this.currentPosition = position;
            float unused2 = TabPageIndicator.this.currentPositionOffset = positionOffset;
            TabPageIndicator.this.scrollToChild(position, (int) (((float) TabPageIndicator.this.tabsContainer.getChildAt(position).getWidth()) * positionOffset));
            TabPageIndicator.this.invalidate();
            if (TabPageIndicator.this.delegatePageListener != null) {
                TabPageIndicator.this.delegatePageListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }

        public void onPageScrollStateChanged(int state) {
            if (state == 0) {
                TabPageIndicator.this.scrollToChild(TabPageIndicator.this.pager.getCurrentItem(), 0);
            }
            if (TabPageIndicator.this.delegatePageListener != null) {
                TabPageIndicator.this.delegatePageListener.onPageScrollStateChanged(state);
            }
        }

        public void onPageSelected(int position) {
            if (TabPageIndicator.this.delegatePageListener != null) {
                TabPageIndicator.this.delegatePageListener.onPageSelected(position);
            }
            int i = 0;
            while (i < TabPageIndicator.this.tabCount) {
                View v = TabPageIndicator.this.tabsContainer.getChildAt(i);
                if (v instanceof TextView) {
                    ((TextView) v).setTextColor(i == TabPageIndicator.this.pager.getCurrentItem() ? TabPageIndicator.this.tabTextColorSelected : TabPageIndicator.this.tabTextColor);
                }
                i++;
            }
        }
    }

    public void setIndicatorColor(int indicatorColor2) {
        this.indicatorColor = indicatorColor2;
        invalidate();
    }

    public void setIndicatorColorResource(int resId) {
        this.indicatorColor = getResources().getColor(resId);
        invalidate();
    }

    public int getIndicatorColor() {
        return this.indicatorColor;
    }

    public void setIndicatorHeight(int indicatorLineHeightPx) {
        this.indicatorHeight = indicatorLineHeightPx;
        invalidate();
    }

    public int getIndicatorHeight() {
        return this.indicatorHeight;
    }

    public void setUnderlineColor(int underlineColor2) {
        this.underlineColor = underlineColor2;
        invalidate();
    }

    public void setUnderlineColorResource(int resId) {
        this.underlineColor = getResources().getColor(resId);
        invalidate();
    }

    public int getUnderlineColor() {
        return this.underlineColor;
    }

    public void setDividerColor(int dividerColor2) {
        this.dividerColor = dividerColor2;
        invalidate();
    }

    public void setDividerColorResource(int resId) {
        this.dividerColor = getResources().getColor(resId);
        invalidate();
    }

    public int getDividerColor() {
        return this.dividerColor;
    }

    public void setUnderlineHeight(int underlineHeightPx) {
        this.underlineHeight = underlineHeightPx;
        invalidate();
    }

    public int getUnderlineHeight() {
        return this.underlineHeight;
    }

    public void setDividerPadding(int dividerPaddingPx) {
        this.dividerPadding = dividerPaddingPx;
        invalidate();
    }

    public int getDividerPadding() {
        return this.dividerPadding;
    }

    public void setScrollOffset(int scrollOffsetPx) {
        this.scrollOffset = scrollOffsetPx;
        invalidate();
    }

    public int getScrollOffset() {
        return this.scrollOffset;
    }

    public void setSameLine(boolean sameLine) {
        this.isSameLine = sameLine;
        requestLayout();
    }

    public boolean getSameLine() {
        return this.isSameLine;
    }

    public boolean isTextAllCaps() {
        return this.textAllCaps;
    }

    public void setAllCaps(boolean textAllCaps2) {
        this.textAllCaps = textAllCaps2;
    }

    public void setTextSize(int textSizePx) {
        this.tabTextSize = textSizePx;
        updateTabStyles();
    }

    public int getTextSize() {
        return this.tabTextSize;
    }

    public void setTextColor(int textColor) {
        this.tabTextColor = textColor;
        updateTabStyles();
    }

    public void setTextColorSelected(int textColorSelected) {
        this.tabTextColorSelected = textColorSelected;
        updateTabStyles();
    }

    public void setTextColorResource(int resId) {
        this.tabTextColor = getResources().getColor(resId);
        updateTabStyles();
    }

    public int getTextColor() {
        return this.tabTextColor;
    }

    public void setTabBackground(int resId) {
        this.tabBackgroundResId = resId;
        updateTabStyles();
    }

    public int getTabBackground() {
        return this.tabBackgroundResId;
    }

    public void setTabPaddingLeftRight(int paddingPx) {
        this.tabPadding = paddingPx;
        updateTabStyles();
    }

    public int getTabPaddingLeftRight() {
        return this.tabPadding;
    }

    public int getIndicatorPaddingLeft() {
        return this.indicatorPaddingLeft;
    }

    public void setIndicatorPaddingLeft(int indicatorPaddingLeft2) {
        this.indicatorPaddingLeft = indicatorPaddingLeft2;
    }

    public int getIndicatorPaddingRight() {
        return this.indicatorPaddingRight;
    }

    public void setIndicatorPaddingRight(int indicatorPaddingRight2) {
        this.indicatorPaddingRight = indicatorPaddingRight2;
    }

    public void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.currentPosition = savedState.currentPosition;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.currentPosition = this.currentPosition;
        return savedState;
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        int currentPosition;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.currentPosition = in.readInt();
        }

        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(this.currentPosition);
        }
    }

    public int dip2px(float dpValue) {
        return (int) ((dpValue * this.mContext.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
