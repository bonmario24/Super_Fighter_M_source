package com.facebook.share.internal;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import com.facebook.FacebookButtonBase;
import com.facebook.common.C1278R;
import com.facebook.internal.AnalyticsEvents;

@Deprecated
public class LikeButton extends FacebookButtonBase {
    @Deprecated
    public LikeButton(Context context, boolean isLiked) {
        super(context, (AttributeSet) null, 0, 0, AnalyticsEvents.EVENT_LIKE_BUTTON_CREATE, AnalyticsEvents.EVENT_LIKE_BUTTON_DID_TAP);
        setSelected(isLiked);
    }

    @Deprecated
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        updateForLikeStatus();
    }

    /* access modifiers changed from: protected */
    public void configureButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super.configureButton(context, attrs, defStyleAttr, defStyleRes);
        updateForLikeStatus();
    }

    /* access modifiers changed from: protected */
    public int getDefaultRequestCode() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getDefaultStyleResource() {
        return C1278R.style.com_facebook_button_like;
    }

    private void updateForLikeStatus() {
        if (isSelected()) {
            setCompoundDrawablesWithIntrinsicBounds(C1278R.C1280drawable.com_facebook_button_like_icon_selected, 0, 0, 0);
            setText(getResources().getString(C1278R.string.com_facebook_like_button_liked));
            return;
        }
        setCompoundDrawablesWithIntrinsicBounds(AppCompatResources.getDrawable(getContext(), C1278R.C1280drawable.com_facebook_button_icon), (Drawable) null, (Drawable) null, (Drawable) null);
        setText(getResources().getString(C1278R.string.com_facebook_like_button_not_liked));
    }
}
