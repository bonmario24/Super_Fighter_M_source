package com.google.zxing.pdf417.decoder;

import java.util.Formatter;

class DetectionResultColumn {
    private static final int MAX_NEARBY_DISTANCE = 5;
    private final BoundingBox boundingBox;
    private final Codeword[] codewords;

    DetectionResultColumn(BoundingBox boundingBox2) {
        this.boundingBox = new BoundingBox(boundingBox2);
        this.codewords = new Codeword[((boundingBox2.getMaxY() - boundingBox2.getMinY()) + 1)];
    }

    /* access modifiers changed from: package-private */
    public final Codeword getCodewordNearby(int imageRow) {
        Codeword codeword = getCodeword(imageRow);
        if (codeword != null) {
            Codeword codeword2 = codeword;
            return codeword;
        }
        int i = 1;
        while (i < 5) {
            int nearImageRow = imageRowToCodewordIndex(imageRow) - i;
            if (nearImageRow < 0 || (codeword = this.codewords[nearImageRow]) == null) {
                int nearImageRow2 = imageRowToCodewordIndex(imageRow) + i;
                if (nearImageRow2 >= this.codewords.length || (codeword = this.codewords[nearImageRow2]) == null) {
                    i++;
                } else {
                    Codeword codeword3 = codeword;
                    return codeword;
                }
            } else {
                Codeword codeword4 = codeword;
                return codeword;
            }
        }
        Codeword codeword5 = codeword;
        return null;
    }

    /* access modifiers changed from: package-private */
    public final int imageRowToCodewordIndex(int imageRow) {
        return imageRow - this.boundingBox.getMinY();
    }

    /* access modifiers changed from: package-private */
    public final void setCodeword(int imageRow, Codeword codeword) {
        this.codewords[imageRowToCodewordIndex(imageRow)] = codeword;
    }

    /* access modifiers changed from: package-private */
    public final Codeword getCodeword(int imageRow) {
        return this.codewords[imageRowToCodewordIndex(imageRow)];
    }

    /* access modifiers changed from: package-private */
    public final BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    /* access modifiers changed from: package-private */
    public final Codeword[] getCodewords() {
        return this.codewords;
    }

    public String toString() {
        int row;
        Formatter formatter = new Formatter();
        Codeword[] codewordArr = this.codewords;
        int length = codewordArr.length;
        int i = 0;
        int row2 = 0;
        while (i < length) {
            Codeword codeword = codewordArr[i];
            if (codeword == null) {
                row = row2 + 1;
                formatter.format("%3d:    |   %n", new Object[]{Integer.valueOf(row2)});
            } else {
                row = row2 + 1;
                formatter.format("%3d: %3d|%3d%n", new Object[]{Integer.valueOf(row2), Integer.valueOf(codeword.getRowNumber()), Integer.valueOf(codeword.getValue())});
            }
            i++;
            row2 = row;
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
