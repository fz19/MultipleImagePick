package kr.atw.imagepick;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;

/**
 * 이미지 유틸리티 클래스
 */
public class ImageUtil {

    /**
     * exif 방향에 맞게 수정하여 bitmap 반환
     * @param bitmap
     * @param orientation
     * @return bitmap
     * @throws OutOfMemoryError
     */
    public static Bitmap exifOrientationBitmap(Bitmap bitmap, int orientation) throws OutOfMemoryError {
        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_NORMAL:
                return bitmap;
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.setScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.setRotate(-90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.setRotate(-90);
                break;
            default:
                return bitmap;
        }

        Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        bitmap.recycle();

        return bmRotated;
    }

}
