package com.doraemon.util;

import android.support.annotation.RawRes;
import com.facebook.internal.AnalyticsEvents;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public final class ResourceUtils {
    private static final int BUFFER_SIZE = 8192;

    private ResourceUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static int getIdByName(String name) {
        return Utils.getApp().getResources().getIdentifier(name, "id", Utils.getApp().getPackageName());
    }

    public static int getStringIdByName(String name) {
        return Utils.getApp().getResources().getIdentifier(name, "string", Utils.getApp().getPackageName());
    }

    public static int getColorIdByName(String name) {
        return Utils.getApp().getResources().getIdentifier(name, "color", Utils.getApp().getPackageName());
    }

    public static int getDimenIdByName(String name) {
        return Utils.getApp().getResources().getIdentifier(name, "dimen", Utils.getApp().getPackageName());
    }

    public static int getDrawableIdByName(String name) {
        return Utils.getApp().getResources().getIdentifier(name, "drawable", Utils.getApp().getPackageName());
    }

    public static int getMipmapIdByName(String name) {
        return Utils.getApp().getResources().getIdentifier(name, "mipmap", Utils.getApp().getPackageName());
    }

    public static int getLayoutIdByName(String name) {
        return Utils.getApp().getResources().getIdentifier(name, "layout", Utils.getApp().getPackageName());
    }

    public static int getStyleIdByName(String name) {
        return Utils.getApp().getResources().getIdentifier(name, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, Utils.getApp().getPackageName());
    }

    public static int getAnimIdByName(String name) {
        return Utils.getApp().getResources().getIdentifier(name, "anim", Utils.getApp().getPackageName());
    }

    public static int getMenuIdByName(String name) {
        return Utils.getApp().getResources().getIdentifier(name, "menu", Utils.getApp().getPackageName());
    }

    public static boolean copyFileFromAssets(String assetsFilePath, String destFilePath) {
        boolean res = true;
        try {
            String[] assets = Utils.getApp().getAssets().list(assetsFilePath);
            if (assets.length <= 0) {
                return writeFileFromIS(destFilePath, Utils.getApp().getAssets().open(assetsFilePath), false);
            }
            for (String asset : assets) {
                res &= copyFileFromAssets(assetsFilePath + "/" + asset, destFilePath + "/" + asset);
            }
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String readAssets2String(String assetsFilePath) {
        return readAssets2String(assetsFilePath, (String) null);
    }

    public static String readAssets2String(String assetsFilePath, String charsetName) {
        try {
            byte[] bytes = is2Bytes(Utils.getApp().getAssets().open(assetsFilePath));
            if (bytes == null) {
                return null;
            }
            if (isSpace(charsetName)) {
                return new String(bytes);
            }
            try {
                return new String(bytes, charsetName);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return "";
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static List<String> readAssets2List(String assetsPath) {
        return readAssets2List(assetsPath, (String) null);
    }

    public static List<String> readAssets2List(String assetsPath, String charsetName) {
        try {
            return is2List(Utils.getApp().getResources().getAssets().open(assetsPath), charsetName);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean copyFileFromRaw(@RawRes int resId, String destFilePath) {
        return writeFileFromIS(destFilePath, Utils.getApp().getResources().openRawResource(resId), false);
    }

    public static String readRaw2String(@RawRes int resId) {
        return readRaw2String(resId, (String) null);
    }

    public static String readRaw2String(@RawRes int resId, String charsetName) {
        byte[] bytes = is2Bytes(Utils.getApp().getResources().openRawResource(resId));
        if (bytes == null) {
            return null;
        }
        if (isSpace(charsetName)) {
            return new String(bytes);
        }
        try {
            return new String(bytes, charsetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static List<String> readRaw2List(@RawRes int resId) {
        return readRaw2List(resId, (String) null);
    }

    public static List<String> readRaw2List(@RawRes int resId, String charsetName) {
        return is2List(Utils.getApp().getResources().openRawResource(resId), charsetName);
    }

    private static boolean writeFileFromIS(String filePath, InputStream is, boolean append) {
        return writeFileFromIS(getFileByPath(filePath), is, append);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0032 A[SYNTHETIC, Splitter:B:20:0x0032] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x005a A[SYNTHETIC, Splitter:B:40:0x005a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean writeFileFromIS(java.io.File r8, java.io.InputStream r9, boolean r10) {
        /*
            r5 = 0
            boolean r6 = createOrExistsFile(r8)
            if (r6 == 0) goto L_0x0009
            if (r9 != 0) goto L_0x000a
        L_0x0009:
            return r5
        L_0x000a:
            r3 = 0
            java.io.BufferedOutputStream r4 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x006b }
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x006b }
            r6.<init>(r8, r10)     // Catch:{ IOException -> 0x006b }
            r4.<init>(r6)     // Catch:{ IOException -> 0x006b }
            r6 = 8192(0x2000, float:1.14794E-41)
            byte[] r0 = new byte[r6]     // Catch:{ IOException -> 0x0028, all -> 0x0068 }
        L_0x0019:
            r6 = 0
            r7 = 8192(0x2000, float:1.14794E-41)
            int r2 = r9.read(r0, r6, r7)     // Catch:{ IOException -> 0x0028, all -> 0x0068 }
            r6 = -1
            if (r2 == r6) goto L_0x003b
            r6 = 0
            r4.write(r0, r6, r2)     // Catch:{ IOException -> 0x0028, all -> 0x0068 }
            goto L_0x0019
        L_0x0028:
            r1 = move-exception
            r3 = r4
        L_0x002a:
            r1.printStackTrace()     // Catch:{ all -> 0x0054 }
            r9.close()     // Catch:{ IOException -> 0x004f }
        L_0x0030:
            if (r3 == 0) goto L_0x0009
            r3.close()     // Catch:{ IOException -> 0x0036 }
            goto L_0x0009
        L_0x0036:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0009
        L_0x003b:
            r5 = 1
            r9.close()     // Catch:{ IOException -> 0x004a }
        L_0x003f:
            if (r4 == 0) goto L_0x0009
            r4.close()     // Catch:{ IOException -> 0x0045 }
            goto L_0x0009
        L_0x0045:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0009
        L_0x004a:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x003f
        L_0x004f:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0030
        L_0x0054:
            r5 = move-exception
        L_0x0055:
            r9.close()     // Catch:{ IOException -> 0x005e }
        L_0x0058:
            if (r3 == 0) goto L_0x005d
            r3.close()     // Catch:{ IOException -> 0x0063 }
        L_0x005d:
            throw r5
        L_0x005e:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0058
        L_0x0063:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x005d
        L_0x0068:
            r5 = move-exception
            r3 = r4
            goto L_0x0055
        L_0x006b:
            r1 = move-exception
            goto L_0x002a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.doraemon.util.ResourceUtils.writeFileFromIS(java.io.File, java.io.InputStream, boolean):boolean");
    }

    private static File getFileByPath(String filePath) {
        if (isSpace(filePath)) {
            return null;
        }
        return new File(filePath);
    }

    private static boolean createOrExistsFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? file.mkdirs() : file.isDirectory());
    }

    private static boolean isSpace(String s) {
        if (s == null) {
            return true;
        }
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x0052 A[SYNTHETIC, Splitter:B:40:0x0052] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] is2Bytes(java.io.InputStream r8) {
        /*
            r5 = 0
            if (r8 != 0) goto L_0x0004
        L_0x0003:
            return r5
        L_0x0004:
            r3 = 0
            java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0063 }
            r4.<init>()     // Catch:{ IOException -> 0x0063 }
            r6 = 8192(0x2000, float:1.14794E-41)
            byte[] r0 = new byte[r6]     // Catch:{ IOException -> 0x001d, all -> 0x0060 }
        L_0x000e:
            r6 = 0
            r7 = 8192(0x2000, float:1.14794E-41)
            int r2 = r8.read(r0, r6, r7)     // Catch:{ IOException -> 0x001d, all -> 0x0060 }
            r6 = -1
            if (r2 == r6) goto L_0x0030
            r6 = 0
            r4.write(r0, r6, r2)     // Catch:{ IOException -> 0x001d, all -> 0x0060 }
            goto L_0x000e
        L_0x001d:
            r1 = move-exception
            r3 = r4
        L_0x001f:
            r1.printStackTrace()     // Catch:{ all -> 0x004c }
            r8.close()     // Catch:{ IOException -> 0x0047 }
        L_0x0025:
            if (r3 == 0) goto L_0x0003
            r3.close()     // Catch:{ IOException -> 0x002b }
            goto L_0x0003
        L_0x002b:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0003
        L_0x0030:
            byte[] r5 = r4.toByteArray()     // Catch:{ IOException -> 0x001d, all -> 0x0060 }
            r8.close()     // Catch:{ IOException -> 0x0042 }
        L_0x0037:
            if (r4 == 0) goto L_0x0003
            r4.close()     // Catch:{ IOException -> 0x003d }
            goto L_0x0003
        L_0x003d:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0003
        L_0x0042:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0037
        L_0x0047:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0025
        L_0x004c:
            r5 = move-exception
        L_0x004d:
            r8.close()     // Catch:{ IOException -> 0x0056 }
        L_0x0050:
            if (r3 == 0) goto L_0x0055
            r3.close()     // Catch:{ IOException -> 0x005b }
        L_0x0055:
            throw r5
        L_0x0056:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0050
        L_0x005b:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0055
        L_0x0060:
            r5 = move-exception
            r3 = r4
            goto L_0x004d
        L_0x0063:
            r1 = move-exception
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.doraemon.util.ResourceUtils.is2Bytes(java.io.InputStream):byte[]");
    }

    private static List<String> is2List(InputStream is, String charsetName) {
        List<String> list;
        BufferedReader reader;
        BufferedReader reader2 = null;
        try {
            list = new ArrayList<>();
            if (isSpace(charsetName)) {
                reader = new BufferedReader(new InputStreamReader(is));
            } else {
                reader = new BufferedReader(new InputStreamReader(is, charsetName));
            }
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                list.add(line);
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            list = null;
            if (reader2 != null) {
                try {
                    reader2.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        } catch (Throwable th) {
            if (reader2 != null) {
                try {
                    reader2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
        return list;
    }
}
