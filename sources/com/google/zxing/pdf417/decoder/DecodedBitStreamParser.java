package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.pdf417.PDF417ResultMetadata;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

final class DecodedBitStreamParser {

    /* renamed from: AL */
    private static final int f55AL = 28;

    /* renamed from: AS */
    private static final int f56AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final Charset DEFAULT_ENCODING = Charset.forName("ISO-8859-1");
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final BigInteger[] EXP900;

    /* renamed from: LL */
    private static final int f57LL = 27;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;
    private static final char[] MIXED_CHARS = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();

    /* renamed from: ML */
    private static final int f58ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMBER_OF_SEQUENCE_CODEWORDS = 2;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;

    /* renamed from: PL */
    private static final int f59PL = 25;

    /* renamed from: PS */
    private static final int f60PS = 29;
    private static final char[] PUNCT_CHARS = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;

    private enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        EXP900 = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger nineHundred = BigInteger.valueOf(900);
        EXP900[1] = nineHundred;
        for (int i = 2; i < EXP900.length; i++) {
            EXP900[i] = EXP900[i - 1].multiply(nineHundred);
        }
    }

    private DecodedBitStreamParser() {
    }

    static DecoderResult decode(int[] codewords, String ecLevel) throws FormatException {
        int codeIndex;
        StringBuilder result = new StringBuilder(codewords.length << 1);
        Charset encoding = DEFAULT_ENCODING;
        int codeIndex2 = 1 + 1;
        int code = codewords[1];
        PDF417ResultMetadata resultMetadata = new PDF417ResultMetadata();
        while (codeIndex2 < codewords[0]) {
            switch (code) {
                case TEXT_COMPACTION_MODE_LATCH /*900*/:
                    codeIndex = textCompaction(codewords, codeIndex2, result);
                    break;
                case BYTE_COMPACTION_MODE_LATCH /*901*/:
                case BYTE_COMPACTION_MODE_LATCH_6 /*924*/:
                    codeIndex = byteCompaction(code, codewords, encoding, codeIndex2, result);
                    break;
                case NUMERIC_COMPACTION_MODE_LATCH /*902*/:
                    codeIndex = numericCompaction(codewords, codeIndex2, result);
                    break;
                case MODE_SHIFT_TO_BYTE_COMPACTION_MODE /*913*/:
                    result.append((char) codewords[codeIndex2]);
                    codeIndex = codeIndex2 + 1;
                    break;
                case MACRO_PDF417_TERMINATOR /*922*/:
                case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /*923*/:
                    throw FormatException.getFormatInstance();
                case ECI_USER_DEFINED /*925*/:
                    codeIndex = codeIndex2 + 1;
                    break;
                case ECI_GENERAL_PURPOSE /*926*/:
                    codeIndex = codeIndex2 + 2;
                    break;
                case ECI_CHARSET /*927*/:
                    encoding = Charset.forName(CharacterSetECI.getCharacterSetECIByValue(codewords[codeIndex2]).name());
                    codeIndex = codeIndex2 + 1;
                    break;
                case 928:
                    codeIndex = decodeMacroBlock(codewords, codeIndex2, resultMetadata);
                    break;
                default:
                    codeIndex = textCompaction(codewords, codeIndex2 - 1, result);
                    break;
            }
            if (codeIndex < codewords.length) {
                code = codewords[codeIndex];
                codeIndex2 = codeIndex + 1;
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (result.length() == 0) {
            throw FormatException.getFormatInstance();
        }
        DecoderResult decoderResult = new DecoderResult((byte[]) null, result.toString(), (List<byte[]>) null, ecLevel);
        decoderResult.setOther(resultMetadata);
        return decoderResult;
    }

    private static int decodeMacroBlock(int[] codewords, int codeIndex, PDF417ResultMetadata resultMetadata) throws FormatException {
        if (codeIndex + 2 > codewords[0]) {
            throw FormatException.getFormatInstance();
        }
        int[] segmentIndexArray = new int[2];
        int i = 0;
        while (i < 2) {
            segmentIndexArray[i] = codewords[codeIndex];
            i++;
            codeIndex++;
        }
        resultMetadata.setSegmentIndex(Integer.parseInt(decodeBase900toBase10(segmentIndexArray, 2)));
        StringBuilder fileId = new StringBuilder();
        int codeIndex2 = textCompaction(codewords, codeIndex, fileId);
        resultMetadata.setFileId(fileId.toString());
        if (codewords[codeIndex2] == BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
            int codeIndex3 = codeIndex2 + 1;
            int[] additionalOptionCodeWords = new int[(codewords[0] - codeIndex3)];
            int additionalOptionCodeWordsIndex = 0;
            boolean end = false;
            while (codeIndex3 < codewords[0] && !end) {
                int codeIndex4 = codeIndex3 + 1;
                int code = codewords[codeIndex3];
                if (code < TEXT_COMPACTION_MODE_LATCH) {
                    additionalOptionCodeWords[additionalOptionCodeWordsIndex] = code;
                    additionalOptionCodeWordsIndex++;
                    codeIndex3 = codeIndex4;
                } else {
                    switch (code) {
                        case MACRO_PDF417_TERMINATOR /*922*/:
                            resultMetadata.setLastSegment(true);
                            codeIndex3 = codeIndex4 + 1;
                            end = true;
                            break;
                        default:
                            throw FormatException.getFormatInstance();
                    }
                }
            }
            resultMetadata.setOptionalData(Arrays.copyOf(additionalOptionCodeWords, additionalOptionCodeWordsIndex));
            return codeIndex3;
        } else if (codewords[codeIndex2] != MACRO_PDF417_TERMINATOR) {
            return codeIndex2;
        } else {
            resultMetadata.setLastSegment(true);
            return codeIndex2 + 1;
        }
    }

    private static int textCompaction(int[] codewords, int codeIndex, StringBuilder result) {
        int[] textCompactionData = new int[((codewords[0] - codeIndex) << 1)];
        int[] byteCompactionData = new int[((codewords[0] - codeIndex) << 1)];
        int index = 0;
        boolean end = false;
        while (codeIndex < codewords[0] && !end) {
            int codeIndex2 = codeIndex + 1;
            int code = codewords[codeIndex];
            if (code >= TEXT_COMPACTION_MODE_LATCH) {
                switch (code) {
                    case TEXT_COMPACTION_MODE_LATCH /*900*/:
                        textCompactionData[index] = TEXT_COMPACTION_MODE_LATCH;
                        index++;
                        codeIndex = codeIndex2;
                        break;
                    case BYTE_COMPACTION_MODE_LATCH /*901*/:
                    case NUMERIC_COMPACTION_MODE_LATCH /*902*/:
                    case MACRO_PDF417_TERMINATOR /*922*/:
                    case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /*923*/:
                    case BYTE_COMPACTION_MODE_LATCH_6 /*924*/:
                    case 928:
                        codeIndex = codeIndex2 - 1;
                        end = true;
                        break;
                    case MODE_SHIFT_TO_BYTE_COMPACTION_MODE /*913*/:
                        textCompactionData[index] = MODE_SHIFT_TO_BYTE_COMPACTION_MODE;
                        codeIndex = codeIndex2 + 1;
                        byteCompactionData[index] = codewords[codeIndex2];
                        index++;
                        break;
                    default:
                        codeIndex = codeIndex2;
                        break;
                }
            } else {
                textCompactionData[index] = code / 30;
                textCompactionData[index + 1] = code % 30;
                index += 2;
                codeIndex = codeIndex2;
            }
        }
        decodeTextCompaction(textCompactionData, byteCompactionData, index, result);
        return codeIndex;
    }

    private static void decodeTextCompaction(int[] textCompactionData, int[] byteCompactionData, int length, StringBuilder result) {
        Mode subMode = Mode.ALPHA;
        Mode priorToShiftMode = Mode.ALPHA;
        for (int i = 0; i < length; i++) {
            int subModeCh = textCompactionData[i];
            char ch = 0;
            switch (subMode) {
                case ALPHA:
                    if (subModeCh >= 26) {
                        if (subModeCh != 26) {
                            if (subModeCh != 27) {
                                if (subModeCh != 28) {
                                    if (subModeCh != 29) {
                                        if (subModeCh != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                            if (subModeCh == TEXT_COMPACTION_MODE_LATCH) {
                                                subMode = Mode.ALPHA;
                                                break;
                                            }
                                        } else {
                                            result.append((char) byteCompactionData[i]);
                                            break;
                                        }
                                    } else {
                                        priorToShiftMode = subMode;
                                        subMode = Mode.PUNCT_SHIFT;
                                        break;
                                    }
                                } else {
                                    subMode = Mode.MIXED;
                                    break;
                                }
                            } else {
                                subMode = Mode.LOWER;
                                break;
                            }
                        } else {
                            ch = ' ';
                            break;
                        }
                    } else {
                        ch = (char) (subModeCh + 65);
                        break;
                    }
                    break;
                case LOWER:
                    if (subModeCh >= 26) {
                        if (subModeCh != 26) {
                            if (subModeCh != 27) {
                                if (subModeCh != 28) {
                                    if (subModeCh != 29) {
                                        if (subModeCh != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                            if (subModeCh == TEXT_COMPACTION_MODE_LATCH) {
                                                subMode = Mode.ALPHA;
                                                break;
                                            }
                                        } else {
                                            result.append((char) byteCompactionData[i]);
                                            break;
                                        }
                                    } else {
                                        priorToShiftMode = subMode;
                                        subMode = Mode.PUNCT_SHIFT;
                                        break;
                                    }
                                } else {
                                    subMode = Mode.MIXED;
                                    break;
                                }
                            } else {
                                priorToShiftMode = subMode;
                                subMode = Mode.ALPHA_SHIFT;
                                break;
                            }
                        } else {
                            ch = ' ';
                            break;
                        }
                    } else {
                        ch = (char) (subModeCh + 97);
                        break;
                    }
                    break;
                case MIXED:
                    if (subModeCh >= 25) {
                        if (subModeCh != 25) {
                            if (subModeCh != 26) {
                                if (subModeCh != 27) {
                                    if (subModeCh != 28) {
                                        if (subModeCh != 29) {
                                            if (subModeCh != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                                if (subModeCh == TEXT_COMPACTION_MODE_LATCH) {
                                                    subMode = Mode.ALPHA;
                                                    break;
                                                }
                                            } else {
                                                result.append((char) byteCompactionData[i]);
                                                break;
                                            }
                                        } else {
                                            priorToShiftMode = subMode;
                                            subMode = Mode.PUNCT_SHIFT;
                                            break;
                                        }
                                    } else {
                                        subMode = Mode.ALPHA;
                                        break;
                                    }
                                } else {
                                    subMode = Mode.LOWER;
                                    break;
                                }
                            } else {
                                ch = ' ';
                                break;
                            }
                        } else {
                            subMode = Mode.PUNCT;
                            break;
                        }
                    } else {
                        ch = MIXED_CHARS[subModeCh];
                        break;
                    }
                    break;
                case PUNCT:
                    if (subModeCh >= 29) {
                        if (subModeCh != 29) {
                            if (subModeCh != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                if (subModeCh == TEXT_COMPACTION_MODE_LATCH) {
                                    subMode = Mode.ALPHA;
                                    break;
                                }
                            } else {
                                result.append((char) byteCompactionData[i]);
                                break;
                            }
                        } else {
                            subMode = Mode.ALPHA;
                            break;
                        }
                    } else {
                        ch = PUNCT_CHARS[subModeCh];
                        break;
                    }
                    break;
                case ALPHA_SHIFT:
                    subMode = priorToShiftMode;
                    if (subModeCh >= 26) {
                        if (subModeCh != 26) {
                            if (subModeCh == TEXT_COMPACTION_MODE_LATCH) {
                                subMode = Mode.ALPHA;
                                break;
                            }
                        } else {
                            ch = ' ';
                            break;
                        }
                    } else {
                        ch = (char) (subModeCh + 65);
                        break;
                    }
                    break;
                case PUNCT_SHIFT:
                    subMode = priorToShiftMode;
                    if (subModeCh >= 29) {
                        if (subModeCh != 29) {
                            if (subModeCh != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                if (subModeCh == TEXT_COMPACTION_MODE_LATCH) {
                                    subMode = Mode.ALPHA;
                                    break;
                                }
                            } else {
                                result.append((char) byteCompactionData[i]);
                                break;
                            }
                        } else {
                            subMode = Mode.ALPHA;
                            break;
                        }
                    } else {
                        ch = PUNCT_CHARS[subModeCh];
                        break;
                    }
                    break;
            }
            if (ch != 0) {
                result.append(ch);
            }
        }
    }

    private static int byteCompaction(int mode, int[] codewords, Charset encoding, int codeIndex, StringBuilder result) {
        ByteArrayOutputStream decodedBytes = new ByteArrayOutputStream();
        if (mode == BYTE_COMPACTION_MODE_LATCH) {
            int count = 0;
            long value = 0;
            int[] byteCompactedCodewords = new int[6];
            boolean end = false;
            int nextCode = codewords[codeIndex];
            codeIndex++;
            while (codeIndex < codewords[0] && !end) {
                int count2 = count + 1;
                byteCompactedCodewords[count] = nextCode;
                value = (900 * value) + ((long) nextCode);
                int codeIndex2 = codeIndex + 1;
                nextCode = codewords[codeIndex];
                if (nextCode == TEXT_COMPACTION_MODE_LATCH || nextCode == BYTE_COMPACTION_MODE_LATCH || nextCode == NUMERIC_COMPACTION_MODE_LATCH || nextCode == BYTE_COMPACTION_MODE_LATCH_6 || nextCode == 928 || nextCode == BEGIN_MACRO_PDF417_OPTIONAL_FIELD || nextCode == MACRO_PDF417_TERMINATOR) {
                    codeIndex = codeIndex2 - 1;
                    end = true;
                    count = count2;
                } else if (count2 % 5 != 0 || count2 <= 0) {
                    count = count2;
                    codeIndex = codeIndex2;
                } else {
                    for (int j = 0; j < 6; j++) {
                        decodedBytes.write((byte) ((int) (value >> ((5 - j) * 8))));
                    }
                    value = 0;
                    count = 0;
                    codeIndex = codeIndex2;
                }
            }
            if (codeIndex == codewords[0] && nextCode < TEXT_COMPACTION_MODE_LATCH) {
                byteCompactedCodewords[count] = nextCode;
                count++;
            }
            for (int i = 0; i < count; i++) {
                decodedBytes.write((byte) byteCompactedCodewords[i]);
            }
        } else if (mode == BYTE_COMPACTION_MODE_LATCH_6) {
            int count3 = 0;
            long value2 = 0;
            boolean end2 = false;
            while (codeIndex < codewords[0] && !end2) {
                int codeIndex3 = codeIndex + 1;
                int code = codewords[codeIndex];
                if (code < TEXT_COMPACTION_MODE_LATCH) {
                    count3++;
                    value2 = (900 * value2) + ((long) code);
                    codeIndex = codeIndex3;
                } else if (code == TEXT_COMPACTION_MODE_LATCH || code == BYTE_COMPACTION_MODE_LATCH || code == NUMERIC_COMPACTION_MODE_LATCH || code == BYTE_COMPACTION_MODE_LATCH_6 || code == 928 || code == BEGIN_MACRO_PDF417_OPTIONAL_FIELD || code == MACRO_PDF417_TERMINATOR) {
                    codeIndex = codeIndex3 - 1;
                    end2 = true;
                } else {
                    codeIndex = codeIndex3;
                }
                if (count3 % 5 == 0 && count3 > 0) {
                    for (int j2 = 0; j2 < 6; j2++) {
                        decodedBytes.write((byte) ((int) (value2 >> ((5 - j2) * 8))));
                    }
                    value2 = 0;
                    count3 = 0;
                }
            }
        }
        result.append(new String(decodedBytes.toByteArray(), encoding));
        return codeIndex;
    }

    private static int numericCompaction(int[] codewords, int codeIndex, StringBuilder result) throws FormatException {
        int count = 0;
        boolean end = false;
        int[] numericCodewords = new int[15];
        while (codeIndex < codewords[0] && !end) {
            int codeIndex2 = codeIndex + 1;
            int code = codewords[codeIndex];
            if (codeIndex2 == codewords[0]) {
                end = true;
            }
            if (code < TEXT_COMPACTION_MODE_LATCH) {
                numericCodewords[count] = code;
                count++;
                codeIndex = codeIndex2;
            } else if (code == TEXT_COMPACTION_MODE_LATCH || code == BYTE_COMPACTION_MODE_LATCH || code == BYTE_COMPACTION_MODE_LATCH_6 || code == 928 || code == BEGIN_MACRO_PDF417_OPTIONAL_FIELD || code == MACRO_PDF417_TERMINATOR) {
                codeIndex = codeIndex2 - 1;
                end = true;
            } else {
                codeIndex = codeIndex2;
            }
            if ((count % 15 == 0 || code == NUMERIC_COMPACTION_MODE_LATCH || end) && count > 0) {
                result.append(decodeBase900toBase10(numericCodewords, count));
                count = 0;
            }
        }
        return codeIndex;
    }

    private static String decodeBase900toBase10(int[] codewords, int count) throws FormatException {
        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i < count; i++) {
            result = result.add(EXP900[(count - i) - 1].multiply(BigInteger.valueOf((long) codewords[i])));
        }
        String resultString = result.toString();
        if (resultString.charAt(0) == '1') {
            return resultString.substring(1);
        }
        throw FormatException.getFormatInstance();
    }
}
