package com.android.cloud.help;

import android.util.Log;

import com.android.cloud.libraryinit.BaseLibraryInitHelp;
import com.orhanobut.logger.Logger;

/**
 * Created by radio on 2017/9/21.
 */

public class LogHelp {
    public static void showLog(String tag,String message){
        if (BaseLibraryInitHelp.getInstance().isLog()){
            Log.i(tag,message);
        }
    }
    public static void showLog(String message){
        if (BaseLibraryInitHelp.getInstance().isLog()){
            Logger.i(message);
        }
    }
    public static void showJsonLog(String message){
        if (BaseLibraryInitHelp.getInstance().isLog()){
            Logger.json(message);
        }
    }
}
