
package com.base.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.ExifInterface;
import android.os.Environment;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Base64;
import android.util.TypedValue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class ScaleUtil {

    /**
     * Convert a dp float value to pixels
     *
     * @param context
     * @param dp
     * @return the responsive pixels
     */
    public static int dp2px(Context context, float dp) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        return Math.round(px);
    }

    public static boolean isStorageEnable() {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
            return false;
        }
        return true;
    }

    // Rotates the bitmap by the specified degree.
    // If a new bitmap is created, the original bitmap is recycled.
    public static Bitmap rotate(Bitmap b, int degrees) {
        if (degrees != 0 && b != null) {
            Matrix m = new Matrix();
            m.setRotate(degrees, (float) b.getWidth() / 2, (float) b.getHeight() / 2);
            try {
                Bitmap b2 = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), m, true);
                if (b != b2) {
                    b.recycle();
                    b = b2;
                }
            } catch (OutOfMemoryError ex) {
                // We have no memory to rotate. Return the original bitmap.
            }
        }
        return b;
    }


    public static String path2String(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file = null;
            return null;
        }
        try {

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            Bitmap bitmap = scale(path);
            Bitmap bitmap1 = rotateBitmapByDegree(bitmap, readPictureDegree(path));
            bitmap1.compress(Bitmap.CompressFormat.JPEG, 90, bos);
            byte[] bytes = bos.toByteArray();
            String s = Base64.encodeToString(bytes, Base64.DEFAULT);
            bitmap1.recycle();
            return s;

        } catch (Exception e) {
        }
        return null;
    }


    //图片加水印并返回string
    public static String path2string(String path, String date) {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        try {

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Bitmap bitmap = scale(path);
            Bitmap bitmap1 = rotateBitmapByDegree(bitmap, readPictureDegree(path));
            Bitmap bitmap2 = createBitmap(bitmap1, date);
            stream.reset();
            bitmap2.compress(Bitmap.CompressFormat.JPEG, 95, stream);
            byte[] bytes = stream.toByteArray();
            String s = new String(Base64.encode(bytes, Base64.DEFAULT));
            bitmap2.recycle();
            return s;
        } catch (Exception e) {

        }
        return null;


    }


    /**
     * 给图片添加水印
     *
     * @param src 照片
     * @return
     */
    private static Bitmap createBitmap(Bitmap src, String timeStamp) {
        Time t = new Time();
        t.setToNow();
        int w = src.getWidth();
        int h = src.getHeight();
        String mstrTitle = timeStamp;
        Bitmap bmpTemp = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bmpTemp);
        Paint p = new Paint();
        String familyName = "宋体";
        Typeface font = Typeface.create(familyName, Typeface.BOLD);
        p.setColor(Color.BLUE);
        p.setTypeface(font);
        p.setTextSize(w / 50);
        canvas.drawBitmap(src, 0, 0, p);
        canvas.drawText(mstrTitle, w - w / 4, h - h / 20, p);
        canvas.save();
        canvas.restore();
        src.recycle();
        return bmpTemp;
    }


    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }


    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {


        Bitmap returnBm = null;
        if (degree == 0) {
            return bm;
        }
        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (returnBm == null) {
            returnBm = bm;
        }
        if (bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }


    private static Bitmap scale(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int rawSize = width > height ? width : height;
        if (rawSize > 1024) {
            float scale = (float) 1024 / (float) rawSize;
            Matrix matrix = new Matrix();
            matrix.setScale(scale, scale);
            Bitmap bitmap_scale = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return bitmap_scale;


        } else {
            return bitmap;
        }
    }


    public static File ScaleImage(File file, String temPath, String date) {

        if (!file.exists()) {
            return null;
        }

        FileOutputStream stream = null;
        try {
            File file_temPath = new File(temPath);
            if (!file_temPath.exists()) {
                file_temPath.mkdirs();
            }
            //
            String path = file.getAbsolutePath();
            String temp_out_path = temPath + File.separator + file.getName();
            stream = new FileOutputStream(temp_out_path);
            //
            Bitmap bitmap = scale(path);
            //
            bitmap = rotateBitmapByDegree(bitmap, readPictureDegree(path));
            //
            if (!TextUtils.isEmpty(date)) {
                bitmap = createBitmap(bitmap, date);
            }
            //
            boolean compress = bitmap.compress(Bitmap.CompressFormat.JPEG, 95, stream);
            if (compress) {
                return new File(temp_out_path);
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                stream.close();
            } catch (Exception e) {

            }
        }


    }


    private ScaleUtil() {
        throw new AssertionError("No Instances");
    }

}
