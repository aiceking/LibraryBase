package com.android.cloud.help;

import android.util.Log;

import com.android.cloud.libraryinit.BaseLibraryManager;
import com.orhanobut.logger.Logger;

/**
 * Created by radio on 2017/9/21.
 */

public class LogHelp {
    public static void showLog(String tag,String message){
        if (BaseLibraryManager.getInstance().isLog()){
            Log.i(tag,message);
        }
    }
    public static void showLog(String message){
        if (BaseLibraryManager.getInstance().isLog()){
            Logger.i(message);
        }
    }
    public static void showJsonLog(String message){
        if (BaseLibraryManager.getInstance().isLog()){
            Logger.json(message);
        }
    }
}
