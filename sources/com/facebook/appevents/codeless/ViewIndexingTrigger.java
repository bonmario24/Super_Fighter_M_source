package com.facebook.appevents.codeless;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

class ViewIndexingTrigger implements SensorEventListener {
    private static final double SHAKE_THRESHOLD_GRAVITY = 2.299999952316284d;
    private OnShakeListener mListener;

    public interface OnShakeListener {
        void onShake();
    }

    ViewIndexingTrigger() {
    }

    public void setOnShakeListener(OnShakeListener listener) {
        this.mListener = listener;
    }

    public void onSensorChanged(SensorEvent event) {
        if (this.mListener != null) {
            float x = event.values[0];
            float y = event.values[1];
            double gX = (double) (x / 9.80665f);
            double gY = (double) (y / 9.80665f);
            double gZ = (double) (event.values[2] / 9.80665f);
            if (Math.sqrt((gX * gX) + (gY * gY) + (gZ * gZ)) > SHAKE_THRESHOLD_GRAVITY) {
                this.mListener.onShake();
            }
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
