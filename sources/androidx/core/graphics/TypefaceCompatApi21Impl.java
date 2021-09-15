package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.FontResourcesParserCompat;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(21)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl {
    private static final String ADD_FONT_WEIGHT_STYLE_METHOD = "addFontWeightStyle";
    private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
    private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
    private static final String TAG = "TypefaceCompatApi21Impl";
    private static Method sAddFontWeightStyle;
    private static Method sCreateFromFamiliesWithDefault;
    private static Class sFontFamily;
    private static Constructor sFontFamilyCtor;
    private static boolean sHasInitBeenCalled = false;

    TypefaceCompatApi21Impl() {
    }

    private static void init() {
        Class fontFamilyClass;
        Constructor fontFamilyCtor;
        Method addFontMethod;
        Method createFromFamiliesWithDefaultMethod;
        if (!sHasInitBeenCalled) {
            sHasInitBeenCalled = true;
            try {
                fontFamilyClass = Class.forName(FONT_FAMILY_CLASS);
                fontFamilyCtor = fontFamilyClass.getConstructor(new Class[0]);
                addFontMethod = fontFamilyClass.getMethod(ADD_FONT_WEIGHT_STYLE_METHOD, new Class[]{String.class, Integer.TYPE, Boolean.TYPE});
                createFromFamiliesWithDefaultMethod = Typeface.class.getMethod(CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD, new Class[]{Array.newInstance(fontFamilyClass, 1).getClass()});
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                Log.e(TAG, e.getClass().getName(), e);
                fontFamilyClass = null;
                fontFamilyCtor = null;
                addFontMethod = null;
                createFromFamiliesWithDefaultMethod = null;
            }
            sFontFamilyCtor = fontFamilyCtor;
            sFontFamily = fontFamilyClass;
            sAddFontWeightStyle = addFontMethod;
            sCreateFromFamiliesWithDefault = createFromFamiliesWithDefaultMethod;
        }
    }

    private File getFile(@NonNull ParcelFileDescriptor fd) {
        try {
            String path = Os.readlink("/proc/self/fd/" + fd.getFd());
            if (OsConstants.S_ISREG(Os.stat(path).st_mode)) {
                return new File(path);
            }
            return null;
        } catch (ErrnoException e) {
            return null;
        }
    }

    private static Object newFamily() {
        init();
        try {
            return sFontFamilyCtor.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static Typeface createFromFamiliesWithDefault(Object family) {
        init();
        try {
            Object familyArray = Array.newInstance(sFontFamily, 1);
            Array.set(familyArray, 0, family);
            return (Typeface) sCreateFromFamiliesWithDefault.invoke((Object) null, new Object[]{familyArray});
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean addFontWeightStyle(Object family, String name, int weight, boolean style) {
        init();
        try {
            return ((Boolean) sAddFontWeightStyle.invoke(family, new Object[]{name, Integer.valueOf(weight), Boolean.valueOf(style)})).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:51:0x006d, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x006e, code lost:
        r8 = r7;
        r9 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x007c, code lost:
        r7 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x00ae, code lost:
        r7 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x00af, code lost:
        r8 = null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x006d A[ExcHandler: all (r7v2 'th' java.lang.Throwable A[CUSTOM_DECLARE])] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r12, android.os.CancellationSignal r13, @androidx.annotation.NonNull androidx.core.provider.FontsContractCompat.FontInfo[] r14, int r15) {
        /*
            r11 = this;
            r6 = 0
            int r7 = r14.length
            r8 = 1
            if (r7 >= r8) goto L_0x0006
        L_0x0005:
            return r6
        L_0x0006:
            androidx.core.provider.FontsContractCompat$FontInfo r0 = r11.findBestInfo(r14, r15)
            android.content.ContentResolver r5 = r12.getContentResolver()
            android.net.Uri r7 = r0.getUri()     // Catch:{ IOException -> 0x0028 }
            java.lang.String r8 = "r"
            android.os.ParcelFileDescriptor r4 = r5.openFileDescriptor(r7, r8, r13)     // Catch:{ IOException -> 0x0028 }
            r8 = 0
            if (r4 != 0) goto L_0x002e
            if (r4 == 0) goto L_0x0005
            if (r6 == 0) goto L_0x002a
            r4.close()     // Catch:{ Throwable -> 0x0023 }
            goto L_0x0005
        L_0x0023:
            r7 = move-exception
            r8.addSuppressed(r7)     // Catch:{ IOException -> 0x0028 }
            goto L_0x0005
        L_0x0028:
            r1 = move-exception
            goto L_0x0005
        L_0x002a:
            r4.close()     // Catch:{ IOException -> 0x0028 }
            goto L_0x0005
        L_0x002e:
            java.io.File r2 = r11.getFile(r4)     // Catch:{ Throwable -> 0x005d, all -> 0x006d }
            if (r2 == 0) goto L_0x003a
            boolean r7 = r2.canRead()     // Catch:{ Throwable -> 0x005d, all -> 0x006d }
            if (r7 != 0) goto L_0x008e
        L_0x003a:
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x005d, all -> 0x006d }
            java.io.FileDescriptor r7 = r4.getFileDescriptor()     // Catch:{ Throwable -> 0x005d, all -> 0x006d }
            r3.<init>(r7)     // Catch:{ Throwable -> 0x005d, all -> 0x006d }
            r9 = 0
            android.graphics.Typeface r7 = super.createFromInputStream(r12, r3)     // Catch:{ Throwable -> 0x007a, all -> 0x00ae }
            if (r3 == 0) goto L_0x004f
            if (r6 == 0) goto L_0x0069
            r3.close()     // Catch:{ Throwable -> 0x0058, all -> 0x006d }
        L_0x004f:
            if (r4 == 0) goto L_0x0056
            if (r6 == 0) goto L_0x0076
            r4.close()     // Catch:{ Throwable -> 0x0071 }
        L_0x0056:
            r6 = r7
            goto L_0x0005
        L_0x0058:
            r10 = move-exception
            r9.addSuppressed(r10)     // Catch:{ Throwable -> 0x005d, all -> 0x006d }
            goto L_0x004f
        L_0x005d:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x005f }
        L_0x005f:
            r8 = move-exception
            r9 = r7
        L_0x0061:
            if (r4 == 0) goto L_0x0068
            if (r9 == 0) goto L_0x00aa
            r4.close()     // Catch:{ Throwable -> 0x00a5 }
        L_0x0068:
            throw r8     // Catch:{ IOException -> 0x0028 }
        L_0x0069:
            r3.close()     // Catch:{ Throwable -> 0x005d, all -> 0x006d }
            goto L_0x004f
        L_0x006d:
            r7 = move-exception
            r8 = r7
            r9 = r6
            goto L_0x0061
        L_0x0071:
            r9 = move-exception
            r8.addSuppressed(r9)     // Catch:{ IOException -> 0x0028 }
            goto L_0x0056
        L_0x0076:
            r4.close()     // Catch:{ IOException -> 0x0028 }
            goto L_0x0056
        L_0x007a:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x007c }
        L_0x007c:
            r7 = move-exception
        L_0x007d:
            if (r3 == 0) goto L_0x0084
            if (r8 == 0) goto L_0x008a
            r3.close()     // Catch:{ Throwable -> 0x0085, all -> 0x006d }
        L_0x0084:
            throw r7     // Catch:{ Throwable -> 0x005d, all -> 0x006d }
        L_0x0085:
            r9 = move-exception
            r8.addSuppressed(r9)     // Catch:{ Throwable -> 0x005d, all -> 0x006d }
            goto L_0x0084
        L_0x008a:
            r3.close()     // Catch:{ Throwable -> 0x005d, all -> 0x006d }
            goto L_0x0084
        L_0x008e:
            android.graphics.Typeface r7 = android.graphics.Typeface.createFromFile(r2)     // Catch:{ Throwable -> 0x005d, all -> 0x006d }
            if (r4 == 0) goto L_0x0099
            if (r6 == 0) goto L_0x00a1
            r4.close()     // Catch:{ Throwable -> 0x009c }
        L_0x0099:
            r6 = r7
            goto L_0x0005
        L_0x009c:
            r9 = move-exception
            r8.addSuppressed(r9)     // Catch:{ IOException -> 0x0028 }
            goto L_0x0099
        L_0x00a1:
            r4.close()     // Catch:{ IOException -> 0x0028 }
            goto L_0x0099
        L_0x00a5:
            r7 = move-exception
            r9.addSuppressed(r7)     // Catch:{ IOException -> 0x0028 }
            goto L_0x0068
        L_0x00aa:
            r4.close()     // Catch:{ IOException -> 0x0028 }
            goto L_0x0068
        L_0x00ae:
            r7 = move-exception
            r8 = r6
            goto L_0x007d
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.TypefaceCompatApi21Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, androidx.core.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }

    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry entry, Resources resources, int style) {
        Object family = newFamily();
        FontResourcesParserCompat.FontFileResourceEntry[] entries = entry.getEntries();
        int length = entries.length;
        int i = 0;
        while (i < length) {
            FontResourcesParserCompat.FontFileResourceEntry e = entries[i];
            File tmpFile = TypefaceCompatUtil.getTempFile(context);
            if (tmpFile == null) {
                return null;
            }
            try {
                if (!TypefaceCompatUtil.copyToFile(tmpFile, resources, e.getResourceId())) {
                    tmpFile.delete();
                    return null;
                } else if (!addFontWeightStyle(family, tmpFile.getPath(), e.getWeight(), e.isItalic())) {
                    return null;
                } else {
                    tmpFile.delete();
                    i++;
                }
            } catch (RuntimeException e2) {
                return null;
            } finally {
                tmpFile.delete();
            }
        }
        return createFromFamiliesWithDefault(family);
    }
}
