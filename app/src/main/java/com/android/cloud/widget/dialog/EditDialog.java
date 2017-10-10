package com.android.cloud.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.cloud.R;
import com.android.cloud.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by radio on 2017/9/28.
 */

public class EditDialog extends Dialog {
    @BindView(R2.id.tv_diloag_title)
    TextView tvDiloagTitle;
    @BindView(R2.id.btn_dialog_cancle)
    Button btnDialogCancle;
    @BindView(R2.id.btn_dialog_sure)
    Button btnDialogSure;
    @BindView(R2.id.ed_diloag_message)
    EditText edDiloagMessage;

    private String title;
    private String cancle;
    private String sure;
    private onBtnClickListener onBtnClickListener;

    public EditDialog(@NonNull Context context) {
        super(context);
    }

    public EditDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public EditDialog initBtnText(String cancleText, String sureText) {
        this.cancle = cancleText;
        this.sure = sureText;
        return this;
    }
    public void ClearEdit(){
        edDiloagMessage.setText("");
    }
    public void setEditText(String s){
        edDiloagMessage.setText(s);
    }
    public EditDialog initTitle(String title) {
        this.title = title;
        return this;
    }
    public void setBtnText(String cancleText, String sureText) {
        this.cancle = cancleText;
        this.sure = sureText;
        setText();
    }

    public void setTitle(String title) {
        this.title = title;
        setText();
    }
    public EditDialog setOnBtnClickListener(EditDialog.onBtnClickListener onBtnClickListener) {
        this.onBtnClickListener = onBtnClickListener;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_eidttext);
        ButterKnife.bind(this);
        setText();

    }

    private void setText() {
        if (!TextUtils.isEmpty(title)) {
            tvDiloagTitle.setText(title);
        }
        if (!TextUtils.isEmpty(cancle)) {
            btnDialogCancle.setText(cancle);
        }
        if (!TextUtils.isEmpty(sure)) {
            btnDialogSure.setText(sure);
        }
    }

    @OnClick({R2.id.btn_dialog_cancle, R2.id.btn_dialog_sure})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.btn_dialog_cancle) {
            if (onBtnClickListener != null) {
                onBtnClickListener.onCancle();
                dismiss();
            }

        } else if (i == R.id.btn_dialog_sure) {
            if (onBtnClickListener != null) {
                onBtnClickListener.onSure(edDiloagMessage.getText().toString());
                dismiss();
            }

        }
    }

    public interface onBtnClickListener {
        void onSure(String message);
        void onCancle();
    }
}
