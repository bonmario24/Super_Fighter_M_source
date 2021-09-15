package com.xsdk.doraemon.widget.sidebar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public abstract class BaseSideBar extends View {
    private int choose = -1;
    private TextView mTextDialog;
    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    private Paint paint = new Paint();

    public interface OnTouchingLetterChangedListener {
        void onTouchingLetterChanged(String str);
    }

    /* access modifiers changed from: protected */
    public abstract String[] getBars();

    public void setTextView(TextView mTextDialog2) {
        this.mTextDialog = mTextDialog2;
    }

    public BaseSideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public BaseSideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseSideBar(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        float letterHeight = (((float) height) * 1.0f) / ((float) getBars().length);
        for (int i = 0; i < getBars().length; i++) {
            this.paint.setColor(Color.rgb(23, 122, 126));
            this.paint.setTypeface(Typeface.DEFAULT_BOLD);
            this.paint.setAntiAlias(true);
            this.paint.setTextSize(20.0f);
            if (i == this.choose) {
                this.paint.setColor(-16776961);
                this.paint.setFakeBoldText(true);
            }
            canvas.drawText(getBars()[i], (float) ((((double) width) * 0.5d) - (((double) this.paint.measureText(getBars()[i])) * 0.5d)), (((float) i) * letterHeight) + letterHeight, this.paint);
            this.paint.reset();
        }
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float touch_y = event.getY();
        int oldChoose = this.choose;
        OnTouchingLetterChangedListener listener = this.onTouchingLetterChangedListener;
        int c = (int) ((touch_y / ((float) getHeight())) * ((float) getBars().length));
        switch (action) {
            case 1:
                this.choose = -1;
                invalidate();
                if (this.mTextDialog == null) {
                    return true;
                }
                this.mTextDialog.setVisibility(8);
                return true;
            default:
                if (oldChoose == c || c < 0 || c >= getBars().length) {
                    return true;
                }
                if (listener != null) {
                    listener.onTouchingLetterChanged(getBars()[c]);
                }
                if (this.mTextDialog != null) {
                    this.mTextDialog.setText(getBars()[c]);
                    this.mTextDialog.setVisibility(0);
                }
                this.choose = c;
                invalidate();
                return true;
        }
    }

    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener onTouchingLetterChangedListener2) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener2;
    }
}
