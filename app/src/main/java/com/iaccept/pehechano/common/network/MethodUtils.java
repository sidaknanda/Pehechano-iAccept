package com.iaccept.pehechano.common.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class MethodUtils {

    public static Bitmap getBitmapFromBytes(String bytes) {
        Bitmap bitmap = null;

        try {
            byte[] arr = Base64.decode(bytes, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(arr, 0, arr.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}