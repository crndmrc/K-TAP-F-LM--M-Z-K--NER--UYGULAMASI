package com.mobiluygulama.app;


import android.app.AlertDialog;
import android.content.Context;

import androidx.appcompat.view.ContextThemeWrapper;

public class Helper2 {
    public static AlertDialog.Builder alertBuilder(Context context){
        return new AlertDialog.Builder(new ContextThemeWrapper(context,R.style.ShowAlertDialogTheme));
    }
}

