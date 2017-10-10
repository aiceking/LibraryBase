package com.android.cloud.help;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by radio on 2017/9/30.
 */

public class DateHelp {
    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//时间显示样式，可选
        return format.format(date);
    }
}
