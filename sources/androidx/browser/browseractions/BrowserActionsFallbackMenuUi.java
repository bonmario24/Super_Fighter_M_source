package androidx.browser.browseractions;

import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.browser.C0267R;
import androidx.core.widget.TextViewCompat;
import java.util.List;

class BrowserActionsFallbackMenuUi implements AdapterView.OnItemClickListener {
    private static final String TAG = "BrowserActionskMenuUi";
    private BrowserActionsFallbackMenuDialog mBrowserActionsDialog;
    private final Context mContext;
    private final List<BrowserActionItem> mMenuItems;
    BrowserActionsFallMenuUiListener mMenuUiListener;
    private final Uri mUri;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @VisibleForTesting
    interface BrowserActionsFallMenuUiListener {
        void onMenuShown(View view);
    }

    BrowserActionsFallbackMenuUi(Context context, Uri uri, List<BrowserActionItem> menuItems) {
        this.mContext = context;
        this.mUri = uri;
        this.mMenuItems = menuItems;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setMenuUiListener(BrowserActionsFallMenuUiListener menuUiListener) {
        this.mMenuUiListener = menuUiListener;
    }

    public void displayMenu() {
        final View view = LayoutInflater.from(this.mContext).inflate(C0267R.layout.browser_actions_context_menu_page, (ViewGroup) null);
        this.mBrowserActionsDialog = new BrowserActionsFallbackMenuDialog(this.mContext, initMenuView(view));
        this.mBrowserActionsDialog.setContentView(view);
        if (this.mMenuUiListener != null) {
            this.mBrowserActionsDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                public void onShow(DialogInterface dialogInterface) {
                    BrowserActionsFallbackMenuUi.this.mMenuUiListener.onMenuShown(view);
                }
            });
        }
        this.mBrowserActionsDialog.show();
    }

    private BrowserActionsFallbackMenuView initMenuView(View view) {
        BrowserActionsFallbackMenuView menuView = (BrowserActionsFallbackMenuView) view.findViewById(C0267R.C0270id.browser_actions_menu_view);
        final TextView urlTextView = (TextView) view.findViewById(C0267R.C0270id.browser_actions_header_text);
        urlTextView.setText(this.mUri.toString());
        urlTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (TextViewCompat.getMaxLines(urlTextView) == Integer.MAX_VALUE) {
                    urlTextView.setMaxLines(1);
                    urlTextView.setEllipsize(TextUtils.TruncateAt.END);
                    return;
                }
                urlTextView.setMaxLines(Integer.MAX_VALUE);
                urlTextView.setEllipsize((TextUtils.TruncateAt) null);
            }
        });
        ListView menuListView = (ListView) view.findViewById(C0267R.C0270id.browser_actions_menu_items);
        menuListView.setAdapter(new BrowserActionsFallbackMenuAdapter(this.mMenuItems, this.mContext));
        menuListView.setOnItemClickListener(this);
        return menuView;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        try {
            this.mMenuItems.get(position).getAction().send();
            this.mBrowserActionsDialog.dismiss();
        } catch (PendingIntent.CanceledException e) {
            Log.e(TAG, "Failed to send custom item action", e);
        }
    }
}
