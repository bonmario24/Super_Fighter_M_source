package com.xhuyu.component.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import com.lzy.okgo.OkGo;
import com.xhuyu.component.utils.ResourceUtil;
import java.util.Timer;
import java.util.TimerTask;

public class CountDownTimerButton extends Button implements View.OnClickListener {
    /* access modifiers changed from: private */
    public String clickBeffor;
    private long duration;
    private Context mContext;
    /* access modifiers changed from: private */
    public Handler mHandler;
    private View.OnClickListener mOnClickListener;
    private TimerTask mTask;
    private Timer mTimer;
    /* access modifiers changed from: private */
    public long temp_duration;

    public CountDownTimerButton(Context context) {
        this(context, (AttributeSet) null);
        setText(this.clickBeffor);
    }

    public CountDownTimerButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        setText(this.clickBeffor);
    }

    public CountDownTimerButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.duration = OkGo.DEFAULT_MILLISECONDS;
        this.clickBeffor = ResourceUtil.getString("huyu_tip_get_msm_code");
        this.mHandler = new Handler(new Handler.Callback() {
            public boolean handleMessage(@NonNull Message msg) {
                try {
                    CountDownTimerButton.this.setText(ResourceUtil.getString("huyu_resend") + "(" + (CountDownTimerButton.this.temp_duration / 1000) + ")");
                    long unused = CountDownTimerButton.this.temp_duration = CountDownTimerButton.this.temp_duration - 1000;
                    if (CountDownTimerButton.this.temp_duration >= 0) {
                        return false;
                    }
                    CountDownTimerButton.this.setEnabled(true);
                    CountDownTimerButton.this.setText(CountDownTimerButton.this.clickBeffor);
                    CountDownTimerButton.this.stopTimer();
                    return false;
                } catch (Exception e) {
                    return false;
                }
            }
        });
        this.mContext = context;
        setText(this.clickBeffor);
        setOnClickListener(this);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        if (onClickListener instanceof CountDownTimerButton) {
            super.setOnClickListener(onClickListener);
        } else {
            this.mOnClickListener = onClickListener;
        }
    }

    public void onClick(View view) {
        if (this.mOnClickListener != null) {
            this.mOnClickListener.onClick(view);
        }
    }

    public void startTimer() {
        if (isEnabled()) {
            this.temp_duration = this.duration;
            setEnabled(false);
            this.mTimer = new Timer();
            this.mTask = new TimerTask() {
                public void run() {
                    CountDownTimerButton.this.mHandler.sendEmptyMessage(1);
                }
            };
            this.mTimer.schedule(this.mTask, 0, 1000);
            setEnabled(false);
        }
    }

    public void stopTimer() {
        if (this.mTask != null) {
            this.mTask.cancel();
            this.mTask = null;
        }
        if (this.mTimer != null) {
            this.mTimer.cancel();
            this.mTimer = null;
        }
        setEnabled(true);
    }
}
