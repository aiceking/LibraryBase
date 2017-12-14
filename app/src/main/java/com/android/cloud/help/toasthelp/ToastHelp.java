package com.android.cloud.help.toasthelp;

import android.widget.Toast;

import com.android.cloud.libraryinit.BaseLibraryManager;

import es.dmoral.toasty.Toasty;

/**
 * Created by radio on 2017/10/12.
 */

public class ToastHelp {

    public static void showSuccessToast(String message){
        Toasty.success(BaseLibraryManager.getInstance().getContext(), message, Toast.LENGTH_SHORT, true).show();
    }
    public static void showInfoToast(String message){
        Toasty.info(BaseLibraryManager.getInstance().getContext(), message, Toast.LENGTH_SHORT, true).show();
    }
    public static void showErrorToast(String message){
        Toasty.error(BaseLibraryManager.getInstance().getContext(), message, Toast.LENGTH_SHORT, true).show();
    }
}
