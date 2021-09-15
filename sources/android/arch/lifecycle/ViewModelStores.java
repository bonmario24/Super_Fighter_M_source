package android.arch.lifecycle;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentActivity;

public class ViewModelStores {
    private ViewModelStores() {
    }

    @MainThread
    @NonNull
    /* renamed from: of */
    public static ViewModelStore m195of(@NonNull FragmentActivity activity) {
        if (activity instanceof ViewModelStoreOwner) {
            return activity.getViewModelStore();
        }
        return HolderFragment.holderFragmentFor(activity).getViewModelStore();
    }

    @MainThread
    @NonNull
    /* renamed from: of */
    public static ViewModelStore m194of(@NonNull Fragment fragment) {
        if (fragment instanceof ViewModelStoreOwner) {
            return fragment.getViewModelStore();
        }
        return HolderFragment.holderFragmentFor(fragment).getViewModelStore();
    }
}
