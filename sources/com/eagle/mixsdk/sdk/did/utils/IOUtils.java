package com.eagle.mixsdk.sdk.did.utils;

import com.eagle.mixsdk.sdk.did.config.DIDConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IOUtils {
    private static final String LINE_SEP = System.getProperty("line.separator");

    public static String readFile2String(File file, String charsetName) {
        BufferedReader reader;
        String str = null;
        if (file.exists()) {
            BufferedReader reader2 = null;
            try {
                StringBuilder sb = new StringBuilder();
                if (isSpace(charsetName)) {
                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                } else {
                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charsetName));
                }
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    sb.append(line).append(LINE_SEP);
                }
                str = sb.delete(sb.length() - LINE_SEP.length(), sb.length()).toString();
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e2) {
                e2.printStackTrace();
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
        }
        return str;
    }

    public static String readFile2StringPattern(File file, String charsetName) {
        BufferedReader reader;
        String str = null;
        if (file.exists()) {
            BufferedReader reader2 = null;
            try {
                StringBuilder sb = new StringBuilder();
                if (isSpace(charsetName)) {
                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                } else {
                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charsetName));
                }
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    } else if (DIDConfig.didPattern.matcher(line).matches()) {
                        sb.append(line).append(LINE_SEP);
                    }
                }
                str = sb.delete(sb.length() - LINE_SEP.length(), sb.length()).toString();
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e2) {
                e2.printStackTrace();
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
        }
        return str;
    }

    public static String readInputStream2StringPattern(InputStream inputStream, String charsetName) {
        String str;
        BufferedReader reader;
        BufferedReader reader2 = null;
        try {
            StringBuilder sb = new StringBuilder();
            if (isSpace(charsetName)) {
                reader = new BufferedReader(new InputStreamReader(inputStream));
            } else {
                reader = new BufferedReader(new InputStreamReader(inputStream, charsetName));
            }
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                } else if (DIDConfig.didPattern.matcher(line).matches()) {
                    sb.append(line).append(LINE_SEP);
                }
            }
            str = sb.delete(sb.length() - LINE_SEP.length(), sb.length()).toString();
            if (reader != null) {
                try {
                    reader.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            str = null;
            if (reader2 != null) {
                try {
                    reader2.close();
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        } catch (Throwable th) {
            if (reader2 != null) {
                try {
                    reader2.close();
                    inputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
        return str;
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
}
