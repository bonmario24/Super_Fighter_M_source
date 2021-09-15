package com.google.android.gms.internal.clearcut;

final class zzet {
    static String zzc(zzbb zzbb) {
        zzeu zzeu = new zzeu(zzbb);
        StringBuilder sb = new StringBuilder(zzeu.size());
        for (int i = 0; i < zzeu.size(); i++) {
            byte zzj = zzeu.zzj(i);
            switch (zzj) {
                case 7:
                    sb.append("\\a");
                    break;
                case 8:
                    sb.append("\\b");
                    break;
                case 9:
                    sb.append("\\t");
                    break;
                case 10:
                    sb.append("\\n");
                    break;
                case 11:
                    sb.append("\\v");
                    break;
                case 12:
                    sb.append("\\f");
                    break;
                case 13:
                    sb.append("\\r");
                    break;
                case 34:
                    sb.append("\\\"");
                    break;
                case 39:
                    sb.append("\\'");
                    break;
                case 92:
                    sb.append("\\\\");
                    break;
                default:
                    if (zzj >= 32 && zzj <= 126) {
                        sb.append((char) zzj);
                        break;
                    } else {
                        sb.append('\\');
                        sb.append((char) (((zzj >>> 6) & 3) + 48));
                        sb.append((char) (((zzj >>> 3) & 7) + 48));
                        sb.append((char) ((zzj & 7) + 48));
                        break;
                    }
            }
        }
        return sb.toString();
    }
}
