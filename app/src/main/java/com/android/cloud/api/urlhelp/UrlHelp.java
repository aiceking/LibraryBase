package com.android.cloud.api.urlhelp;


import com.android.cloud.libraryinit.BaseLibraryInitHelp;

/**
 * Created by radio on 2017/9/20.
 */

public class UrlHelp {
    public static String getBaseUrl(){
        if (BaseLibraryInitHelp.getInstance().isDebug()){
            return BaseLibraryInitHelp.getInstance().getCeshiUrl();
        }else {
            return BaseLibraryInitHelp.getInstance().getShengchanUrl();
        }
    }
}
