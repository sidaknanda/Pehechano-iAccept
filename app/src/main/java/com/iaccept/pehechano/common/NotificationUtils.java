package com.iaccept.pehechano.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

public class NotificationUtils {
    public final static String TAG = NotificationUtils.class.getName();
    private static ProgressDialog progressDialog;

    public static void showToast(Context context, int messageId) {
        if (context == null) {
            return;
        }

        Toast.makeText(context, messageId, Toast.LENGTH_LONG).show();
    }

    public static void showToast(Context context, String message) {
        if (context == null) {
            return;
        }

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showToast(Context context, int messageId, int length) {
        if (context == null) {
            return;
        }

        Toast.makeText(context, messageId, length).show();
    }

    public static void showToast(Context context, String message, int length) {
        if (context == null) {
            return;
        }

        Toast.makeText(context, message, length).show();
    }

    public static void showProgressDialog(Context context, String message) {
        if (progressDialog != null) {
            dismissProgressDialog();
        }

        try {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(message);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public static void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            try {
                progressDialog.dismiss();
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            } finally {
                progressDialog = null;
            }

        }

        progressDialog = null;
    }

    public static void showProgressDialog(Activity activity, int stringResId) {
        if (activity == null) {
            return;
        }

        showProgressDialog(activity, activity.getString(stringResId));
    }

    public static boolean isShowingProgressDialog() {
        return progressDialog != null && progressDialog.isShowing();
    }

    public static void showAlertDialog(Activity activity, int title,
                                       int message, DialogInterface.OnClickListener listener) {

        showAlertDialog(activity, activity.getString(title), activity.getString(message), listener);
    }

    public static void showAlertDialog(Activity activity, String title,
                                       String message, DialogInterface.OnClickListener listener) {

        new AlertDialog.Builder(activity)
                .setMessage(message)
                .setTitle(title)
                .setPositiveButton("OK", listener)
                .show();
    }
}