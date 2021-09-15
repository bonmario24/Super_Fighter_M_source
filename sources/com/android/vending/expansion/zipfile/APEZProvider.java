package com.android.vending.expansion.zipfile;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.android.vending.expansion.zipfile.ZipResourceFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public abstract class APEZProvider extends ContentProvider {
    public static final String[] ALL_FIELDS = {FILEID, FILENAME, ZIPFILE, MODIFICATION, CRC32, COMPRESSEDLEN, UNCOMPRESSEDLEN, COMPRESSIONTYPE};
    public static final int[] ALL_FIELDS_INT;
    public static final int COMPLEN_IDX = 5;
    public static final String COMPRESSEDLEN = "ZCOL";
    public static final String COMPRESSIONTYPE = "ZTYP";
    public static final int COMPTYPE_IDX = 7;
    public static final String CRC32 = "ZCRC";
    public static final int CRC_IDX = 4;
    public static final String FILEID = "_id";
    public static final int FILEID_IDX = 0;
    public static final String FILENAME = "ZPFN";
    public static final int FILENAME_IDX = 1;
    public static final String MODIFICATION = "ZMOD";
    public static final int MOD_IDX = 3;
    private static final String NO_FILE = "N";
    public static final int UNCOMPLEN_IDX = 6;
    public static final String UNCOMPRESSEDLEN = "ZUNL";
    public static final String ZIPFILE = "ZFIL";
    public static final int ZIPFILE_IDX = 2;
    private ZipResourceFile mAPKExtensionFile;
    private boolean mInit;

    static {
        int[] iArr = new int[8];
        iArr[1] = 1;
        iArr[2] = 2;
        iArr[3] = 3;
        iArr[4] = 4;
        iArr[5] = 5;
        iArr[6] = 6;
        iArr[7] = 7;
        ALL_FIELDS_INT = iArr;
    }

    private boolean initIfNecessary() {
        int i;
        int i2;
        if (!this.mInit) {
            Context context = getContext();
            PackageManager packageManager = context.getPackageManager();
            ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(getAuthority(), 128);
            try {
                int i3 = packageManager.getPackageInfo(context.getPackageName(), 0).versionCode;
                String[] strArr = null;
                if (resolveContentProvider.metaData != null) {
                    i2 = resolveContentProvider.metaData.getInt("mainVersion", i3);
                    i = resolveContentProvider.metaData.getInt("patchVersion", i3);
                    String string = resolveContentProvider.metaData.getString("mainFilename", NO_FILE);
                    if (NO_FILE != string) {
                        String string2 = resolveContentProvider.metaData.getString("patchFilename", NO_FILE);
                        strArr = NO_FILE != string2 ? new String[]{string, string2} : new String[]{string};
                    }
                } else {
                    i = i3;
                    i2 = i3;
                }
                if (strArr == null) {
                    try {
                        this.mAPKExtensionFile = APKExpansionSupport.getAPKExpansionZipFile(context, i2, i);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    this.mAPKExtensionFile = APKExpansionSupport.getResourceZipFile(strArr);
                }
                this.mInit = true;
                return true;
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        initIfNecessary();
        return super.applyBatch(arrayList);
    }

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public abstract String getAuthority();

    public String getType(Uri uri) {
        return "vnd.android.cursor.item/asset";
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public boolean onCreate() {
        return true;
    }

    public AssetFileDescriptor openAssetFile(Uri uri, String str) throws FileNotFoundException {
        initIfNecessary();
        String encodedPath = uri.getEncodedPath();
        if (encodedPath.startsWith("/")) {
            encodedPath = encodedPath.substring(1);
        }
        return this.mAPKExtensionFile.getAssetFileDescriptor(encodedPath);
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        initIfNecessary();
        AssetFileDescriptor openAssetFile = openAssetFile(uri, str);
        if (openAssetFile != null) {
            return openAssetFile.getParcelFileDescriptor();
        }
        return null;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int[] iArr;
        initIfNecessary();
        ZipResourceFile.ZipEntryRO[] allEntries = this.mAPKExtensionFile == null ? new ZipResourceFile.ZipEntryRO[0] : this.mAPKExtensionFile.getAllEntries();
        if (strArr == null) {
            iArr = ALL_FIELDS_INT;
            strArr = ALL_FIELDS;
        } else {
            int length = strArr.length;
            iArr = new int[length];
            for (int i = 0; i < length; i++) {
                if (strArr[i].equals(FILEID)) {
                    iArr[i] = 0;
                } else if (strArr[i].equals(FILENAME)) {
                    iArr[i] = 1;
                } else if (strArr[i].equals(ZIPFILE)) {
                    iArr[i] = 2;
                } else if (strArr[i].equals(MODIFICATION)) {
                    iArr[i] = 3;
                } else if (strArr[i].equals(CRC32)) {
                    iArr[i] = 4;
                } else if (strArr[i].equals(COMPRESSEDLEN)) {
                    iArr[i] = 5;
                } else if (strArr[i].equals(UNCOMPRESSEDLEN)) {
                    iArr[i] = 6;
                } else if (strArr[i].equals(COMPRESSIONTYPE)) {
                    iArr[i] = 7;
                } else {
                    throw new RuntimeException();
                }
            }
        }
        MatrixCursor matrixCursor = new MatrixCursor(strArr, allEntries.length);
        int length2 = iArr.length;
        for (ZipResourceFile.ZipEntryRO zipEntryRO : allEntries) {
            MatrixCursor.RowBuilder newRow = matrixCursor.newRow();
            for (int i2 = 0; i2 < length2; i2++) {
                switch (iArr[i2]) {
                    case 0:
                        newRow.add(Integer.valueOf(i2));
                        break;
                    case 1:
                        newRow.add(zipEntryRO.mFileName);
                        break;
                    case 2:
                        newRow.add(zipEntryRO.getZipFileName());
                        break;
                    case 3:
                        newRow.add(Long.valueOf(zipEntryRO.mWhenModified));
                        break;
                    case 4:
                        newRow.add(Long.valueOf(zipEntryRO.mCRC32));
                        break;
                    case 5:
                        newRow.add(Long.valueOf(zipEntryRO.mCompressedLength));
                        break;
                    case 6:
                        newRow.add(Long.valueOf(zipEntryRO.mUncompressedLength));
                        break;
                    case 7:
                        newRow.add(Integer.valueOf(zipEntryRO.mMethod));
                        break;
                }
            }
        }
        return matrixCursor;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
