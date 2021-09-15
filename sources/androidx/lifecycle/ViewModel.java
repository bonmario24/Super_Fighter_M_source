package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class ViewModel {
    @Nullable
    private final Map<String, Object> mBagOfTags = new HashMap();
    private volatile boolean mCleared = false;

    /* access modifiers changed from: protected */
    public void onCleared() {
    }

    /* access modifiers changed from: package-private */
    @MainThread
    public final void clear() {
        this.mCleared = true;
        if (this.mBagOfTags != null) {
            synchronized (this.mBagOfTags) {
                for (Object value : this.mBagOfTags.values()) {
                    closeWithRuntimeException(value);
                }
            }
        }
        onCleared();
    }

    /* access modifiers changed from: package-private */
    public <T> T setTagIfAbsent(String key, T newValue) {
        T previous;
        T result;
        synchronized (this.mBagOfTags) {
            previous = this.mBagOfTags.get(key);
            if (previous == null) {
                this.mBagOfTags.put(key, newValue);
            }
        }
        if (previous == null) {
            result = newValue;
        } else {
            result = previous;
        }
        if (this.mCleared) {
            closeWithRuntimeException(result);
        }
        return result;
    }

    /* access modifiers changed from: package-private */
    public <T> T getTag(String key) {
        T t;
        synchronized (this.mBagOfTags) {
            t = this.mBagOfTags.get(key);
        }
        return t;
    }

    private static void closeWithRuntimeException(Object obj) {
        if (obj instanceof Closeable) {
            try {
                ((Closeable) obj).close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
