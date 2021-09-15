package com.appsflyer.internal;

import android.util.Base64;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.appsflyer.internal.j */
public final class C0945j {

    /* renamed from: ı */
    public String f606;

    /* renamed from: ǃ */
    public String f607;

    /* renamed from: ɩ */
    public String f608;

    /* renamed from: ι */
    private byte[] f609;

    C0945j() {
    }

    public C0945j(String str, byte[] bArr, String str2) {
        this.f606 = str;
        this.f609 = bArr;
        this.f608 = str2;
    }

    C0945j(char[] cArr) {
        int i;
        Scanner scanner = new Scanner(new String(cArr));
        int i2 = 0;
        int i3 = 0;
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            if (nextLine.startsWith("url=")) {
                this.f606 = nextLine.substring(4).trim();
            } else if (nextLine.startsWith("version=")) {
                this.f608 = nextLine.substring(8).trim();
                Matcher matcher = Pattern.compile("^(0|[1-9]\\d*)\\.(0|[1-9]\\d*)\\.(0|[1-9]\\d*)(?:-((?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*)(?:\\.(?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*))*))?(?:\\+([0-9a-zA-Z-]+(?:\\.[0-9a-zA-Z-]+)*))?$").matcher(this.f608);
                if (matcher.matches()) {
                    i = Integer.parseInt(matcher.group(1));
                    i2 = Integer.parseInt(matcher.group(2));
                } else {
                    i = i3;
                }
                i3 = i;
            } else if (nextLine.startsWith("data=")) {
                String trim = nextLine.substring(5).trim();
                this.f609 = (i3 > 4 || i2 >= 11) ? Base64.decode(trim, 2) : trim.getBytes();
            }
        }
        scanner.close();
    }

    /* renamed from: ǃ */
    public final byte[] mo14711() {
        return this.f609;
    }
}
