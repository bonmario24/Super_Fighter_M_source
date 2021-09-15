package com.doraemon.util;

import android.support.annotation.NonNull;
import com.doraemon.util.Utils;
import java.util.List;

public final class ShellUtils {
    private static final String LINE_SEP = System.getProperty("line.separator");

    private ShellUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Utils.Task<CommandResult> execCmdAsync(String command, boolean isRooted, Utils.Callback<CommandResult> callback) {
        return execCmdAsync(new String[]{command}, isRooted, true, callback);
    }

    public static Utils.Task<CommandResult> execCmdAsync(List<String> commands, boolean isRooted, Utils.Callback<CommandResult> callback) {
        return execCmdAsync(commands == null ? null : (String[]) commands.toArray(new String[0]), isRooted, true, callback);
    }

    public static Utils.Task<CommandResult> execCmdAsync(String[] commands, boolean isRooted, Utils.Callback<CommandResult> callback) {
        return execCmdAsync(commands, isRooted, true, callback);
    }

    public static Utils.Task<CommandResult> execCmdAsync(String command, boolean isRooted, boolean isNeedResultMsg, Utils.Callback<CommandResult> callback) {
        return execCmdAsync(new String[]{command}, isRooted, isNeedResultMsg, callback);
    }

    public static Utils.Task<CommandResult> execCmdAsync(List<String> commands, boolean isRooted, boolean isNeedResultMsg, Utils.Callback<CommandResult> callback) {
        return execCmdAsync(commands == null ? null : (String[]) commands.toArray(new String[0]), isRooted, isNeedResultMsg, callback);
    }

    public static Utils.Task<CommandResult> execCmdAsync(final String[] commands, final boolean isRooted, final boolean isNeedResultMsg, @NonNull Utils.Callback<CommandResult> callback) {
        return Utils.doAsync(new Utils.Task<CommandResult>(callback) {
            public CommandResult doInBackground() {
                return ShellUtils.execCmd(commands, isRooted, isNeedResultMsg);
            }
        });
    }

    public static CommandResult execCmd(String command, boolean isRooted) {
        return execCmd(new String[]{command}, isRooted, true);
    }

    public static CommandResult execCmd(List<String> commands, boolean isRooted) {
        return execCmd(commands == null ? null : (String[]) commands.toArray(new String[0]), isRooted, true);
    }

    public static CommandResult execCmd(String[] commands, boolean isRooted) {
        return execCmd(commands, isRooted, true);
    }

    public static CommandResult execCmd(String command, boolean isRooted, boolean isNeedResultMsg) {
        return execCmd(new String[]{command}, isRooted, isNeedResultMsg);
    }

    public static CommandResult execCmd(List<String> commands, boolean isRooted, boolean isNeedResultMsg) {
        return execCmd(commands == null ? null : (String[]) commands.toArray(new String[0]), isRooted, isNeedResultMsg);
    }

    /* JADX WARNING: Removed duplicated region for block: B:112:0x0192  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x019a  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006e A[SYNTHETIC, Splitter:B:27:0x006e] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0073 A[SYNTHETIC, Splitter:B:30:0x0073] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0078 A[SYNTHETIC, Splitter:B:33:0x0078] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0133 A[SYNTHETIC, Splitter:B:71:0x0133] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0138 A[SYNTHETIC, Splitter:B:74:0x0138] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x013d A[SYNTHETIC, Splitter:B:77:0x013d] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0142  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.doraemon.util.ShellUtils.CommandResult execCmd(java.lang.String[] r21, boolean r22, boolean r23) {
        /*
            r13 = -1
            if (r21 == 0) goto L_0x000a
            r0 = r21
            int r0 = r0.length
            r18 = r0
            if (r18 != 0) goto L_0x001a
        L_0x000a:
            com.doraemon.util.ShellUtils$CommandResult r18 = new com.doraemon.util.ShellUtils$CommandResult
            java.lang.String r19 = ""
            java.lang.String r20 = ""
            r0 = r18
            r1 = r19
            r2 = r20
            r0.<init>(r13, r1, r2)
        L_0x0019:
            return r18
        L_0x001a:
            r12 = 0
            r16 = 0
            r7 = 0
            r14 = 0
            r5 = 0
            r10 = 0
            java.lang.Runtime r19 = java.lang.Runtime.getRuntime()     // Catch:{ Exception -> 0x01b6 }
            if (r22 == 0) goto L_0x0050
            java.lang.String r18 = "su"
        L_0x0029:
            r0 = r19
            r1 = r18
            java.lang.Process r12 = r0.exec(r1)     // Catch:{ Exception -> 0x01b6 }
            java.io.DataOutputStream r11 = new java.io.DataOutputStream     // Catch:{ Exception -> 0x01b6 }
            java.io.OutputStream r18 = r12.getOutputStream()     // Catch:{ Exception -> 0x01b6 }
            r0 = r18
            r11.<init>(r0)     // Catch:{ Exception -> 0x01b6 }
            r0 = r21
            int r0 = r0.length     // Catch:{ Exception -> 0x0067, all -> 0x01a2 }
            r19 = r0
            r18 = 0
        L_0x0043:
            r0 = r18
            r1 = r19
            if (r0 >= r1) goto L_0x0098
            r3 = r21[r18]     // Catch:{ Exception -> 0x0067, all -> 0x01a2 }
            if (r3 != 0) goto L_0x0053
        L_0x004d:
            int r18 = r18 + 1
            goto L_0x0043
        L_0x0050:
            java.lang.String r18 = "sh"
            goto L_0x0029
        L_0x0053:
            byte[] r20 = r3.getBytes()     // Catch:{ Exception -> 0x0067, all -> 0x01a2 }
            r0 = r20
            r11.write(r0)     // Catch:{ Exception -> 0x0067, all -> 0x01a2 }
            java.lang.String r20 = LINE_SEP     // Catch:{ Exception -> 0x0067, all -> 0x01a2 }
            r0 = r20
            r11.writeBytes(r0)     // Catch:{ Exception -> 0x0067, all -> 0x01a2 }
            r11.flush()     // Catch:{ Exception -> 0x0067, all -> 0x01a2 }
            goto L_0x004d
        L_0x0067:
            r4 = move-exception
            r10 = r11
        L_0x0069:
            r4.printStackTrace()     // Catch:{ all -> 0x01a0 }
            if (r10 == 0) goto L_0x0071
            r10.close()     // Catch:{ IOException -> 0x0171 }
        L_0x0071:
            if (r16 == 0) goto L_0x0076
            r16.close()     // Catch:{ IOException -> 0x0177 }
        L_0x0076:
            if (r7 == 0) goto L_0x007b
            r7.close()     // Catch:{ IOException -> 0x017d }
        L_0x007b:
            if (r12 == 0) goto L_0x0080
            r12.destroy()
        L_0x0080:
            com.doraemon.util.ShellUtils$CommandResult r19 = new com.doraemon.util.ShellUtils$CommandResult
            if (r14 != 0) goto L_0x0192
            java.lang.String r18 = ""
            r20 = r18
        L_0x0088:
            if (r5 != 0) goto L_0x019a
            java.lang.String r18 = ""
        L_0x008c:
            r0 = r19
            r1 = r20
            r2 = r18
            r0.<init>(r13, r1, r2)
            r18 = r19
            goto L_0x0019
        L_0x0098:
            java.lang.StringBuilder r18 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0067, all -> 0x01a2 }
            r18.<init>()     // Catch:{ Exception -> 0x0067, all -> 0x01a2 }
            java.lang.String r19 = "exit"
            java.lang.StringBuilder r18 = r18.append(r19)     // Catch:{ Exception -> 0x0067, all -> 0x01a2 }
            java.lang.String r19 = LINE_SEP     // Catch:{ Exception -> 0x0067, all -> 0x01a2 }
            java.lang.StringBuilder r18 = r18.append(r19)     // Catch:{ Exception -> 0x0067, all -> 0x01a2 }
            java.lang.String r18 = r18.toString()     // Catch:{ Exception -> 0x0067, all -> 0x01a2 }
            r0 = r18
            r11.writeBytes(r0)     // Catch:{ Exception -> 0x0067, all -> 0x01a2 }
            r11.flush()     // Catch:{ Exception -> 0x0067, all -> 0x01a2 }
            int r13 = r12.waitFor()     // Catch:{ Exception -> 0x0067, all -> 0x01a2 }
            if (r23 == 0) goto L_0x014b
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0067, all -> 0x01a2 }
            r15.<init>()     // Catch:{ Exception -> 0x0067, all -> 0x01a2 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b9, all -> 0x01a5 }
            r6.<init>()     // Catch:{ Exception -> 0x01b9, all -> 0x01a5 }
            java.io.BufferedReader r17 = new java.io.BufferedReader     // Catch:{ Exception -> 0x01be, all -> 0x01a9 }
            java.io.InputStreamReader r18 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x01be, all -> 0x01a9 }
            java.io.InputStream r19 = r12.getInputStream()     // Catch:{ Exception -> 0x01be, all -> 0x01a9 }
            java.lang.String r20 = "UTF-8"
            r18.<init>(r19, r20)     // Catch:{ Exception -> 0x01be, all -> 0x01a9 }
            r17.<init>(r18)     // Catch:{ Exception -> 0x01be, all -> 0x01a9 }
            java.io.BufferedReader r8 = new java.io.BufferedReader     // Catch:{ Exception -> 0x01c4, all -> 0x01ae }
            java.io.InputStreamReader r18 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x01c4, all -> 0x01ae }
            java.io.InputStream r19 = r12.getErrorStream()     // Catch:{ Exception -> 0x01c4, all -> 0x01ae }
            java.lang.String r20 = "UTF-8"
            r18.<init>(r19, r20)     // Catch:{ Exception -> 0x01c4, all -> 0x01ae }
            r0 = r18
            r8.<init>(r0)     // Catch:{ Exception -> 0x01c4, all -> 0x01ae }
            java.lang.String r9 = r17.readLine()     // Catch:{ Exception -> 0x0104, all -> 0x012a }
            if (r9 == 0) goto L_0x010d
            r15.append(r9)     // Catch:{ Exception -> 0x0104, all -> 0x012a }
        L_0x00f0:
            java.lang.String r9 = r17.readLine()     // Catch:{ Exception -> 0x0104, all -> 0x012a }
            if (r9 == 0) goto L_0x010d
            java.lang.String r18 = LINE_SEP     // Catch:{ Exception -> 0x0104, all -> 0x012a }
            r0 = r18
            java.lang.StringBuilder r18 = r15.append(r0)     // Catch:{ Exception -> 0x0104, all -> 0x012a }
            r0 = r18
            r0.append(r9)     // Catch:{ Exception -> 0x0104, all -> 0x012a }
            goto L_0x00f0
        L_0x0104:
            r4 = move-exception
            r10 = r11
            r5 = r6
            r14 = r15
            r7 = r8
            r16 = r17
            goto L_0x0069
        L_0x010d:
            java.lang.String r9 = r8.readLine()     // Catch:{ Exception -> 0x0104, all -> 0x012a }
            if (r9 == 0) goto L_0x0146
            r6.append(r9)     // Catch:{ Exception -> 0x0104, all -> 0x012a }
        L_0x0116:
            java.lang.String r9 = r8.readLine()     // Catch:{ Exception -> 0x0104, all -> 0x012a }
            if (r9 == 0) goto L_0x0146
            java.lang.String r18 = LINE_SEP     // Catch:{ Exception -> 0x0104, all -> 0x012a }
            r0 = r18
            java.lang.StringBuilder r18 = r6.append(r0)     // Catch:{ Exception -> 0x0104, all -> 0x012a }
            r0 = r18
            r0.append(r9)     // Catch:{ Exception -> 0x0104, all -> 0x012a }
            goto L_0x0116
        L_0x012a:
            r18 = move-exception
            r10 = r11
            r5 = r6
            r14 = r15
            r7 = r8
            r16 = r17
        L_0x0131:
            if (r10 == 0) goto L_0x0136
            r10.close()     // Catch:{ IOException -> 0x0183 }
        L_0x0136:
            if (r16 == 0) goto L_0x013b
            r16.close()     // Catch:{ IOException -> 0x0188 }
        L_0x013b:
            if (r7 == 0) goto L_0x0140
            r7.close()     // Catch:{ IOException -> 0x018d }
        L_0x0140:
            if (r12 == 0) goto L_0x0145
            r12.destroy()
        L_0x0145:
            throw r18
        L_0x0146:
            r5 = r6
            r14 = r15
            r7 = r8
            r16 = r17
        L_0x014b:
            if (r11 == 0) goto L_0x0150
            r11.close()     // Catch:{ IOException -> 0x0162 }
        L_0x0150:
            if (r16 == 0) goto L_0x0155
            r16.close()     // Catch:{ IOException -> 0x0167 }
        L_0x0155:
            if (r7 == 0) goto L_0x015a
            r7.close()     // Catch:{ IOException -> 0x016c }
        L_0x015a:
            if (r12 == 0) goto L_0x01cc
            r12.destroy()
            r10 = r11
            goto L_0x0080
        L_0x0162:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x0150
        L_0x0167:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x0155
        L_0x016c:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x015a
        L_0x0171:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x0071
        L_0x0177:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x0076
        L_0x017d:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x007b
        L_0x0183:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x0136
        L_0x0188:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x013b
        L_0x018d:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x0140
        L_0x0192:
            java.lang.String r18 = r14.toString()
            r20 = r18
            goto L_0x0088
        L_0x019a:
            java.lang.String r18 = r5.toString()
            goto L_0x008c
        L_0x01a0:
            r18 = move-exception
            goto L_0x0131
        L_0x01a2:
            r18 = move-exception
            r10 = r11
            goto L_0x0131
        L_0x01a5:
            r18 = move-exception
            r10 = r11
            r14 = r15
            goto L_0x0131
        L_0x01a9:
            r18 = move-exception
            r10 = r11
            r5 = r6
            r14 = r15
            goto L_0x0131
        L_0x01ae:
            r18 = move-exception
            r10 = r11
            r5 = r6
            r14 = r15
            r16 = r17
            goto L_0x0131
        L_0x01b6:
            r4 = move-exception
            goto L_0x0069
        L_0x01b9:
            r4 = move-exception
            r10 = r11
            r14 = r15
            goto L_0x0069
        L_0x01be:
            r4 = move-exception
            r10 = r11
            r5 = r6
            r14 = r15
            goto L_0x0069
        L_0x01c4:
            r4 = move-exception
            r10 = r11
            r5 = r6
            r14 = r15
            r16 = r17
            goto L_0x0069
        L_0x01cc:
            r10 = r11
            goto L_0x0080
        */
        throw new UnsupportedOperationException("Method not decompiled: com.doraemon.util.ShellUtils.execCmd(java.lang.String[], boolean, boolean):com.doraemon.util.ShellUtils$CommandResult");
    }

    public static class CommandResult {
        public String errorMsg;
        public int result;
        public String successMsg;

        public CommandResult(int result2, String successMsg2, String errorMsg2) {
            this.result = result2;
            this.successMsg = successMsg2;
            this.errorMsg = errorMsg2;
        }

        public String toString() {
            return "result: " + this.result + "\nsuccessMsg: " + this.successMsg + "\nerrorMsg: " + this.errorMsg;
        }
    }
}
