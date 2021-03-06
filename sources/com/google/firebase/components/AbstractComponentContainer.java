package com.google.firebase.components;

import com.google.firebase.inject.Provider;
import java.util.Set;

/* compiled from: com.google.firebase:firebase-components@@16.0.0 */
abstract class AbstractComponentContainer implements ComponentContainer {
    AbstractComponentContainer() {
    }

    public <T> T get(Class<T> anInterface) {
        Provider<T> provider = getProvider(anInterface);
        if (provider == null) {
            return null;
        }
        return provider.get();
    }

    public <T> Set<T> setOf(Class<T> anInterface) {
        return setOfProvider(anInterface).get();
    }
}
