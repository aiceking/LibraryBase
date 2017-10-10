package com.android.cloud.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.android.cloud.R;
import com.android.cloud.R2;
import com.victor.loading.rotate.RotateLoading;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by radio on 2017/9/27.
 */

public class LoadingDialog extends Dialog {
    @BindView(R2.id.tv_diloag_title)
    TextView tvDiloagTitle;
    @BindView(R2.id.rotateloading)
    RotateLoading rotateloading;

    private String title;

    public LoadingDialog(@NonNull Context context) {
        super(context);
    }

    public LoadingDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }


    public LoadingDialog initTitle(String title) {
        this.title = title;
        return this;
    }
    public void setTitle(String title) {
        this.title = title;
        if (!TextUtils.isEmpty(title)) {
            tvDiloagTitle.setText(title);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        ButterKnife.bind(this);
        rotateloading.start();
        if (!TextUtils.isEmpty(title)) {
            tvDiloagTitle.setText(title);
        }
    }


}
