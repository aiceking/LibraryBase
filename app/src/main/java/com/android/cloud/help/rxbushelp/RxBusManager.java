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

public class RxBusManager {
    private static RxBusManager rxBusManager;
    private  Subject<Object> mBus;
    private HashMap<Context,List<Disposable>> hashMap;
    private RxBusManager(){
        mBus = PublishSubject.create().toSerialized();
        hashMap=new HashMap<>();
    }
    public static RxBusManager getInstance(){
        if (rxBusManager ==null){
            synchronized (RxBusManager.class){
                if (rxBusManager ==null){
                    rxBusManager =new RxBusManager();
                }
            }
        }
        return rxBusManager;
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
                    rxBusListener.onFailed(e);
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

}
