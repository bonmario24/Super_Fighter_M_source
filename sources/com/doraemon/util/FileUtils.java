package com.doraemon.util;

import android.content.Intent;
import android.net.Uri;
import android.support.p000v4.media.session.PlaybackStateCompat;
import com.lzy.okgo.model.HttpHeaders;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HttpsURLConnection;

public final class FileUtils {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final String LINE_SEP = System.getProperty("line.separator");

    public interface OnReplaceListener {
        boolean onReplace();
    }

    private FileUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static File getFileByPath(String filePath) {
        if (isSpace(filePath)) {
            return null;
        }
        return new File(filePath);
    }

    public static boolean isFileExists(String filePath) {
        return isFileExists(getFileByPath(filePath));
    }

    public static boolean isFileExists(File file) {
        return file != null && file.exists();
    }

    public static boolean rename(String filePath, String newName) {
        return rename(getFileByPath(filePath), newName);
    }

    public static boolean rename(File file, String newName) {
        boolean z = true;
        if (file == null || !file.exists() || isSpace(newName)) {
            return false;
        }
        if (newName.equals(file.getName())) {
            return true;
        }
        File newFile = new File(file.getParent() + File.separator + newName);
        if (newFile.exists() || !file.renameTo(newFile)) {
            z = false;
        }
        return z;
    }

    public static boolean isDir(String dirPath) {
        return isDir(getFileByPath(dirPath));
    }

    public static boolean isDir(File file) {
        return file != null && file.exists() && file.isDirectory();
    }

    public static boolean isFile(String filePath) {
        return isFile(getFileByPath(filePath));
    }

    public static boolean isFile(File file) {
        return file != null && file.exists() && file.isFile();
    }

    public static boolean createOrExistsDir(String dirPath) {
        return createOrExistsDir(getFileByPath(dirPath));
    }

    public static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? file.mkdirs() : file.isDirectory());
    }

    public static boolean createOrExistsFile(String filePath) {
        return createOrExistsFile(getFileByPath(filePath));
    }

    public static boolean createOrExistsFile(File file) {
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

    public static boolean createFileByDeleteOldFile(String filePath) {
        return createFileByDeleteOldFile(getFileByPath(filePath));
    }

    public static boolean createFileByDeleteOldFile(File file) {
        if (file == null) {
            return false;
        }
        if ((file.exists() && !file.delete()) || !createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean copyDir(String srcDirPath, String destDirPath) {
        return copyDir(getFileByPath(srcDirPath), getFileByPath(destDirPath));
    }

    public static boolean copyDir(String srcDirPath, String destDirPath, OnReplaceListener listener) {
        return copyDir(getFileByPath(srcDirPath), getFileByPath(destDirPath), listener);
    }

    public static boolean copyDir(File srcDir, File destDir) {
        return copyOrMoveDir(srcDir, destDir, false);
    }

    public static boolean copyDir(File srcDir, File destDir, OnReplaceListener listener) {
        return copyOrMoveDir(srcDir, destDir, listener, false);
    }

    public static boolean copyFile(String srcFilePath, String destFilePath) {
        return copyFile(getFileByPath(srcFilePath), getFileByPath(destFilePath));
    }

    public static boolean copyFile(String srcFilePath, String destFilePath, OnReplaceListener listener) {
        return copyFile(getFileByPath(srcFilePath), getFileByPath(destFilePath), listener);
    }

    public static boolean copyFile(File srcFile, File destFile) {
        return copyOrMoveFile(srcFile, destFile, false);
    }

    public static boolean copyFile(File srcFile, File destFile, OnReplaceListener listener) {
        return copyOrMoveFile(srcFile, destFile, listener, false);
    }

    public static boolean moveDir(String srcDirPath, String destDirPath) {
        return moveDir(getFileByPath(srcDirPath), getFileByPath(destDirPath));
    }

    public static boolean moveDir(String srcDirPath, String destDirPath, OnReplaceListener listener) {
        return moveDir(getFileByPath(srcDirPath), getFileByPath(destDirPath), listener);
    }

    public static boolean moveDir(File srcDir, File destDir) {
        return copyOrMoveDir(srcDir, destDir, true);
    }

    public static boolean moveDir(File srcDir, File destDir, OnReplaceListener listener) {
        return copyOrMoveDir(srcDir, destDir, listener, true);
    }

    public static boolean moveFile(String srcFilePath, String destFilePath) {
        return moveFile(getFileByPath(srcFilePath), getFileByPath(destFilePath));
    }

    public static boolean moveFile(String srcFilePath, String destFilePath, OnReplaceListener listener) {
        return moveFile(getFileByPath(srcFilePath), getFileByPath(destFilePath), listener);
    }

    public static boolean moveFile(File srcFile, File destFile) {
        return copyOrMoveFile(srcFile, destFile, true);
    }

    public static boolean moveFile(File srcFile, File destFile, OnReplaceListener listener) {
        return copyOrMoveFile(srcFile, destFile, listener, true);
    }

    private static boolean copyOrMoveDir(File srcDir, File destDir, boolean isMove) {
        return copyOrMoveDir(srcDir, destDir, new OnReplaceListener() {
            public boolean onReplace() {
                return true;
            }
        }, isMove);
    }

    private static boolean copyOrMoveDir(File srcDir, File destDir, OnReplaceListener listener, boolean isMove) {
        if (srcDir == null || destDir == null) {
            return false;
        }
        String destPath = destDir.getPath() + File.separator;
        if (destPath.contains(srcDir.getPath() + File.separator) || !srcDir.exists() || !srcDir.isDirectory()) {
            return false;
        }
        if (destDir.exists()) {
            if (listener != null && !listener.onReplace()) {
                return true;
            }
            if (!deleteAllInDir(destDir)) {
                return false;
            }
        }
        if (!createOrExistsDir(destDir)) {
            return false;
        }
        for (File file : srcDir.listFiles()) {
            File oneDestFile = new File(destPath + file.getName());
            if (file.isFile()) {
                if (!copyOrMoveFile(file, oneDestFile, listener, isMove)) {
                    return false;
                }
            } else if (file.isDirectory() && !copyOrMoveDir(file, oneDestFile, listener, isMove)) {
                return false;
            }
        }
        if (!isMove || deleteDir(srcDir)) {
            return true;
        }
        return false;
    }

    private static boolean copyOrMoveFile(File srcFile, File destFile, boolean isMove) {
        return copyOrMoveFile(srcFile, destFile, new OnReplaceListener() {
            public boolean onReplace() {
                return true;
            }
        }, isMove);
    }

    private static boolean copyOrMoveFile(File srcFile, File destFile, OnReplaceListener listener, boolean isMove) {
        boolean z = true;
        if (srcFile == null || destFile == null || srcFile.equals(destFile) || !srcFile.exists() || !srcFile.isFile()) {
            return false;
        }
        if (destFile.exists()) {
            if (listener != null && !listener.onReplace()) {
                return true;
            }
            if (!destFile.delete()) {
                return false;
            }
        }
        if (!createOrExistsDir(destFile.getParentFile())) {
            return false;
        }
        try {
            if (!writeFileFromIS(destFile, new FileInputStream(srcFile)) || (isMove && !deleteFile(srcFile))) {
                z = false;
            }
            return z;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(String filePath) {
        return delete(getFileByPath(filePath));
    }

    public static boolean delete(File file) {
        if (file == null) {
            return false;
        }
        if (file.isDirectory()) {
            return deleteDir(file);
        }
        return deleteFile(file);
    }

    public static boolean deleteDir(String dirPath) {
        return deleteDir(getFileByPath(dirPath));
    }

    public static boolean deleteDir(File dir) {
        if (dir == null) {
            return false;
        }
        if (!dir.exists()) {
            return true;
        }
        if (!dir.isDirectory()) {
            return false;
        }
        File[] files = dir.listFiles();
        if (!(files == null || files.length == 0)) {
            for (File file : files) {
                if (file.isFile()) {
                    if (!file.delete()) {
                        return false;
                    }
                } else if (file.isDirectory() && !deleteDir(file)) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public static boolean deleteFile(String srcFilePath) {
        return deleteFile(getFileByPath(srcFilePath));
    }

    public static boolean deleteFile(File file) {
        return file != null && (!file.exists() || (file.isFile() && file.delete()));
    }

    public static boolean deleteAllInDir(String dirPath) {
        return deleteAllInDir(getFileByPath(dirPath));
    }

    public static boolean deleteAllInDir(File dir) {
        return deleteFilesInDirWithFilter(dir, (FileFilter) new FileFilter() {
            public boolean accept(File pathname) {
                return true;
            }
        });
    }

    public static boolean deleteFilesInDir(String dirPath) {
        return deleteFilesInDir(getFileByPath(dirPath));
    }

    public static boolean deleteFilesInDir(File dir) {
        return deleteFilesInDirWithFilter(dir, (FileFilter) new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
    }

    public static boolean deleteFilesInDirWithFilter(String dirPath, FileFilter filter) {
        return deleteFilesInDirWithFilter(getFileByPath(dirPath), filter);
    }

    public static boolean deleteFilesInDirWithFilter(File dir, FileFilter filter) {
        if (dir == null) {
            return false;
        }
        if (!dir.exists()) {
            return true;
        }
        if (!dir.isDirectory()) {
            return false;
        }
        File[] files = dir.listFiles();
        if (!(files == null || files.length == 0)) {
            for (File file : files) {
                if (filter.accept(file)) {
                    if (file.isFile()) {
                        if (!file.delete()) {
                            return false;
                        }
                    } else if (file.isDirectory() && !deleteDir(file)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static List<File> listFilesInDir(String dirPath) {
        return listFilesInDir(dirPath, false);
    }

    public static List<File> listFilesInDir(File dir) {
        return listFilesInDir(dir, false);
    }

    public static List<File> listFilesInDir(String dirPath, boolean isRecursive) {
        return listFilesInDir(getFileByPath(dirPath), isRecursive);
    }

    public static List<File> listFilesInDir(File dir, boolean isRecursive) {
        return listFilesInDirWithFilter(dir, (FileFilter) new FileFilter() {
            public boolean accept(File pathname) {
                return true;
            }
        }, isRecursive);
    }

    public static List<File> listFilesInDirWithFilter(String dirPath, FileFilter filter) {
        return listFilesInDirWithFilter(getFileByPath(dirPath), filter, false);
    }

    public static List<File> listFilesInDirWithFilter(File dir, FileFilter filter) {
        return listFilesInDirWithFilter(dir, filter, false);
    }

    public static List<File> listFilesInDirWithFilter(String dirPath, FileFilter filter, boolean isRecursive) {
        return listFilesInDirWithFilter(getFileByPath(dirPath), filter, isRecursive);
    }

    public static List<File> listFilesInDirWithFilter(File dir, FileFilter filter, boolean isRecursive) {
        if (!isDir(dir)) {
            return null;
        }
        List<File> list = new ArrayList<>();
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return list;
        }
        for (File file : files) {
            if (filter.accept(file)) {
                list.add(file);
            }
            if (isRecursive && file.isDirectory()) {
                list.addAll(listFilesInDirWithFilter(file, filter, true));
            }
        }
        return list;
    }

    public static long getFileLastModified(String filePath) {
        return getFileLastModified(getFileByPath(filePath));
    }

    public static long getFileLastModified(File file) {
        if (file == null) {
            return -1;
        }
        return file.lastModified();
    }

    public static String getFileCharsetSimple(String filePath) {
        return getFileCharsetSimple(getFileByPath(filePath));
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0021 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0030 A[SYNTHETIC, Splitter:B:18:0x0030] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x003c A[SYNTHETIC, Splitter:B:24:0x003c] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0045 A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0048 A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x004b A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getFileCharsetSimple(java.io.File r6) {
        /*
            r3 = 0
            r1 = 0
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x002a }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ IOException -> 0x002a }
            r4.<init>(r6)     // Catch:{ IOException -> 0x002a }
            r2.<init>(r4)     // Catch:{ IOException -> 0x002a }
            int r4 = r2.read()     // Catch:{ IOException -> 0x0051, all -> 0x004e }
            int r4 = r4 << 8
            int r5 = r2.read()     // Catch:{ IOException -> 0x0051, all -> 0x004e }
            int r3 = r4 + r5
            if (r2 == 0) goto L_0x001d
            r2.close()     // Catch:{ IOException -> 0x0024 }
        L_0x001d:
            r1 = r2
        L_0x001e:
            switch(r3) {
                case 61371: goto L_0x0045;
                case 65279: goto L_0x004b;
                case 65534: goto L_0x0048;
                default: goto L_0x0021;
            }
        L_0x0021:
            java.lang.String r4 = "GBK"
        L_0x0023:
            return r4
        L_0x0024:
            r0 = move-exception
            r0.printStackTrace()
            r1 = r2
            goto L_0x001e
        L_0x002a:
            r0 = move-exception
        L_0x002b:
            r0.printStackTrace()     // Catch:{ all -> 0x0039 }
            if (r1 == 0) goto L_0x001e
            r1.close()     // Catch:{ IOException -> 0x0034 }
            goto L_0x001e
        L_0x0034:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x001e
        L_0x0039:
            r4 = move-exception
        L_0x003a:
            if (r1 == 0) goto L_0x003f
            r1.close()     // Catch:{ IOException -> 0x0040 }
        L_0x003f:
            throw r4
        L_0x0040:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x003f
        L_0x0045:
            java.lang.String r4 = "UTF-8"
            goto L_0x0023
        L_0x0048:
            java.lang.String r4 = "Unicode"
            goto L_0x0023
        L_0x004b:
            java.lang.String r4 = "UTF-16BE"
            goto L_0x0023
        L_0x004e:
            r4 = move-exception
            r1 = r2
            goto L_0x003a
        L_0x0051:
            r0 = move-exception
            r1 = r2
            goto L_0x002b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.doraemon.util.FileUtils.getFileCharsetSimple(java.io.File):java.lang.String");
    }

    public static int getFileLines(String filePath) {
        return getFileLines(getFileByPath(filePath));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0025, code lost:
        if (r3 >= r6) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002b, code lost:
        if (r0[r3] != 10) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002f, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0032, code lost:
        r6 = r5.read(r0, 0, 1024);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0039, code lost:
        if (r6 == -1) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003b, code lost:
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003c, code lost:
        if (r3 >= r6) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0042, code lost:
        if (r0[r3] != 13) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0044, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0046, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0049, code lost:
        if (r5 == null) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004e, code lost:
        r4 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0050, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0051, code lost:
        r2.printStackTrace();
        r4 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0019, code lost:
        if (LINE_SEP.endsWith(com.doraemon.util.ShellAdbUtil.COMMAND_LINE_END) != false) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001b, code lost:
        r6 = r5.read(r0, 0, 1024);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0022, code lost:
        if (r6 == -1) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        r3 = 0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x005c A[SYNTHETIC, Splitter:B:35:0x005c] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0068 A[SYNTHETIC, Splitter:B:41:0x0068] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getFileLines(java.io.File r10) {
        /*
            r9 = -1
            r1 = 1
            r4 = 0
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0056 }
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0056 }
            r7.<init>(r10)     // Catch:{ IOException -> 0x0056 }
            r5.<init>(r7)     // Catch:{ IOException -> 0x0056 }
            r7 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r7]     // Catch:{ IOException -> 0x0074, all -> 0x0071 }
            java.lang.String r7 = LINE_SEP     // Catch:{ IOException -> 0x0074, all -> 0x0071 }
            java.lang.String r8 = "\n"
            boolean r7 = r7.endsWith(r8)     // Catch:{ IOException -> 0x0074, all -> 0x0071 }
            if (r7 == 0) goto L_0x0032
        L_0x001b:
            r7 = 0
            r8 = 1024(0x400, float:1.435E-42)
            int r6 = r5.read(r0, r7, r8)     // Catch:{ IOException -> 0x0074, all -> 0x0071 }
            if (r6 == r9) goto L_0x0049
            r3 = 0
        L_0x0025:
            if (r3 >= r6) goto L_0x001b
            byte r7 = r0[r3]     // Catch:{ IOException -> 0x0074, all -> 0x0071 }
            r8 = 10
            if (r7 != r8) goto L_0x002f
            int r1 = r1 + 1
        L_0x002f:
            int r3 = r3 + 1
            goto L_0x0025
        L_0x0032:
            r7 = 0
            r8 = 1024(0x400, float:1.435E-42)
            int r6 = r5.read(r0, r7, r8)     // Catch:{ IOException -> 0x0074, all -> 0x0071 }
            if (r6 == r9) goto L_0x0049
            r3 = 0
        L_0x003c:
            if (r3 >= r6) goto L_0x0032
            byte r7 = r0[r3]     // Catch:{ IOException -> 0x0074, all -> 0x0071 }
            r8 = 13
            if (r7 != r8) goto L_0x0046
            int r1 = r1 + 1
        L_0x0046:
            int r3 = r3 + 1
            goto L_0x003c
        L_0x0049:
            if (r5 == 0) goto L_0x004e
            r5.close()     // Catch:{ IOException -> 0x0050 }
        L_0x004e:
            r4 = r5
        L_0x004f:
            return r1
        L_0x0050:
            r2 = move-exception
            r2.printStackTrace()
            r4 = r5
            goto L_0x004f
        L_0x0056:
            r2 = move-exception
        L_0x0057:
            r2.printStackTrace()     // Catch:{ all -> 0x0065 }
            if (r4 == 0) goto L_0x004f
            r4.close()     // Catch:{ IOException -> 0x0060 }
            goto L_0x004f
        L_0x0060:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x004f
        L_0x0065:
            r7 = move-exception
        L_0x0066:
            if (r4 == 0) goto L_0x006b
            r4.close()     // Catch:{ IOException -> 0x006c }
        L_0x006b:
            throw r7
        L_0x006c:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x006b
        L_0x0071:
            r7 = move-exception
            r4 = r5
            goto L_0x0066
        L_0x0074:
            r2 = move-exception
            r4 = r5
            goto L_0x0057
        */
        throw new UnsupportedOperationException("Method not decompiled: com.doraemon.util.FileUtils.getFileLines(java.io.File):int");
    }

    public static String getDirSize(String dirPath) {
        return getDirSize(getFileByPath(dirPath));
    }

    public static String getDirSize(File dir) {
        long len = getDirLength(dir);
        return len == -1 ? "" : byte2FitMemorySize(len);
    }

    public static String getFileSize(String filePath) {
        long len = getFileLength(filePath);
        return len == -1 ? "" : byte2FitMemorySize(len);
    }

    public static String getFileSize(File file) {
        long len = getFileLength(file);
        return len == -1 ? "" : byte2FitMemorySize(len);
    }

    public static long getDirLength(String dirPath) {
        return getDirLength(getFileByPath(dirPath));
    }

    public static long getDirLength(File dir) {
        long length;
        if (!isDir(dir)) {
            return -1;
        }
        long len = 0;
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return 0;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                length = getDirLength(file);
            } else {
                length = file.length();
            }
            len += length;
        }
        return len;
    }

    public static long getFileLength(String filePath) {
        if (filePath.matches("[a-zA-z]+://[^\\s]*")) {
            try {
                HttpsURLConnection conn = (HttpsURLConnection) new URL(filePath).openConnection();
                conn.setRequestProperty(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING, "identity");
                conn.connect();
                if (conn.getResponseCode() == 200) {
                    return (long) conn.getContentLength();
                }
                return -1;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return getFileLength(getFileByPath(filePath));
    }

    public static long getFileLength(File file) {
        if (!isFile(file)) {
            return -1;
        }
        return file.length();
    }

    public static String getFileMD5ToString(String filePath) {
        return getFileMD5ToString(isSpace(filePath) ? null : new File(filePath));
    }

    public static String getFileMD5ToString(File file) {
        return bytes2HexString(getFileMD5(file));
    }

    public static byte[] getFileMD5(String filePath) {
        return getFileMD5(getFileByPath(filePath));
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0039 A[SYNTHETIC, Splitter:B:22:0x0039] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0045 A[SYNTHETIC, Splitter:B:28:0x0045] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] getFileMD5(java.io.File r8) {
        /*
            r6 = 0
            if (r8 != 0) goto L_0x0004
        L_0x0003:
            return r6
        L_0x0004:
            r1 = 0
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ NoSuchAlgorithmException -> 0x0032, IOException -> 0x004e }
            r4.<init>(r8)     // Catch:{ NoSuchAlgorithmException -> 0x0032, IOException -> 0x004e }
            java.lang.String r7 = "MD5"
            java.security.MessageDigest r5 = java.security.MessageDigest.getInstance(r7)     // Catch:{ NoSuchAlgorithmException -> 0x0032, IOException -> 0x004e }
            java.security.DigestInputStream r2 = new java.security.DigestInputStream     // Catch:{ NoSuchAlgorithmException -> 0x0032, IOException -> 0x004e }
            r2.<init>(r4, r5)     // Catch:{ NoSuchAlgorithmException -> 0x0032, IOException -> 0x004e }
            r7 = 262144(0x40000, float:3.67342E-40)
            byte[] r0 = new byte[r7]     // Catch:{ NoSuchAlgorithmException -> 0x0054, IOException -> 0x0057, all -> 0x0051 }
        L_0x0019:
            int r7 = r2.read(r0)     // Catch:{ NoSuchAlgorithmException -> 0x0054, IOException -> 0x0057, all -> 0x0051 }
            if (r7 > 0) goto L_0x0019
            java.security.MessageDigest r5 = r2.getMessageDigest()     // Catch:{ NoSuchAlgorithmException -> 0x0054, IOException -> 0x0057, all -> 0x0051 }
            byte[] r6 = r5.digest()     // Catch:{ NoSuchAlgorithmException -> 0x0054, IOException -> 0x0057, all -> 0x0051 }
            if (r2 == 0) goto L_0x0003
            r2.close()     // Catch:{ IOException -> 0x002d }
            goto L_0x0003
        L_0x002d:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x0003
        L_0x0032:
            r7 = move-exception
        L_0x0033:
            r3 = r7
        L_0x0034:
            r3.printStackTrace()     // Catch:{ all -> 0x0042 }
            if (r1 == 0) goto L_0x0003
            r1.close()     // Catch:{ IOException -> 0x003d }
            goto L_0x0003
        L_0x003d:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x0003
        L_0x0042:
            r6 = move-exception
        L_0x0043:
            if (r1 == 0) goto L_0x0048
            r1.close()     // Catch:{ IOException -> 0x0049 }
        L_0x0048:
            throw r6
        L_0x0049:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x0048
        L_0x004e:
            r7 = move-exception
        L_0x004f:
            r3 = r7
            goto L_0x0034
        L_0x0051:
            r6 = move-exception
            r1 = r2
            goto L_0x0043
        L_0x0054:
            r7 = move-exception
            r1 = r2
            goto L_0x0033
        L_0x0057:
            r7 = move-exception
            r1 = r2
            goto L_0x004f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.doraemon.util.FileUtils.getFileMD5(java.io.File):byte[]");
    }

    public static String getDirName(File file) {
        if (file == null) {
            return "";
        }
        return getDirName(file.getAbsolutePath());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0009, code lost:
        r0 = r3.lastIndexOf(java.io.File.separator);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getDirName(java.lang.String r3) {
        /*
            boolean r1 = isSpace(r3)
            if (r1 == 0) goto L_0x0009
            java.lang.String r1 = ""
        L_0x0008:
            return r1
        L_0x0009:
            java.lang.String r1 = java.io.File.separator
            int r0 = r3.lastIndexOf(r1)
            r1 = -1
            if (r0 != r1) goto L_0x0015
            java.lang.String r1 = ""
            goto L_0x0008
        L_0x0015:
            r1 = 0
            int r2 = r0 + 1
            java.lang.String r1 = r3.substring(r1, r2)
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.doraemon.util.FileUtils.getDirName(java.lang.String):java.lang.String");
    }

    public static String getFileName(File file) {
        if (file == null) {
            return "";
        }
        return getFileName(file.getAbsolutePath());
    }

    public static String getFileName(String filePath) {
        if (isSpace(filePath)) {
            return "";
        }
        int lastSep = filePath.lastIndexOf(File.separator);
        return lastSep != -1 ? filePath.substring(lastSep + 1) : filePath;
    }

    public static String getFileNameNoExtension(File file) {
        if (file == null) {
            return "";
        }
        return getFileNameNoExtension(file.getPath());
    }

    public static String getFileNameNoExtension(String filePath) {
        if (isSpace(filePath)) {
            return "";
        }
        int lastPoi = filePath.lastIndexOf(46);
        int lastSep = filePath.lastIndexOf(File.separator);
        if (lastSep == -1) {
            if (lastPoi != -1) {
                return filePath.substring(0, lastPoi);
            }
            return filePath;
        } else if (lastPoi == -1 || lastSep > lastPoi) {
            return filePath.substring(lastSep + 1);
        } else {
            return filePath.substring(lastSep + 1, lastPoi);
        }
    }

    public static String getFileExtension(File file) {
        if (file == null) {
            return "";
        }
        return getFileExtension(file.getPath());
    }

    public static String getFileExtension(String filePath) {
        if (isSpace(filePath)) {
            return "";
        }
        int lastPoi = filePath.lastIndexOf(46);
        int lastSep = filePath.lastIndexOf(File.separator);
        if (lastPoi == -1 || lastSep >= lastPoi) {
            return "";
        }
        return filePath.substring(lastPoi + 1);
    }

    public static void notifySystemToScan(File file) {
        if (file != null && file.exists()) {
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setData(Uri.fromFile(file));
            Utils.getApp().sendBroadcast(intent);
        }
    }

    public static void notifySystemToScan(String filePath) {
        notifySystemToScan(getFileByPath(filePath));
    }

    private static String bytes2HexString(byte[] bytes) {
        int len;
        if (bytes == null || (len = bytes.length) <= 0) {
            return "";
        }
        char[] ret = new char[(len << 1)];
        int j = 0;
        for (int i = 0; i < len; i++) {
            int j2 = j + 1;
            ret[j] = HEX_DIGITS[(bytes[i] >> 4) & 15];
            j = j2 + 1;
            ret[j2] = HEX_DIGITS[bytes[i] & 15];
        }
        return new String(ret);
    }

    private static String byte2FitMemorySize(long byteNum) {
        if (byteNum < 0) {
            return "shouldn't be less than zero!";
        }
        if (byteNum < PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
            return String.format(Locale.getDefault(), "%.3fB", new Object[]{Double.valueOf((double) byteNum)});
        } else if (byteNum < PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
            return String.format(Locale.getDefault(), "%.3fKB", new Object[]{Double.valueOf(((double) byteNum) / 1024.0d)});
        } else if (byteNum < 1073741824) {
            return String.format(Locale.getDefault(), "%.3fMB", new Object[]{Double.valueOf(((double) byteNum) / 1048576.0d)});
        } else {
            return String.format(Locale.getDefault(), "%.3fGB", new Object[]{Double.valueOf(((double) byteNum) / 1.073741824E9d)});
        }
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

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0029 A[SYNTHETIC, Splitter:B:16:0x0029] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0052 A[SYNTHETIC, Splitter:B:38:0x0052] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean writeFileFromIS(java.io.File r8, java.io.InputStream r9) {
        /*
            r5 = 0
            r3 = 0
            java.io.BufferedOutputStream r4 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x0063 }
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0063 }
            r6.<init>(r8)     // Catch:{ IOException -> 0x0063 }
            r4.<init>(r6)     // Catch:{ IOException -> 0x0063 }
            r6 = 8192(0x2000, float:1.14794E-41)
            byte[] r0 = new byte[r6]     // Catch:{ IOException -> 0x001f, all -> 0x0060 }
        L_0x0010:
            r6 = 0
            r7 = 8192(0x2000, float:1.14794E-41)
            int r2 = r9.read(r0, r6, r7)     // Catch:{ IOException -> 0x001f, all -> 0x0060 }
            r6 = -1
            if (r2 == r6) goto L_0x002d
            r6 = 0
            r4.write(r0, r6, r2)     // Catch:{ IOException -> 0x001f, all -> 0x0060 }
            goto L_0x0010
        L_0x001f:
            r1 = move-exception
            r3 = r4
        L_0x0021:
            r1.printStackTrace()     // Catch:{ all -> 0x004c }
            r9.close()     // Catch:{ IOException -> 0x0042 }
        L_0x0027:
            if (r3 == 0) goto L_0x002c
            r3.close()     // Catch:{ IOException -> 0x0047 }
        L_0x002c:
            return r5
        L_0x002d:
            r5 = 1
            r9.close()     // Catch:{ IOException -> 0x0038 }
        L_0x0031:
            if (r4 == 0) goto L_0x0036
            r4.close()     // Catch:{ IOException -> 0x003d }
        L_0x0036:
            r3 = r4
            goto L_0x002c
        L_0x0038:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0031
        L_0x003d:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0036
        L_0x0042:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0027
        L_0x0047:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x002c
        L_0x004c:
            r5 = move-exception
        L_0x004d:
            r9.close()     // Catch:{ IOException -> 0x0056 }
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
            goto L_0x0021
        */
        throw new UnsupportedOperationException("Method not decompiled: com.doraemon.util.FileUtils.writeFileFromIS(java.io.File, java.io.InputStream):boolean");
    }
}
