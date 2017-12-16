package com.android.cloud.help;

import android.util.Log;

import com.android.cloud.libraryinit.BaseLibraryManager;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.Map;

/**
 * Created by radio on 2017/9/21.
 */

public class LogHelp {

    public static void showLog(String tag,String message){
        if (BaseLibraryManager.getInstance().isLog()){
            Logger.t(tag).i(message);
        }
    }
    public static void showJsonLog(String tag,String message){
        if (BaseLibraryManager.getInstance().isLog()){
            Logger.t(tag).json(message);
        }
    }
    public static void showListLog(String tag, List list){
        if (BaseLibraryManager.getInstance().isLog()){
            Logger.t(tag).d(list);
        }
    }
    public static void showMapLog(String tag, Map map){
        if (BaseLibraryManager.getInstance().isLog()){
            Logger.t(tag).d(map);
        }
    }
}
