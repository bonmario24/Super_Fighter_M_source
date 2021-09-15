package androidx.core.p005os;

import android.os.Parcel;
import androidx.annotation.NonNull;

/* renamed from: androidx.core.os.ParcelCompat */
public final class ParcelCompat {
    public static boolean readBoolean(@NonNull Parcel in) {
        return in.readInt() != 0;
    }

    public static void writeBoolean(@NonNull Parcel out, boolean value) {
        out.writeInt(value ? 1 : 0);
    }

    private ParcelCompat() {
    }
}
