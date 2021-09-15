package com.google.android.gms.common.data;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.ArrayList;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public abstract class EntityBuffer<T> extends AbstractDataBuffer<T> {
    private boolean zamh = false;
    private ArrayList<Integer> zami;

    @KeepForSdk
    protected EntityBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract T getEntry(int i, int i2);

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract String getPrimaryDataMarkerColumn();

    @KeepForSdk
    public final T get(int i) {
        int i2;
        zabz();
        int zah = zah(i);
        if (i < 0 || i == this.zami.size()) {
            i2 = 0;
        } else {
            if (i == this.zami.size() - 1) {
                i2 = this.mDataHolder.getCount() - this.zami.get(i).intValue();
            } else {
                i2 = this.zami.get(i + 1).intValue() - this.zami.get(i).intValue();
            }
            if (i2 == 1) {
                int zah2 = zah(i);
                int windowIndex = this.mDataHolder.getWindowIndex(zah2);
                String childDataMarkerColumn = getChildDataMarkerColumn();
                if (childDataMarkerColumn != null && this.mDataHolder.getString(childDataMarkerColumn, zah2, windowIndex) == null) {
                    i2 = 0;
                }
            }
        }
        return getEntry(zah, i2);
    }

    @KeepForSdk
    public int getCount() {
        zabz();
        return this.zami.size();
    }

    private final void zabz() {
        synchronized (this) {
            if (!this.zamh) {
                int count = this.mDataHolder.getCount();
                this.zami = new ArrayList<>();
                if (count > 0) {
                    this.zami.add(0);
                    String primaryDataMarkerColumn = getPrimaryDataMarkerColumn();
                    String string = this.mDataHolder.getString(primaryDataMarkerColumn, 0, this.mDataHolder.getWindowIndex(0));
                    int i = 1;
                    while (i < count) {
                        int windowIndex = this.mDataHolder.getWindowIndex(i);
                        String string2 = this.mDataHolder.getString(primaryDataMarkerColumn, i, windowIndex);
                        if (string2 == null) {
                            throw new NullPointerException(new StringBuilder(String.valueOf(primaryDataMarkerColumn).length() + 78).append("Missing value for markerColumn: ").append(primaryDataMarkerColumn).append(", at row: ").append(i).append(", for window: ").append(windowIndex).toString());
                        }
                        if (!string2.equals(string)) {
                            this.zami.add(Integer.valueOf(i));
                        } else {
                            string2 = string;
                        }
                        i++;
                        string = string2;
                    }
                }
                this.zamh = true;
            }
        }
    }

    private final int zah(int i) {
        if (i >= 0 && i < this.zami.size()) {
            return this.zami.get(i).intValue();
        }
        throw new IllegalArgumentException(new StringBuilder(53).append("Position ").append(i).append(" is out of bounds for this buffer").toString());
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public String getChildDataMarkerColumn() {
        return null;
    }
}
