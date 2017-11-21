package com.android.cloud.help.rxbushelp;


import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by static on 2017/11/20/020.
 */

public class RxBus {
    private static RxBus rxBus;
    private  Subject<Object> mBus;
    private HashMap<Context,List<Disposable>> hashMap;
    private RxBus(){
        mBus = PublishSubject.create().toSerialized();
        hashMap=new HashMap<>();
    }
    public static RxBus getInstance(){
        if (rxBus==null){
            synchronized (RxBus.class){
                if (rxBus==null){
                    rxBus=new RxBus();
                }
            }
        }
        return rxBus;
    }
    public void send(Object obj) {
        mBus.onNext(obj);
    }
    public <T> Observable<T> toObservable(Class<T> tClass) {
        return mBus.ofType(tClass);
    }
    public<T> void receive(Context context,final int code,final RxBusListener<T> rxBusListener){
        if (hashMap.get(context)==null){
            hashMap.put(context,new ArrayList<Disposable>());
        }
        Disposable disposable=toObservable(RxBusModel.class).subscribe(new Consumer<RxBusModel>() {
            @Override
            public void accept(RxBusModel rxBusModel)  {
                try{
                    if (rxBusModel.getCode()==code){
                        rxBusListener.onSuccess(rxBusModel);
                    }
                }catch (Exception e){
                    rxBusListener.onFailed(e.getMessage());
                }

            }
        });
        hashMap.get(context).add(disposable);
    }

    public void unregister(Context context){
        if (hashMap.get(context)!=null){
            for (Disposable disposable:hashMap.get(context)){
                disposable.dispose();
            }
            hashMap.remove(context);
        }
    }
    public void unregisterAll() {
        //会将所有由mBus生成的Observable都置completed状态,后续的所有消息都收不到了
        mBus.onComplete();
    }
}
