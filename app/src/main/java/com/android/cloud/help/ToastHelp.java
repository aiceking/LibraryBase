package com.android.cloud.help;

import android.widget.Toast;

import com.android.cloud.libraryinit.BaseLibraryInitHelp;

/**
 * Created by radio on 2017/10/12.
 */

public class ToastHelp {
    private static Toast toast;
    public static void showToast(String message){
        if(toast==null){
            toast = Toast.makeText(BaseLibraryInitHelp.getInstance().getContext(), message, Toast.LENGTH_SHORT);
        }
        toast.setText(message);
        toast.show();
    }
}
