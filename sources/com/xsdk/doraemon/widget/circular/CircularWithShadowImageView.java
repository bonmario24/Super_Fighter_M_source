package com.xsdk.doraemon.widget.circular;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class CircularWithShadowImageView extends ImageView {
    private int borderWidth = 4;
    private Bitmap image;
    private Paint paint;
    private Paint paintBorder;
    private BitmapShader shader;
    private int viewHeight;
    private int viewWidth;

    public CircularWithShadowImageView(Context context) {
        super(context);
        setup();
    }

    public CircularWithShadowImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public CircularWithShadowImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setup();
    }

    private void setup() {
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paintBorder = new Paint();
        setBorderColor(-1);
        this.paintBorder.setAntiAlias(true);
        setLayerType(1, this.paintBorder);
        this.paintBorder.setShadowLayer(2.0f, 0.0f, 1.0f, Color.parseColor("#89cdcdcd"));
    }

    public void setBorderWidth(int borderWidth2) {
        this.borderWidth = borderWidth2;
        invalidate();
    }

    public void setBorderColor(int borderColor) {
        if (this.paintBorder != null) {
            this.paintBorder.setColor(borderColor);
        }
        invalidate();
    }

    private void loadBitmap() {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getDrawable();
        if (bitmapDrawable != null) {
            this.image = bitmapDrawable.getBitmap();
        }
    }

    @SuppressLint({"DrawAllocation"})
    public void onDraw(Canvas canvas) {
        loadBitmap();
        if (this.image != null) {
            this.shader = new BitmapShader(Bitmap.createScaledBitmap(this.image, canvas.getWidth(), canvas.getHeight(), false), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            this.paint.setShader(this.shader);
            int circleCenter = this.viewWidth / 2;
            canvas.drawCircle((float) (this.borderWidth + circleCenter), (float) (this.borderWidth + circleCenter), ((float) (this.borderWidth + circleCenter)) - 4.0f, this.paintBorder);
            canvas.drawCircle((float) (this.borderWidth + circleCenter), (float) (this.borderWidth + circleCenter), ((float) circleCenter) - 4.0f, this.paint);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec, widthMeasureSpec);
        this.viewWidth = width - (this.borderWidth * 2);
        this.viewHeight = height - (this.borderWidth * 2);
        setMeasuredDimension(width, height);
    }

    private int measureWidth(int measureSpec) {
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);
        if (specMode == 1073741824) {
            return specSize;
        }
        return this.viewWidth;
    }

    private int measureHeight(int measureSpecHeight, int measureSpecWidth) {
        int result;
        int specMode = View.MeasureSpec.getMode(measureSpecHeight);
        int specSize = View.MeasureSpec.getSize(measureSpecHeight);
        if (specMode == 1073741824) {
            result = specSize;
        } else {
            result = this.viewHeight;
        }
        return result + 2;
    }
}
