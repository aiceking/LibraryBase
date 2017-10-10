package com.android.cloud.help;

import android.util.Log;

import com.android.cloud.libraryinit.BaseLibraryInitHelp;
import com.orhanobut.logger.Logger;

/**
 * Created by radio on 2017/9/21.
 */

public class LogHelp {
    public static void showLog(String tag,String message){
        if (BaseLibraryInitHelp.getInstance().isDebug()){
            Log.i(tag,message);
        }
    }
    public static void showLog(String message){
        if (BaseLibraryInitHelp.getInstance().isDebug()){
            Logger.i(message);
        }
    }
    public static void showJsonLog(String message){
        if (BaseLibraryInitHelp.getInstance().isDebug()){
            Logger.json(message);
        }
    }
}
