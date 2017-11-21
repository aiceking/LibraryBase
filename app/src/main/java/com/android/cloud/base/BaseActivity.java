package com.android.cloud.base;
import android.os.Bundle;

import com.android.cloud.help.rxbushelp.RxBus;
import com.android.cloud.http.httprequestlife.LifeCycleListener;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

public abstract  class BaseActivity extends RxAppCompatActivity {
    public LifeCycleListener mListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        receiveEvent();
    }
    protected abstract int getLayoutId();
    protected abstract void receiveEvent();
    /**
     * 回调函数
     */
    public void setOnLifeCycleListener(LifeCycleListener listener) {
        mListener = listener;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unregister(this);
        if (mListener!=null){
        mListener.onDestroy();
        }
    }
}
