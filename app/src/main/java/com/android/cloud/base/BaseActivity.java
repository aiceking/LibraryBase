package com.android.cloud.base;
import android.os.Bundle;

import com.android.cloud.help.rxbushelp.RxBusManager;
import com.android.cloud.http.httprequestlife.LifeCycleListener;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

public abstract  class BaseActivity extends RxAppCompatActivity {
    public LifeCycleListener mListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
    }
    protected abstract int getLayoutId();
    /**
     * 回调函数
     */
    public void setOnLifeCycleListener(LifeCycleListener listener) {
        mListener = listener;
    }
    public LifecycleTransformer bindLifecycle() {
        return bindToLifecycle();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unregister(this);
        if (mListener!=null){
        mListener.onDestroy();
        }
    }
}
