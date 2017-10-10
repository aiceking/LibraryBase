package com.android.cloud.libraryinit;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.android.cloud.R;
import com.bumptech.glide.Glide;
import com.lzy.imagepicker.loader.ImageLoader;

import java.io.File;

/**
 * Created by radio on 2017/8/9.
 */

public class GlideImageLoader implements ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)
                .load(Uri.fromFile(new File(path))).asBitmap()
                .placeholder(R.mipmap.imageplaceholder)
                .error(R.mipmap.imageplaceholder).into(imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)                             //配置上下文
                .load(Uri.fromFile(new File(path))) .asBitmap()     //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
