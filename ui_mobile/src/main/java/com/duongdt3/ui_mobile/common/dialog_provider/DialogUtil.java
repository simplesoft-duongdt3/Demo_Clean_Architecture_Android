package com.duongdt3.ui_mobile.common.dialog_provider;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;

public class DialogUtil {

    public static void showNetworkErrorDialog(Context context) {
        Dialog dialog = getDialog(context, "Network error", "Try again later!", "Ok", "Cancel", null, null);
        if (canShowDialog(context)) {
            dialog.show();
        }
    }

    public static void showServerErrorDialog(Context context, String msg) {
        Dialog dialog = getDialog(context, "Server error", msg, "Ok", "Cancel", null, null);
        if (canShowDialog(context)) {
            dialog.show();
        }
    }

    public static void showNetworkTimeoutDialog(Context context) {
        Dialog dialog = getDialog(context, "Network timeout error", "Try again later!", "Ok", "Cancel", null, null);
        if (canShowDialog(context)) {
            dialog.show();
        }
    }

    public static void showUnknownProblemDialog(Context context) {
        Dialog dialog = getDialog(context, "Unknown problem", "Try again later!", "Ok", "Cancel", null, null);
        if (canShowDialog(context)) {
            dialog.show();
        }
    }

    private static Dialog getDialog(final Context context, String title,
                                    String message, String positiveButton, String negativeButton,
                                    final DialogInterface.OnClickListener positiveListener,
                                    final DialogInterface.OnClickListener negativeListener) {


        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButton, positiveListener)
                .setNegativeButton(negativeButton, negativeListener).create();
    }

    private static boolean canShowDialog(Context context) {
        boolean canShow = true;
        //Check bad token null
        if (context == null || (context instanceof Activity && ((Activity) context).isFinishing())) {
            canShow = false;
        }
        return canShow;
    }
}