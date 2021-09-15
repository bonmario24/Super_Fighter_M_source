package androidx.savedstate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.savedstate.Recreator;
import java.util.Iterator;
import java.util.Map;

@SuppressLint({"RestrictedApi"})
public final class SavedStateRegistry {
    private static final String SAVED_COMPONENTS_KEY = "androidx.lifecycle.BundlableSavedStateRegistry.key";
    boolean mAllowingSavingState = true;
    private SafeIterableMap<String, SavedStateProvider> mComponents = new SafeIterableMap<>();
    private Recreator.SavedStateProvider mRecreatorProvider;
    private boolean mRestored;
    @Nullable
    private Bundle mRestoredState;

    public interface AutoRecreated {
        void onRecreated(@NonNull SavedStateRegistryOwner savedStateRegistryOwner);
    }

    public interface SavedStateProvider {
        @NonNull
        Bundle saveState();
    }

    SavedStateRegistry() {
    }

    @MainThread
    @Nullable
    public Bundle consumeRestoredStateForKey(@NonNull String key) {
        if (!this.mRestored) {
            throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
        } else if (this.mRestoredState == null) {
            return null;
        } else {
            Bundle result = this.mRestoredState.getBundle(key);
            this.mRestoredState.remove(key);
            if (!this.mRestoredState.isEmpty()) {
                return result;
            }
            this.mRestoredState = null;
            return result;
        }
    }

    @MainThread
    public void registerSavedStateProvider(@NonNull String key, @NonNull SavedStateProvider provider) {
        if (this.mComponents.putIfAbsent(key, provider) != null) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
        }
    }

    @MainThread
    public void unregisterSavedStateProvider(@NonNull String key) {
        this.mComponents.remove(key);
    }

    @MainThread
    public boolean isRestored() {
        return this.mRestored;
    }

    @MainThread
    public void runOnNextRecreation(@NonNull Class<? extends AutoRecreated> clazz) {
        if (!this.mAllowingSavingState) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if (this.mRecreatorProvider == null) {
            this.mRecreatorProvider = new Recreator.SavedStateProvider(this);
        }
        try {
            clazz.getDeclaredConstructor(new Class[0]);
            this.mRecreatorProvider.add(clazz.getName());
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Class" + clazz.getSimpleName() + " must have default constructor in order to be automatically recreated", e);
        }
    }

    /* access modifiers changed from: package-private */
    @MainThread
    public void performRestore(@NonNull Lifecycle lifecycle, @Nullable Bundle savedState) {
        if (this.mRestored) {
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
        if (savedState != null) {
            this.mRestoredState = savedState.getBundle(SAVED_COMPONENTS_KEY);
        }
        lifecycle.addObserver(new GenericLifecycleObserver() {
            public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_START) {
                    SavedStateRegistry.this.mAllowingSavingState = true;
                } else if (event == Lifecycle.Event.ON_STOP) {
                    SavedStateRegistry.this.mAllowingSavingState = false;
                }
            }
        });
        this.mRestored = true;
    }

    /* access modifiers changed from: package-private */
    @MainThread
    public void performSave(@NonNull Bundle outBundle) {
        Bundle components = new Bundle();
        if (this.mRestoredState != null) {
            components.putAll(this.mRestoredState);
        }
        Iterator<Map.Entry<String, SavedStateProvider>> it = this.mComponents.iteratorWithAdditions();
        while (it.hasNext()) {
            Map.Entry<String, SavedStateProvider> entry1 = it.next();
            components.putBundle(entry1.getKey(), entry1.getValue().saveState());
        }
        outBundle.putBundle(SAVED_COMPONENTS_KEY, components);
    }
}
