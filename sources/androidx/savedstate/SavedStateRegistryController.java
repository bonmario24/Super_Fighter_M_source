package androidx.savedstate;

import android.os.Bundle;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;

public final class SavedStateRegistryController {
    private final SavedStateRegistryOwner mOwner;
    private final SavedStateRegistry mRegistry = new SavedStateRegistry();

    private SavedStateRegistryController(SavedStateRegistryOwner owner) {
        this.mOwner = owner;
    }

    @NonNull
    public SavedStateRegistry getSavedStateRegistry() {
        return this.mRegistry;
    }

    @MainThread
    public void performRestore(@Nullable Bundle savedState) {
        Lifecycle lifecycle = this.mOwner.getLifecycle();
        if (lifecycle.getCurrentState() != Lifecycle.State.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        lifecycle.addObserver(new Recreator(this.mOwner));
        this.mRegistry.performRestore(lifecycle, savedState);
    }

    @MainThread
    public void performSave(@NonNull Bundle outBundle) {
        this.mRegistry.performSave(outBundle);
    }

    @NonNull
    public static SavedStateRegistryController create(@NonNull SavedStateRegistryOwner owner) {
        return new SavedStateRegistryController(owner);
    }
}
