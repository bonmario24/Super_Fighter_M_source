package com.unity3d.player;

import java.lang.reflect.Method;
import java.util.HashMap;

/* renamed from: com.unity3d.player.m */
final class C0784m {

    /* renamed from: a */
    private HashMap f269a = new HashMap();

    /* renamed from: b */
    private Class f270b = null;

    /* renamed from: c */
    private Object f271c = null;

    /* renamed from: com.unity3d.player.m$a */
    class C0785a {

        /* renamed from: a */
        public Class[] f272a;

        /* renamed from: b */
        public Method f273b = null;

        public C0785a(Class[] clsArr) {
            this.f272a = clsArr;
        }
    }

    public C0784m(Class cls, Object obj) {
        this.f270b = cls;
        this.f271c = obj;
    }

    /* renamed from: a */
    private void m155a(String str, C0785a aVar) {
        try {
            aVar.f273b = this.f270b.getMethod(str, aVar.f272a);
        } catch (Exception e) {
            C0771e.Log(6, "Exception while trying to get method " + str + ". " + e.getLocalizedMessage());
            aVar.f273b = null;
        }
    }

    /* renamed from: a */
    public final Object mo12525a(String str, Object... objArr) {
        Object obj;
        if (!this.f269a.containsKey(str)) {
            C0771e.Log(6, "No definition for method " + str + " can be found");
            return null;
        }
        C0785a aVar = (C0785a) this.f269a.get(str);
        if (aVar.f273b == null) {
            m155a(str, aVar);
        }
        if (aVar.f273b == null) {
            C0771e.Log(6, "Unable to create method: " + str);
            return null;
        }
        try {
            obj = objArr.length == 0 ? aVar.f273b.invoke(this.f271c, new Object[0]) : aVar.f273b.invoke(this.f271c, objArr);
        } catch (Exception e) {
            C0771e.Log(6, "Error trying to call delegated method " + str + ". " + e.getLocalizedMessage());
            obj = null;
        }
        return obj;
    }

    /* renamed from: a */
    public final void mo12526a(String str, Class[] clsArr) {
        this.f269a.put(str, new C0785a(clsArr));
    }
}
