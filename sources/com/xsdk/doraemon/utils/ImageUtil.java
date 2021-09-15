package com.xsdk.doraemon.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

public class ImageUtil {
    public static Bitmap getRoundBitmapByShader(Bitmap bitmap, int outWidth, int outHeight, int radius, int boarder, boolean topLeftRightCorner, boolean bottomLeftRightCorner) {
        return getRoundBitmapByShader(bitmap, outWidth, outHeight, radius, boarder, -16711936, topLeftRightCorner, bottomLeftRightCorner);
    }

    public static Bitmap getRoundBitmapByShader(Bitmap bitmap, int outWidth, int outHeight, int radius, int boarder, int color, boolean topLeftRightCorner, boolean bottomLeftRightCorner) {
        if (bitmap == null) {
            return null;
        }
        int height = bitmap.getHeight();
        float widthScale = (((float) outWidth) * 1.0f) / ((float) bitmap.getWidth());
        Matrix matrix = new Matrix();
        matrix.setScale(widthScale, (((float) outHeight) * 1.0f) / ((float) height));
        Bitmap desBitmap = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(desBitmap);
        Paint paint = new Paint(1);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        RectF rect = new RectF((float) boarder, (float) boarder, (float) (outWidth - boarder), (float) (outHeight - boarder));
        canvas.drawRoundRect(rect, (float) radius, (float) radius, paint);
        if (topLeftRightCorner) {
            canvas.drawRect(0.0f, (float) (canvas.getHeight() - radius), (float) radius, (float) canvas.getHeight(), paint);
            canvas.drawRect((float) (canvas.getWidth() - radius), (float) (canvas.getHeight() - radius), (float) canvas.getWidth(), (float) canvas.getHeight(), paint);
        }
        if (bottomLeftRightCorner) {
            canvas.drawRect(0.0f, 0.0f, (float) radius, (float) radius, paint);
            canvas.drawRect((float) (canvas.getWidth() - radius), 0.0f, (float) canvas.getWidth(), (float) radius, paint);
        }
        if (boarder <= 0) {
            return desBitmap;
        }
        Paint boarderPaint = new Paint(1);
        boarderPaint.setColor(color);
        boarderPaint.setStyle(Paint.Style.STROKE);
        boarderPaint.setStrokeWidth((float) boarder);
        canvas.drawRoundRect(rect, (float) radius, (float) radius, boarderPaint);
        return desBitmap;
    }

    public static Bitmap getCircleBitmapByShader(Bitmap bitmap, int outWidth, int outHeight, int boarder) {
        return getCircleBitmapByShader(bitmap, outWidth, outHeight, boarder, -16711936);
    }

    public static Bitmap getCircleBitmapByShader(Bitmap bitmap, int outWidth, int outHeight, int boarder, int color) {
        int radius;
        float widthScale = (((float) outWidth) * 1.0f) / ((float) bitmap.getWidth());
        float heightScale = (((float) outHeight) * 1.0f) / ((float) bitmap.getHeight());
        Bitmap desBitmap = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888);
        if (outHeight > outWidth) {
            radius = outWidth / 2;
        } else {
            radius = outHeight / 2;
        }
        Canvas canvas = new Canvas(desBitmap);
        Paint paint = new Paint(1);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Matrix matrix = new Matrix();
        matrix.setScale(widthScale, heightScale);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        canvas.drawCircle((float) (outWidth / 2), (float) (outHeight / 2), (float) (radius - boarder), paint);
        if (boarder > 0) {
            Paint boarderPaint = new Paint(1);
            boarderPaint.setColor(color);
            boarderPaint.setStyle(Paint.Style.STROKE);
            boarderPaint.setStrokeWidth((float) boarder);
            canvas.drawCircle((float) (outWidth / 2), (float) (outHeight / 2), (float) (radius - boarder), boarderPaint);
        }
        return desBitmap;
    }
}
