package com.android.cloud.api.urlhelp;


import com.android.cloud.libraryinit.BaseLibraryManager;

/**
 * Created by radio on 2017/9/20.
 */

public class UrlHelp {
    public static String getBaseUrl(){
        if (BaseLibraryManager.getInstance().isDebug()){
            return BaseLibraryManager.getInstance().getCeshiUrl();
        }else {
            return BaseLibraryManager.getInstance().getShengchanUrl();
        }
    }
}
