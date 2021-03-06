package android.support.p000v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RestrictTo;
import android.support.p000v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.app.NotificationCompatBuilder */
class NotificationCompatBuilder implements NotificationBuilderWithBuilderAccessor {
    private final List<Bundle> mActionExtrasList = new ArrayList();
    private RemoteViews mBigContentView;
    private final Notification.Builder mBuilder;
    private final NotificationCompat.Builder mBuilderCompat;
    private RemoteViews mContentView;
    private final Bundle mExtras = new Bundle();
    private int mGroupAlertBehavior;
    private RemoteViews mHeadsUpContentView;

    NotificationCompatBuilder(NotificationCompat.Builder b) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        this.mBuilderCompat = b;
        if (Build.VERSION.SDK_INT >= 26) {
            this.mBuilder = new Notification.Builder(b.mContext, b.mChannelId);
        } else {
            this.mBuilder = new Notification.Builder(b.mContext);
        }
        Notification n = b.mNotification;
        Notification.Builder lights = this.mBuilder.setWhen(n.when).setSmallIcon(n.icon, n.iconLevel).setContent(n.contentView).setTicker(n.tickerText, b.mTickerView).setVibrate(n.vibrate).setLights(n.ledARGB, n.ledOnMS, n.ledOffMS);
        if ((n.flags & 2) != 0) {
            z = true;
        } else {
            z = false;
        }
        Notification.Builder ongoing = lights.setOngoing(z);
        if ((n.flags & 8) != 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        Notification.Builder onlyAlertOnce = ongoing.setOnlyAlertOnce(z2);
        if ((n.flags & 16) != 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        Notification.Builder deleteIntent = onlyAlertOnce.setAutoCancel(z3).setDefaults(n.defaults).setContentTitle(b.mContentTitle).setContentText(b.mContentText).setContentInfo(b.mContentInfo).setContentIntent(b.mContentIntent).setDeleteIntent(n.deleteIntent);
        PendingIntent pendingIntent = b.mFullScreenIntent;
        if ((n.flags & 128) != 0) {
            z4 = true;
        } else {
            z4 = false;
        }
        deleteIntent.setFullScreenIntent(pendingIntent, z4).setLargeIcon(b.mLargeIcon).setNumber(b.mNumber).setProgress(b.mProgressMax, b.mProgress, b.mProgressIndeterminate);
        if (Build.VERSION.SDK_INT < 21) {
            this.mBuilder.setSound(n.sound, n.audioStreamType);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            this.mBuilder.setSubText(b.mSubText).setUsesChronometer(b.mUseChronometer).setPriority(b.mPriority);
            Iterator<NotificationCompat.Action> it = b.mActions.iterator();
            while (it.hasNext()) {
                addAction(it.next());
            }
            if (b.mExtras != null) {
                this.mExtras.putAll(b.mExtras);
            }
            if (Build.VERSION.SDK_INT < 20) {
                if (b.mLocalOnly) {
                    this.mExtras.putBoolean("android.support.localOnly", true);
                }
                if (b.mGroupKey != null) {
                    this.mExtras.putString("android.support.groupKey", b.mGroupKey);
                    if (b.mGroupSummary) {
                        this.mExtras.putBoolean("android.support.isGroupSummary", true);
                    } else {
                        this.mExtras.putBoolean("android.support.useSideChannel", true);
                    }
                }
                if (b.mSortKey != null) {
                    this.mExtras.putString("android.support.sortKey", b.mSortKey);
                }
            }
            this.mContentView = b.mContentView;
            this.mBigContentView = b.mBigContentView;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            this.mBuilder.setShowWhen(b.mShowWhen);
            if (Build.VERSION.SDK_INT < 21 && b.mPeople != null && !b.mPeople.isEmpty()) {
                this.mExtras.putStringArray("android.people", (String[]) b.mPeople.toArray(new String[b.mPeople.size()]));
            }
        }
        if (Build.VERSION.SDK_INT >= 20) {
            this.mBuilder.setLocalOnly(b.mLocalOnly).setGroup(b.mGroupKey).setGroupSummary(b.mGroupSummary).setSortKey(b.mSortKey);
            this.mGroupAlertBehavior = b.mGroupAlertBehavior;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            this.mBuilder.setCategory(b.mCategory).setColor(b.mColor).setVisibility(b.mVisibility).setPublicVersion(b.mPublicVersion).setSound(n.sound, n.audioAttributes);
            Iterator<String> it2 = b.mPeople.iterator();
            while (it2.hasNext()) {
                this.mBuilder.addPerson(it2.next());
            }
            this.mHeadsUpContentView = b.mHeadsUpContentView;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            this.mBuilder.setExtras(b.mExtras).setRemoteInputHistory(b.mRemoteInputHistory);
            if (b.mContentView != null) {
                this.mBuilder.setCustomContentView(b.mContentView);
            }
            if (b.mBigContentView != null) {
                this.mBuilder.setCustomBigContentView(b.mBigContentView);
            }
            if (b.mHeadsUpContentView != null) {
                this.mBuilder.setCustomHeadsUpContentView(b.mHeadsUpContentView);
            }
        }
        if (Build.VERSION.SDK_INT >= 26) {
            this.mBuilder.setBadgeIconType(b.mBadgeIcon).setShortcutId(b.mShortcutId).setTimeoutAfter(b.mTimeout).setGroupAlertBehavior(b.mGroupAlertBehavior);
            if (b.mColorizedSet) {
                this.mBuilder.setColorized(b.mColorized);
            }
            if (!TextUtils.isEmpty(b.mChannelId)) {
                this.mBuilder.setSound((Uri) null).setDefaults(0).setLights(0, 0, 0).setVibrate((long[]) null);
            }
        }
    }

    public Notification.Builder getBuilder() {
        return this.mBuilder;
    }

    public Notification build() {
        Bundle extras;
        RemoteViews styleHeadsUpContentView;
        RemoteViews styleBigContentView;
        NotificationCompat.Style style = this.mBuilderCompat.mStyle;
        if (style != null) {
            style.apply(this);
        }
        RemoteViews styleContentView = style != null ? style.makeContentView(this) : null;
        Notification n = buildInternal();
        if (styleContentView != null) {
            n.contentView = styleContentView;
        } else if (this.mBuilderCompat.mContentView != null) {
            n.contentView = this.mBuilderCompat.mContentView;
        }
        if (!(Build.VERSION.SDK_INT < 16 || style == null || (styleBigContentView = style.makeBigContentView(this)) == null)) {
            n.bigContentView = styleBigContentView;
        }
        if (!(Build.VERSION.SDK_INT < 21 || style == null || (styleHeadsUpContentView = this.mBuilderCompat.mStyle.makeHeadsUpContentView(this)) == null)) {
            n.headsUpContentView = styleHeadsUpContentView;
        }
        if (!(Build.VERSION.SDK_INT < 16 || style == null || (extras = NotificationCompat.getExtras(n)) == null)) {
            style.addCompatExtras(extras);
        }
        return n;
    }

    private void addAction(NotificationCompat.Action action) {
        Bundle actionExtras;
        if (Build.VERSION.SDK_INT >= 20) {
            Notification.Action.Builder actionBuilder = new Notification.Action.Builder(action.getIcon(), action.getTitle(), action.getActionIntent());
            if (action.getRemoteInputs() != null) {
                for (RemoteInput remoteInput : RemoteInput.fromCompat(action.getRemoteInputs())) {
                    actionBuilder.addRemoteInput(remoteInput);
                }
            }
            if (action.getExtras() != null) {
                actionExtras = new Bundle(action.getExtras());
            } else {
                actionExtras = new Bundle();
            }
            actionExtras.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
            if (Build.VERSION.SDK_INT >= 24) {
                actionBuilder.setAllowGeneratedReplies(action.getAllowGeneratedReplies());
            }
            actionBuilder.addExtras(actionExtras);
            this.mBuilder.addAction(actionBuilder.build());
        } else if (Build.VERSION.SDK_INT >= 16) {
            this.mActionExtrasList.add(NotificationCompatJellybean.writeActionAndGetExtras(this.mBuilder, action));
        }
    }

    /* access modifiers changed from: protected */
    public Notification buildInternal() {
        if (Build.VERSION.SDK_INT >= 26) {
            return this.mBuilder.build();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            Notification notification = this.mBuilder.build();
            if (this.mGroupAlertBehavior == 0) {
                return notification;
            }
            if (!(notification.getGroup() == null || (notification.flags & 512) == 0 || this.mGroupAlertBehavior != 2)) {
                removeSoundAndVibration(notification);
            }
            if (notification.getGroup() == null || (notification.flags & 512) != 0 || this.mGroupAlertBehavior != 1) {
                return notification;
            }
            removeSoundAndVibration(notification);
            return notification;
        } else if (Build.VERSION.SDK_INT >= 21) {
            this.mBuilder.setExtras(this.mExtras);
            Notification notification2 = this.mBuilder.build();
            if (this.mContentView != null) {
                notification2.contentView = this.mContentView;
            }
            if (this.mBigContentView != null) {
                notification2.bigContentView = this.mBigContentView;
            }
            if (this.mHeadsUpContentView != null) {
                notification2.headsUpContentView = this.mHeadsUpContentView;
            }
            if (this.mGroupAlertBehavior == 0) {
                return notification2;
            }
            if (!(notification2.getGroup() == null || (notification2.flags & 512) == 0 || this.mGroupAlertBehavior != 2)) {
                removeSoundAndVibration(notification2);
            }
            if (notification2.getGroup() == null || (notification2.flags & 512) != 0 || this.mGroupAlertBehavior != 1) {
                return notification2;
            }
            removeSoundAndVibration(notification2);
            return notification2;
        } else if (Build.VERSION.SDK_INT >= 20) {
            this.mBuilder.setExtras(this.mExtras);
            Notification notification3 = this.mBuilder.build();
            if (this.mContentView != null) {
                notification3.contentView = this.mContentView;
            }
            if (this.mBigContentView != null) {
                notification3.bigContentView = this.mBigContentView;
            }
            if (this.mGroupAlertBehavior == 0) {
                return notification3;
            }
            if (!(notification3.getGroup() == null || (notification3.flags & 512) == 0 || this.mGroupAlertBehavior != 2)) {
                removeSoundAndVibration(notification3);
            }
            if (notification3.getGroup() == null || (notification3.flags & 512) != 0 || this.mGroupAlertBehavior != 1) {
                return notification3;
            }
            removeSoundAndVibration(notification3);
            return notification3;
        } else if (Build.VERSION.SDK_INT >= 19) {
            SparseArray<Bundle> actionExtrasMap = NotificationCompatJellybean.buildActionExtrasMap(this.mActionExtrasList);
            if (actionExtrasMap != null) {
                this.mExtras.putSparseParcelableArray("android.support.actionExtras", actionExtrasMap);
            }
            this.mBuilder.setExtras(this.mExtras);
            Notification notification4 = this.mBuilder.build();
            if (this.mContentView != null) {
                notification4.contentView = this.mContentView;
            }
            if (this.mBigContentView == null) {
                return notification4;
            }
            notification4.bigContentView = this.mBigContentView;
            return notification4;
        } else if (Build.VERSION.SDK_INT < 16) {
            return this.mBuilder.getNotification();
        } else {
            Notification notification5 = this.mBuilder.build();
            Bundle extras = NotificationCompat.getExtras(notification5);
            Bundle mergeBundle = new Bundle(this.mExtras);
            for (String key : this.mExtras.keySet()) {
                if (extras.containsKey(key)) {
                    mergeBundle.remove(key);
                }
            }
            extras.putAll(mergeBundle);
            SparseArray<Bundle> actionExtrasMap2 = NotificationCompatJellybean.buildActionExtrasMap(this.mActionExtrasList);
            if (actionExtrasMap2 != null) {
                NotificationCompat.getExtras(notification5).putSparseParcelableArray("android.support.actionExtras", actionExtrasMap2);
            }
            if (this.mContentView != null) {
                notification5.contentView = this.mContentView;
            }
            if (this.mBigContentView == null) {
                return notification5;
            }
            notification5.bigContentView = this.mBigContentView;
            return notification5;
        }
    }

    private void removeSoundAndVibration(Notification notification) {
        notification.sound = null;
        notification.vibrate = null;
        notification.defaults &= -2;
        notification.defaults &= -3;
    }
}
