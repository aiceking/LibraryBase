package com.android.cloud.http.gsonhelp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by radio on 2017/8/10.
 */

public class GsonHelp {
    /**可以把null转换成“”空字符串*/
    public static Gson getGson(){
        Gson gson  = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
        return gson;
    }

}
