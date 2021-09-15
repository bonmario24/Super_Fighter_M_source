package bitter.jnibridge;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JNIBridge {

    /* renamed from: bitter.jnibridge.JNIBridge$a */
    private static class C0887a implements InvocationHandler {

        /* renamed from: a */
        private Object f333a = new Object[0];

        /* renamed from: b */
        private long f334b;

        public C0887a(long j) {
            this.f334b = j;
        }

        /* renamed from: a */
        public final void mo14089a() {
            synchronized (this.f333a) {
                this.f334b = 0;
            }
        }

        public final void finalize() {
            synchronized (this.f333a) {
                if (this.f334b != 0) {
                    JNIBridge.delete(this.f334b);
                }
            }
        }

        public final Object invoke(Object obj, Method method, Object[] objArr) {
            Object invoke;
            synchronized (this.f333a) {
                invoke = this.f334b == 0 ? null : JNIBridge.invoke(this.f334b, method.getDeclaringClass(), method, objArr);
            }
            return invoke;
        }
    }

    static native void delete(long j);

    static void disableInterfaceProxy(Object obj) {
        ((C0887a) Proxy.getInvocationHandler(obj)).mo14089a();
    }

    static native Object invoke(long j, Class cls, Method method, Object[] objArr);

    static Object newInterfaceProxy(long j, Class[] clsArr) {
        return Proxy.newProxyInstance(JNIBridge.class.getClassLoader(), clsArr, new C0887a(j));
    }
}
