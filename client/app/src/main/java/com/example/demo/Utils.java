package com.example.demo;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

public class Utils {

    public interface OnRunningListener {
        void onRunning(ProgressDialog dialog);
    }

    public static void showProgressDialog(Context context, String message, OnRunningListener listener) {
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.show();
        listener.onRunning(dialog);
    }

    public static void showToast(Activity activity, String text) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
    }
}
