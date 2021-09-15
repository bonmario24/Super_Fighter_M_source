package com.eagle.mixsdk.sdk.did.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import com.android.vending.expansion.zipfile.APEZProvider;
import com.eagle.mixsdk.sdk.did.bean.MediaFileBean;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class UriDisposeUtil {
    public static final int AUDIO_MEDIA_TYPE = 3;
    public static final int IMAGE_MEDIA_TYPE = 1;
    private static final String SUB_DIRECTORY = "Zen/data/libs/log/msc";
    public static final int VIDEO_MEDIA_TYPE = 2;
    private static MediaScannerConnection.OnScanCompletedListener onScanCompletedListener = new MediaScannerConnection.OnScanCompletedListener() {
        public void onScanCompleted(String path, Uri uri) {
            Log.d("UniqueID", "┏-----------------------------------------------------┓ ");
            Log.d("UniqueID", "Scanned " + path);
            Log.d("UniqueID", "uri=" + uri);
            Log.d("UniqueID", "┗-----------------------------------------------------┛ ");
        }
    };

    public static InputStream getMediaFile(Context context, int mediaType, String fileName) {
        try {
            return loadMediaFiles(context, mediaType, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static InputStream loadMediaFiles(Context context, int mediaType, String fileName) {
        try {
            LinkedHashMap<Uri, MediaFileBean> linkedHashMap = queryFile(context, mediaType);
            if (linkedHashMap.size() > 0) {
                for (Map.Entry<Uri, MediaFileBean> entry : linkedHashMap.entrySet()) {
                    if (fileName.equals(entry.getValue().getDisPlayName())) {
                        updateGallery(context, entry.getValue().getFilePath());
                        return context.getContentResolver().openInputStream(entry.getKey());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static LinkedHashMap<Uri, MediaFileBean> queryFile(Context context, int mediaType) {
        LinkedHashMap<Uri, MediaFileBean> mapUri = new LinkedHashMap<>();
        Cursor cursor = null;
        switch (mediaType) {
            case 1:
                cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{APEZProvider.FILEID, "_display_name", "_data"}, (String) null, (String[]) null, (String) null);
                break;
            case 2:
                cursor = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{APEZProvider.FILEID, "_display_name", "_data"}, (String) null, (String[]) null, (String) null);
                break;
            case 3:
                cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{APEZProvider.FILEID, "_display_name", "_data"}, (String) null, (String[]) null, (String) null);
                break;
        }
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        String disPlayName = "";
                        Uri uri = null;
                        int id = 0;
                        String filepath = null;
                        switch (mediaType) {
                            case 1:
                                disPlayName = cursor.getString(cursor.getColumnIndex("_display_name"));
                                id = cursor.getInt(cursor.getColumnIndex(APEZProvider.FILEID));
                                uri = Uri.parse(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString() + File.separator + id);
                                filepath = cursor.getString(cursor.getColumnIndex("_data"));
                                break;
                            case 2:
                                disPlayName = cursor.getString(cursor.getColumnIndex("_display_name"));
                                id = cursor.getInt(cursor.getColumnIndex(APEZProvider.FILEID));
                                uri = Uri.parse(MediaStore.Video.Media.EXTERNAL_CONTENT_URI.toString() + File.separator + id);
                                filepath = cursor.getString(cursor.getColumnIndex("_data"));
                                break;
                            case 3:
                                disPlayName = cursor.getString(cursor.getColumnIndex("_display_name"));
                                id = cursor.getInt(cursor.getColumnIndex(APEZProvider.FILEID));
                                uri = Uri.parse(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI.toString() + File.separator + id);
                                filepath = cursor.getString(cursor.getColumnIndex("_data"));
                                break;
                        }
                        if (uri != null) {
                            mapUri.put(uri, new MediaFileBean(id, disPlayName, filepath));
                        }
                    } while (cursor.moveToNext());
                }
                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mapUri;
    }

    private static void deleteFile(Context context, int mediaType, String fileName) {
        try {
            LinkedHashMap<Uri, MediaFileBean> linkedHashMap = queryFile(context, mediaType);
            if (linkedHashMap.size() > 0) {
                for (Map.Entry<Uri, MediaFileBean> entry : linkedHashMap.entrySet()) {
                    if (fileName.equals(entry.getValue().getDisPlayName())) {
                        updateGallery(context, entry.getValue().getFilePath());
                        context.getContentResolver().delete(entry.getKey(), (String) null, (String[]) null);
                        linkedHashMap.remove(entry.getKey());
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String insertMediaFile(Context context, int mediaType, String mimeType, String fileName, InputStream inputStream) {
        Uri fileUri = getUriDispose(context, mediaType, mimeType, fileName);
        ContentResolver cr = context.getContentResolver();
        try {
            byte[] buffer = new byte[1048576];
            ParcelFileDescriptor parcelFileDescriptor = cr.openFileDescriptor(fileUri, "w");
            FileOutputStream fileOutputStream = null;
            if (parcelFileDescriptor != null) {
                fileOutputStream = new FileOutputStream(parcelFileDescriptor.getFileDescriptor());
            }
            if (fileOutputStream != null) {
                while (true) {
                    int numRead = inputStream.read(buffer);
                    if (numRead == -1) {
                        break;
                    }
                    fileOutputStream.write(buffer, 0, numRead);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TAG", "Failed to insert media file" + e);
            if (fileUri != null) {
                cr.delete(fileUri, (String) null, (String[]) null);
                fileUri = null;
            }
        }
        if (fileUri != null) {
            return fileUri.toString();
        }
        return null;
    }

    private static Uri getUriDispose(Context context, int mediaType, String mimeType, String fileName) {
        Uri fileUri = null;
        ContentValues contentValues = new ContentValues();
        switch (mediaType) {
            case 1:
                contentValues.put("_display_name", fileName);
                contentValues.put("mime_type", mimeType);
                fileUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                if (Build.VERSION.SDK_INT < 29) {
                    contentValues.put("_data", fileMkdirs(fileName, Environment.DIRECTORY_PICTURES));
                    break;
                } else {
                    contentValues.put("relative_path", "DCIM/Zen/data/libs/log/msc");
                    break;
                }
            case 2:
                contentValues.put("_display_name", fileName);
                contentValues.put("mime_type", mimeType);
                fileUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                if (Build.VERSION.SDK_INT < 29) {
                    contentValues.put("_data", fileMkdirs(fileName, Environment.DIRECTORY_MOVIES));
                    break;
                } else {
                    contentValues.put("relative_path", "Movies/Zen/data/libs/log/msc");
                    break;
                }
            case 3:
                contentValues.put("_display_name", fileName);
                contentValues.put("mime_type", mimeType);
                fileUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                if (Build.VERSION.SDK_INT < 29) {
                    contentValues.put("_data", fileMkdirs(fileName, Environment.DIRECTORY_MUSIC));
                    break;
                } else {
                    contentValues.put("relative_path", "Music/Zen/data/libs/log/msc");
                    break;
                }
        }
        deleteFile(context, mediaType, fileName);
        if (fileUri != null) {
            return context.getContentResolver().insert(fileUri, contentValues);
        }
        return fileUri;
    }

    private static String fileMkdirs(String fileName, String publicDir) {
        File dir = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + publicDir + File.separator + SUB_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, fileName);
        if (file.exists()) {
            file.delete();
        }
        return file.getPath();
    }

    private static void updateGallery(Context context, String filePath) {
        MediaScannerConnection.scanFile(context, new String[]{filePath}, (String[]) null, onScanCompletedListener);
    }
}
