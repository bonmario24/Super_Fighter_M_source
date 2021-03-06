package com.unity3d.player;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

/* renamed from: com.unity3d.player.g */
public final class C0773g extends Fragment {

    /* renamed from: a */
    private final Runnable f240a;

    public C0773g() {
        this.f240a = null;
    }

    public C0773g(Runnable runnable) {
        this.f240a = runnable;
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.f240a == null) {
            getFragmentManager().beginTransaction().remove(this).commit();
        } else {
            requestPermissions(getArguments().getStringArray("PermissionNames"), 15881);
        }
    }

    public final void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 15881) {
            int i2 = 0;
            while (i2 < strArr.length && i2 < iArr.length) {
                C0771e.Log(4, strArr[i2] + (iArr[i2] == 0 ? " granted" : " denied"));
                i2++;
            }
            FragmentTransaction beginTransaction = getActivity().getFragmentManager().beginTransaction();
            beginTransaction.remove(this);
            beginTransaction.commit();
            this.f240a.run();
        }
    }
}
