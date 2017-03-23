package edu.ucsb.cs.inQuery.group.a185;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Class to hold utility functions that may be used in different activities.
 */

public class Utility {
    User user = User.getInstance();
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static Bitmap getSmallBitmap(Context context, User userInstance) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(userInstance.getAvatar());
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, options);
            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, 100, 100);
            // Decode bitmap with inSampleSize set
            InputStream inputStream2 = context.getContentResolver().openInputStream(userInstance.getAvatar());
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeStream(inputStream2, null, options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
