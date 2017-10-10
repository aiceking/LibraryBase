package com.android.cloud.help;

import android.app.Activity;
import android.content.Context;

import com.android.cloud.R;
import com.android.cloud.widget.dialog.EditDialog;
import com.android.cloud.widget.dialog.LoadingDialog;
import com.android.cloud.widget.dialog.OneTitleDialog;
import com.android.cloud.widget.dialog.TitleAndMessageDialog;
import com.bigkoo.pickerview.TimePickerView;

import java.util.Calendar;

/**
 * Created by radio on 2017/9/19.
 */

public class DialogHelp {
    private static DialogHelp dialogHelp;
    private LoadingDialog loadingDialog;
    private OneTitleDialog oneTitleDialog;
    private EditDialog editDialog;
    private TitleAndMessageDialog titleAndMessageDialog;
    public static DialogHelp getInstance(){
        if (dialogHelp==null){
            synchronized (DialogHelp.class){
                if (dialogHelp==null){
                    dialogHelp=new DialogHelp();
                }
            }
        }
        return dialogHelp;
    }
    /**带输入框的dialog*/
    public  void showEditDialog(Activity context, String title, String cancleText, String sureText,EditDialog.onBtnClickListener listener){
        try {
            if (editDialog==null){
                editDialog=new EditDialog(context, R.style.dialog).initTitle(title)
                        .initBtnText(cancleText,sureText)
                        .setOnBtnClickListener(listener);
                editDialog.show();
            }else{
                editDialog.setTitle(title);
                editDialog.setBtnText(cancleText,sureText);
                editDialog.ClearEdit();
                if (!editDialog.isShowing()){
                    editDialog.show();
                }
            }
        }catch (Exception e){
            editDialog=new EditDialog(context, R.style.dialog).initTitle(title)
                    .initBtnText(cancleText,sureText)
                    .setOnBtnClickListener(listener);
            editDialog.show();
        }
    }
    /**带输入框的dialog,有预输入内容*/
    public  void showEditDialog(Activity context, String title, String cancleText, String sureText,String editText,EditDialog.onBtnClickListener listener){
        try {
            if (editDialog==null){
                editDialog=new EditDialog(context, R.style.dialog).initTitle(title)
                        .initBtnText(cancleText,sureText)
                        .setOnBtnClickListener(listener);
                editDialog.show();
                editDialog.setEditText(editText);
            }else{
                editDialog.setTitle(title);
                editDialog.setBtnText(cancleText,sureText);
                editDialog.setEditText(editText);
                if (!editDialog.isShowing()){
                    editDialog.show();
                }
            }
        }catch (Exception e){
            editDialog=new EditDialog(context, R.style.dialog).initTitle(title)
                    .initBtnText(cancleText,sureText)
                    .setOnBtnClickListener(listener);
            editDialog.show();
            editDialog.setEditText(editText);
        }
    }
    /**带Title和message的dialog*/
    public  void showTitleAndMessageDialog(Activity context, String title,String message, String cancleText, String sureText,TitleAndMessageDialog.onBtnClickListener listener){
        try {
            if (titleAndMessageDialog==null){
                titleAndMessageDialog=new TitleAndMessageDialog(context, R.style.dialog).initTitleAndMessage(title,message)
                        .initBtnText(cancleText,sureText)
                        .setOnBtnClickListener(listener);
                titleAndMessageDialog.show();
            }else{
                titleAndMessageDialog.setTitleAndMessage(title,message);
                titleAndMessageDialog.setBtnText(cancleText,sureText);
                if (!titleAndMessageDialog.isShowing()){
                    titleAndMessageDialog.show();
                }
            }
        }catch (Exception e){
            titleAndMessageDialog=new TitleAndMessageDialog(context, R.style.dialog).initTitleAndMessage(title,message)
                    .initBtnText(cancleText,sureText)
                    .setOnBtnClickListener(listener);
            titleAndMessageDialog.show();
        }
    }
    /**只有Title的dialog*/
    public  void showOneTitleDialog(Activity context, String title, String cancleText, String sureText,OneTitleDialog.onBtnClickListener listener){
        try {
        if (oneTitleDialog==null){
            oneTitleDialog=new OneTitleDialog(context, R.style.dialog).initTitle(title,cancleText,sureText)
            .setOnBtnClickListener(listener);
            oneTitleDialog.show();
        }else{
            oneTitleDialog.setTitle(title,cancleText,sureText);
            if (!oneTitleDialog.isShowing()){
                oneTitleDialog.show();
             }
            }
        }catch (Exception e){
            oneTitleDialog=new OneTitleDialog(context, R.style.dialog).initTitle(title,cancleText,sureText)
                    .setOnBtnClickListener(listener);
            oneTitleDialog.show();
        }
    }
    /**加载的dialog*/
    public  void showLoadingDialog(Activity context, String title,boolean cancle){
        try {
            if (loadingDialog==null){
                loadingDialog=new LoadingDialog(context, R.style.dialog).initTitle(title);
                loadingDialog.setCancelable(cancle);
                loadingDialog.show();
            }else{
                loadingDialog.setTitle(title);
                if (!loadingDialog.isShowing()){
                    loadingDialog.show();
                }
            }
        }catch (Exception e){
            loadingDialog=new LoadingDialog(context, R.style.dialog).initTitle(title);
            loadingDialog.setCancelable(cancle);
            loadingDialog.show();
        }
    }
    public  void showDateDialog(Context context, Calendar startDate, Calendar endDate, TimePickerView.OnTimeSelectListener onTimeSelectListener){
        TimePickerView pvTime = new TimePickerView.Builder(context, onTimeSelectListener)
                .setContentSize(30) .setType(new boolean[]{true, true, true, false, false, false}).setRangDate(startDate, endDate).build();
        pvTime.setDate(Calendar.getInstance());
        pvTime.show();
    }
    public void closeLoadingDialog(){
        if (loadingDialog!=null){
            if (loadingDialog.isShowing()){
                loadingDialog.dismiss();
            }
        }
    }

}
