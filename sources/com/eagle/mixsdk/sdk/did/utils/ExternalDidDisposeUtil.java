package com.eagle.mixsdk.sdk.did.utils;

import android.content.Context;
import android.util.Log;
import com.doraemon.p027eg.CheckUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExternalDidDisposeUtil {
    public static final String AUDIO_FILE_NAME = "coin";
    public static final String IMAGE_FILE_NAME = "ocean";
    public static final String VIDEO_FILE_NAME = "ocean";

    public static void saveDidToPicture(Context context, String pictureName, String did) {
        if (DIDUtil.isPictureStorage()) {
            insertMediaFile(context, 1, "image/*", pictureName, ".png", did);
        }
    }

    public static String readDidToPicture(Context context, String pictureName) {
        if (!DIDUtil.isPictureStorage()) {
            return "";
        }
        try {
            InputStream mediaFile = UriDisposeUtil.getMediaFile(context, 1, pictureName + ".png");
            if (mediaFile != null) {
                String didFromImgFile = IOUtils.readInputStream2StringPattern(mediaFile, "");
                Log.d("UniqueID", "didFromImgFile:" + didFromImgFile);
                return didFromImgFile;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void saveDidToAudio(Context context, String audioFileName, String did) {
        if (DIDUtil.isAudioStorage()) {
            insertMediaFile(context, 3, "audio/*", audioFileName, ".mp3", did);
        }
    }

    public static String readDidToAudio(Context context, String audioFileName) {
        if (!DIDUtil.isAudioStorage()) {
            return "";
        }
        try {
            InputStream mediaFile = UriDisposeUtil.getMediaFile(context, 3, audioFileName + ".mp3");
            if (mediaFile != null) {
                String didFromAudioFile = IOUtils.readInputStream2StringPattern(mediaFile, "");
                Log.d("UniqueID", "didFromAudioFile:" + didFromAudioFile);
                return didFromAudioFile;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void saveDidToVideo(Context context, String videoFileName, String did) {
        if (DIDUtil.isVideoStorage()) {
            insertMediaFile(context, 2, "video/*", videoFileName, ".mp4", did);
        }
    }

    public static String readDidToVideo(Context context, String videoFileName) {
        if (!DIDUtil.isVideoStorage()) {
            return "";
        }
        try {
            InputStream mediaFile = UriDisposeUtil.getMediaFile(context, 2, videoFileName + ".mp4");
            if (mediaFile != null) {
                String didFromVideoFile = IOUtils.readInputStream2StringPattern(mediaFile, "");
                Log.d("UniqueID", "didFromVideoFile:" + didFromVideoFile);
                return didFromVideoFile;
            }
        } catch (Exception e) {
        }
        return null;
    }

    private static void insertMediaFile(Context context, int mediaType, String mimeType, String fileName, String expandedName, String did) {
        String assetsFileName;
        if (fileName.contains("_")) {
            assetsFileName = fileName.substring(0, fileName.indexOf("_"));
        } else {
            assetsFileName = fileName;
        }
        InputStream assetsFile = getAssetsFile(context, assetsFileName + expandedName);
        if (assetsFile != null) {
            try {
                byte[] data = getBytes(assetsFile, did);
                if (data.length > 0 && CheckUtils.isNullOrEmpty(UriDisposeUtil.insertMediaFile(context, mediaType, mimeType, fileName + expandedName, new ByteArrayInputStream(data)))) {
                    Log.e("UniqueID", "did insert to public dir fail");
                }
                try {
                    assetsFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                try {
                    assetsFile.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    assetsFile.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                throw th;
            }
        }
    }

    private static byte[] getBytes(InputStream is, String did) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1048576];
        while (true) {
            int len = is.read(buffer);
            if (len != -1) {
                outStream.write(buffer, 0, len);
            } else {
                outStream.write(("\r\n" + did).getBytes());
                outStream.flush();
                outStream.close();
                return outStream.toByteArray();
            }
        }
    }

    private static InputStream getAssetsFile(Context context, String fileName) {
        try {
            return context.getAssets().open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
