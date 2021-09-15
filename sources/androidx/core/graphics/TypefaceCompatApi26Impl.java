package androidx.core.graphics;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.FontResourcesParserCompat;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

@RequiresApi(26)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl {
    private static final String ABORT_CREATION_METHOD = "abortCreation";
    private static final String ADD_FONT_FROM_ASSET_MANAGER_METHOD = "addFontFromAssetManager";
    private static final String ADD_FONT_FROM_BUFFER_METHOD = "addFontFromBuffer";
    private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
    private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
    private static final String FREEZE_METHOD = "freeze";
    private static final int RESOLVE_BY_FONT_TABLE = -1;
    private static final String TAG = "TypefaceCompatApi26Impl";
    protected final Method mAbortCreation;
    protected final Method mAddFontFromAssetManager;
    protected final Method mAddFontFromBuffer;
    protected final Method mCreateFromFamiliesWithDefault;
    protected final Class mFontFamily;
    protected final Constructor mFontFamilyCtor;
    protected final Method mFreeze;

    public TypefaceCompatApi26Impl() {
        Class fontFamily;
        Constructor fontFamilyCtor;
        Method addFontFromAssetManager;
        Method addFontFromBuffer;
        Method freeze;
        Method abortCreation;
        Method createFromFamiliesWithDefault;
        try {
            fontFamily = obtainFontFamily();
            fontFamilyCtor = obtainFontFamilyCtor(fontFamily);
            addFontFromAssetManager = obtainAddFontFromAssetManagerMethod(fontFamily);
            addFontFromBuffer = obtainAddFontFromBufferMethod(fontFamily);
            freeze = obtainFreezeMethod(fontFamily);
            abortCreation = obtainAbortCreationMethod(fontFamily);
            createFromFamiliesWithDefault = obtainCreateFromFamiliesWithDefaultMethod(fontFamily);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e(TAG, "Unable to collect necessary methods for class " + e.getClass().getName(), e);
            fontFamily = null;
            fontFamilyCtor = null;
            addFontFromAssetManager = null;
            addFontFromBuffer = null;
            freeze = null;
            abortCreation = null;
            createFromFamiliesWithDefault = null;
        }
        this.mFontFamily = fontFamily;
        this.mFontFamilyCtor = fontFamilyCtor;
        this.mAddFontFromAssetManager = addFontFromAssetManager;
        this.mAddFontFromBuffer = addFontFromBuffer;
        this.mFreeze = freeze;
        this.mAbortCreation = abortCreation;
        this.mCreateFromFamiliesWithDefault = createFromFamiliesWithDefault;
    }

    private boolean isFontFamilyPrivateAPIAvailable() {
        if (this.mAddFontFromAssetManager == null) {
            Log.w(TAG, "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        return this.mAddFontFromAssetManager != null;
    }

    @Nullable
    private Object newFamily() {
        try {
            return this.mFontFamilyCtor.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            return null;
        }
    }

    private boolean addFontFromAssetManager(Context context, Object family, String fileName, int ttcIndex, int weight, int style, @Nullable FontVariationAxis[] axes) {
        try {
            return ((Boolean) this.mAddFontFromAssetManager.invoke(family, new Object[]{context.getAssets(), fileName, 0, false, Integer.valueOf(ttcIndex), Integer.valueOf(weight), Integer.valueOf(style), axes})).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            return false;
        }
    }

    private boolean addFontFromBuffer(Object family, ByteBuffer buffer, int ttcIndex, int weight, int style) {
        try {
            return ((Boolean) this.mAddFontFromBuffer.invoke(family, new Object[]{buffer, Integer.valueOf(ttcIndex), null, Integer.valueOf(weight), Integer.valueOf(style)})).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public Typeface createFromFamiliesWithDefault(Object family) {
        try {
            Object familyArray = Array.newInstance(this.mFontFamily, 1);
            Array.set(familyArray, 0, family);
            return (Typeface) this.mCreateFromFamiliesWithDefault.invoke((Object) null, new Object[]{familyArray, -1, -1});
        } catch (IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }

    private boolean freeze(Object family) {
        try {
            return ((Boolean) this.mFreeze.invoke(family, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            return false;
        }
    }

    private void abortCreation(Object family) {
        try {
            this.mAbortCreation.invoke(family, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException e) {
        }
    }

    @Nullable
    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry entry, Resources resources, int style) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromFontFamilyFilesResourceEntry(context, entry, resources, style);
        }
        Object fontFamily = newFamily();
        if (fontFamily == null) {
            return null;
        }
        for (FontResourcesParserCompat.FontFileResourceEntry fontFile : entry.getEntries()) {
            if (!addFontFromAssetManager(context, fontFamily, fontFile.getFileName(), fontFile.getTtcIndex(), fontFile.getWeight(), fontFile.isItalic() ? 1 : 0, FontVariationAxis.fromFontVariationSettings(fontFile.getVariationSettings()))) {
                abortCreation(fontFamily);
                return null;
            }
        }
        if (!freeze(fontFamily)) {
            return null;
        }
        return createFromFamiliesWithDefault(fontFamily);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0070, code lost:
        r6 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0071, code lost:
        r7 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0107, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0108, code lost:
        r6 = r3;
     */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r21, @androidx.annotation.Nullable android.os.CancellationSignal r22, @androidx.annotation.NonNull androidx.core.provider.FontsContractCompat.FontInfo[] r23, int r24) {
        /*
            r20 = this;
            r0 = r23
            int r3 = r0.length
            r6 = 1
            if (r3 >= r6) goto L_0x0008
            r3 = 0
        L_0x0007:
            return r3
        L_0x0008:
            boolean r3 = r20.isFontFamilyPrivateAPIAvailable()
            if (r3 != 0) goto L_0x0083
            r0 = r20
            r1 = r23
            r2 = r24
            androidx.core.provider.FontsContractCompat$FontInfo r10 = r0.findBestInfo(r1, r2)
            android.content.ContentResolver r14 = r21.getContentResolver()
            android.net.Uri r3 = r10.getUri()     // Catch:{ IOException -> 0x0039 }
            java.lang.String r6 = "r"
            r0 = r22
            android.os.ParcelFileDescriptor r13 = r14.openFileDescriptor(r3, r6, r0)     // Catch:{ IOException -> 0x0039 }
            r7 = 0
            if (r13 != 0) goto L_0x0040
            r3 = 0
            if (r13 == 0) goto L_0x0007
            if (r7 == 0) goto L_0x003c
            r13.close()     // Catch:{ Throwable -> 0x0034 }
            goto L_0x0007
        L_0x0034:
            r6 = move-exception
            r7.addSuppressed(r6)     // Catch:{ IOException -> 0x0039 }
            goto L_0x0007
        L_0x0039:
            r11 = move-exception
            r3 = 0
            goto L_0x0007
        L_0x003c:
            r13.close()     // Catch:{ IOException -> 0x0039 }
            goto L_0x0007
        L_0x0040:
            android.graphics.Typeface$Builder r3 = new android.graphics.Typeface$Builder     // Catch:{ Throwable -> 0x006e, all -> 0x0107 }
            java.io.FileDescriptor r6 = r13.getFileDescriptor()     // Catch:{ Throwable -> 0x006e, all -> 0x0107 }
            r3.<init>(r6)     // Catch:{ Throwable -> 0x006e, all -> 0x0107 }
            int r6 = r10.getWeight()     // Catch:{ Throwable -> 0x006e, all -> 0x0107 }
            android.graphics.Typeface$Builder r3 = r3.setWeight(r6)     // Catch:{ Throwable -> 0x006e, all -> 0x0107 }
            boolean r6 = r10.isItalic()     // Catch:{ Throwable -> 0x006e, all -> 0x0107 }
            android.graphics.Typeface$Builder r3 = r3.setItalic(r6)     // Catch:{ Throwable -> 0x006e, all -> 0x0107 }
            android.graphics.Typeface r3 = r3.build()     // Catch:{ Throwable -> 0x006e, all -> 0x0107 }
            if (r13 == 0) goto L_0x0007
            if (r7 == 0) goto L_0x006a
            r13.close()     // Catch:{ Throwable -> 0x0065 }
            goto L_0x0007
        L_0x0065:
            r6 = move-exception
            r7.addSuppressed(r6)     // Catch:{ IOException -> 0x0039 }
            goto L_0x0007
        L_0x006a:
            r13.close()     // Catch:{ IOException -> 0x0039 }
            goto L_0x0007
        L_0x006e:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0070 }
        L_0x0070:
            r6 = move-exception
            r7 = r3
        L_0x0072:
            if (r13 == 0) goto L_0x0079
            if (r7 == 0) goto L_0x007f
            r13.close()     // Catch:{ Throwable -> 0x007a }
        L_0x0079:
            throw r6     // Catch:{ IOException -> 0x0039 }
        L_0x007a:
            r3 = move-exception
            r7.addSuppressed(r3)     // Catch:{ IOException -> 0x0039 }
            goto L_0x0079
        L_0x007f:
            r13.close()     // Catch:{ IOException -> 0x0039 }
            goto L_0x0079
        L_0x0083:
            r0 = r21
            r1 = r23
            r2 = r22
            java.util.Map r17 = androidx.core.provider.FontsContractCompat.prepareFontData(r0, r1, r2)
            java.lang.Object r4 = r20.newFamily()
            if (r4 != 0) goto L_0x0096
            r3 = 0
            goto L_0x0007
        L_0x0096:
            r9 = 0
            r0 = r23
            int r0 = r0.length
            r19 = r0
            r3 = 0
            r18 = r3
        L_0x009f:
            r0 = r18
            r1 = r19
            if (r0 >= r1) goto L_0x00dd
            r12 = r23[r18]
            android.net.Uri r3 = r12.getUri()
            r0 = r17
            java.lang.Object r5 = r0.get(r3)
            java.nio.ByteBuffer r5 = (java.nio.ByteBuffer) r5
            if (r5 != 0) goto L_0x00ba
        L_0x00b5:
            int r3 = r18 + 1
            r18 = r3
            goto L_0x009f
        L_0x00ba:
            int r6 = r12.getTtcIndex()
            int r7 = r12.getWeight()
            boolean r3 = r12.isItalic()
            if (r3 == 0) goto L_0x00d9
            r8 = 1
        L_0x00c9:
            r3 = r20
            boolean r15 = r3.addFontFromBuffer(r4, r5, r6, r7, r8)
            if (r15 != 0) goto L_0x00db
            r0 = r20
            r0.abortCreation(r4)
            r3 = 0
            goto L_0x0007
        L_0x00d9:
            r8 = 0
            goto L_0x00c9
        L_0x00db:
            r9 = 1
            goto L_0x00b5
        L_0x00dd:
            if (r9 != 0) goto L_0x00e7
            r0 = r20
            r0.abortCreation(r4)
            r3 = 0
            goto L_0x0007
        L_0x00e7:
            r0 = r20
            boolean r3 = r0.freeze(r4)
            if (r3 != 0) goto L_0x00f2
            r3 = 0
            goto L_0x0007
        L_0x00f2:
            r0 = r20
            android.graphics.Typeface r16 = r0.createFromFamiliesWithDefault(r4)
            if (r16 != 0) goto L_0x00fd
            r3 = 0
            goto L_0x0007
        L_0x00fd:
            r0 = r16
            r1 = r24
            android.graphics.Typeface r3 = android.graphics.Typeface.create(r0, r1)
            goto L_0x0007
        L_0x0107:
            r3 = move-exception
            r6 = r3
            goto L_0x0072
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.TypefaceCompatApi26Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, androidx.core.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }

    @Nullable
    public Typeface createFromResourcesFontFile(Context context, Resources resources, int id, String path, int style) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromResourcesFontFile(context, resources, id, path, style);
        }
        Object fontFamily = newFamily();
        if (fontFamily == null) {
            return null;
        }
        if (!addFontFromAssetManager(context, fontFamily, path, 0, -1, -1, (FontVariationAxis[]) null)) {
            abortCreation(fontFamily);
            return null;
        } else if (freeze(fontFamily)) {
            return createFromFamiliesWithDefault(fontFamily);
        } else {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public Class obtainFontFamily() throws ClassNotFoundException {
        return Class.forName(FONT_FAMILY_CLASS);
    }

    /* access modifiers changed from: protected */
    public Constructor obtainFontFamilyCtor(Class fontFamily) throws NoSuchMethodException {
        return fontFamily.getConstructor(new Class[0]);
    }

    /* access modifiers changed from: protected */
    public Method obtainAddFontFromAssetManagerMethod(Class fontFamily) throws NoSuchMethodException {
        return fontFamily.getMethod(ADD_FONT_FROM_ASSET_MANAGER_METHOD, new Class[]{AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, FontVariationAxis[].class});
    }

    /* access modifiers changed from: protected */
    public Method obtainAddFontFromBufferMethod(Class fontFamily) throws NoSuchMethodException {
        return fontFamily.getMethod(ADD_FONT_FROM_BUFFER_METHOD, new Class[]{ByteBuffer.class, Integer.TYPE, FontVariationAxis[].class, Integer.TYPE, Integer.TYPE});
    }

    /* access modifiers changed from: protected */
    public Method obtainFreezeMethod(Class fontFamily) throws NoSuchMethodException {
        return fontFamily.getMethod(FREEZE_METHOD, new Class[0]);
    }

    /* access modifiers changed from: protected */
    public Method obtainAbortCreationMethod(Class fontFamily) throws NoSuchMethodException {
        return fontFamily.getMethod(ABORT_CREATION_METHOD, new Class[0]);
    }

    /* access modifiers changed from: protected */
    public Method obtainCreateFromFamiliesWithDefaultMethod(Class fontFamily) throws NoSuchMethodException {
        Method m = Typeface.class.getDeclaredMethod(CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD, new Class[]{Array.newInstance(fontFamily, 1).getClass(), Integer.TYPE, Integer.TYPE});
        m.setAccessible(true);
        return m;
    }
}
