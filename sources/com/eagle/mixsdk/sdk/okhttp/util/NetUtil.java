package com.eagle.mixsdk.sdk.okhttp.util;

import com.doraemon.util.ShellAdbUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetUtil {
    private static Logger logger = Logger.getLogger("NetPing");

    public static String[] pingHost(int pingNum, String host) {
        try {
            logf("┏========================PING 主机IP:%s是否可用STAR========================┓\n", host);
            Process p = Runtime.getRuntime().exec("/system/bin/ping -c " + pingNum + " -w 100 " + host);
            int status = p.waitFor();
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuffer buffer = new StringBuffer();
            double avg = 0.0d;
            while (true) {
                String line = in.readLine();
                if (line == null) {
                    break;
                }
                if (line.contains("avg")) {
                    String tempLine = line;
                    int i = 0;
                    while (true) {
                        if (i >= 6) {
                            break;
                        } else if (i == 4) {
                            avg = Double.parseDouble(tempLine.substring(0, tempLine.indexOf("/")));
                            break;
                        } else {
                            tempLine = tempLine.substring(tempLine.indexOf("/") + 1);
                            i++;
                        }
                    }
                }
                buffer.append(line).append(ShellAdbUtil.COMMAND_LINE_END);
                log("info： " + line);
            }
            if (status == 0) {
                log("avgValue： " + Math.round(avg));
                log("info success......");
                logf("┗========================PING 主机IP:%s是否可用END========================┛\n", host);
            } else {
                log("info failed......" + status + "...");
                logf("┗========================PING 主机IP:%s是否可用END========================┛\n", host);
            }
            return new String[]{host, Math.round(avg) + "", buffer.toString()};
        } catch (IOException e) {
            return null;
        } catch (InterruptedException e2) {
            return null;
        }
    }

    private static void log(String message) {
        logger.log(Level.INFO, message);
    }

    private static void logf(String message, String formatArgs) {
        logger.log(Level.INFO, String.format(message, new Object[]{formatArgs}));
    }
}
