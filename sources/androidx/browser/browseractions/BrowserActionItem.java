package androidx.browser.browseractions;

import android.app.PendingIntent;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

public class BrowserActionItem {
    private final PendingIntent mAction;
    @DrawableRes
    private final int mIconId;
    private final String mTitle;

    public BrowserActionItem(@NonNull String title, @NonNull PendingIntent action, @DrawableRes int iconId) {
        this.mTitle = title;
        this.mAction = action;
        this.mIconId = iconId;
    }

    public BrowserActionItem(@NonNull String title, @NonNull PendingIntent action) {
        this(title, action, 0);
    }

    public int getIconId() {
        return this.mIconId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public PendingIntent getAction() {
        return this.mAction;
    }
}
