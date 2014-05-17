package com.example.githubtest.app;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.graphics.Bitmap;
import android.media.Image;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Michael on 5/16/14.
 */
public class PhotoHelper {

    public static Uri generateTimeStampPhotoFileUri() {
        //public static final String LOG_TAG = "getPhotoDirectory";
        Uri photoFileUri = null;

        File outputDir = getPhotoDirectory();

        if (outputDir != null) {
            //Create a file name for the photo based on current date/tie
            String timeStamp = new SimpleDateFormat("yyyyMMDD_HHmss").format(new Date());
            String photoFileName = "IMG_" + timeStamp + ".jpg";

            //Create File instance representing photo
            File photoFile = new File(outputDir, photoFileName);
            //Convert the File path to URI
            photoFileUri = Uri.fromFile(photoFile);
        }

        return photoFileUri;
    }

    private static File getPhotoDirectory() {
        String LOG_TAG = "hELLO";
        File outputDir = null;

        //Confirm that External Storage (SD Card) is mounted
        String externalStorageState = Environment.getExternalStorageState();
        if (externalStorageState.equals(Environment.MEDIA_MOUNTED)) {
            //Request the Folder where photos are supposed to be stored
            File pictureDir =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

            //Create a subfolder
            outputDir = new File(pictureDir, "Testing");
            if (!outputDir.exists()) {
                if (!outputDir.mkdirs()) {
                    Log.e(LOG_TAG, "Failed to create directory: " + outputDir.getAbsolutePath());
                    outputDir = null;
                }
            }

        }
        return outputDir;
    }

    public static void addPhotoToMediaStoreAndDisplayThumbnail(String pathName, Activity activity, ImageView imageView) {
        final ImageView thumbnailImageView = imageView;
        final Activity thumbnailActivity = activity;

        String[] filesToScan = {pathName};

        MediaScannerConnection.scanFile(thumbnailActivity, filesToScan, null, new MediaScannerConnection.OnScanCompletedListener() {
            @Override
            public void onScanCompleted(String s, Uri uri) {
                long id = ContentUris.parseId(uri);
                ContentResolver contentResolver = thumbnailActivity.getContentResolver();

                final Bitmap thumbnail = MediaStore.Images.Thumbnails.getThumbnail(
                        contentResolver, id, MediaStore.Images.Thumbnails.MINI_KIND, null);


                thumbnailActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        thumbnailImageView.setImageBitmap(thumbnail);
                    }
                });
            }
        });
    }
}
