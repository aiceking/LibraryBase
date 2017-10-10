package com.android.cloud.http.observer;
import com.android.cloud.http.function.HttpResponseFunction;
import com.android.cloud.http.function.ServerReponseFunction;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by radio on 2017/9/20.
 */
public class RetrofitRxObservableHelp {
    /**自动管理RxJava请求的生命周期*/
    public static Observable getObservable(Observable baseObservable, LifecycleTransformer lifecycleTransformer) {
        Observable observable = baseObservable
                .map(new ServerReponseFunction())
                .onErrorResumeNext(new HttpResponseFunction<>())
                .subscribeOn(Schedulers.io())
                .compose(lifecycleTransformer)
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }
    /**可控制在具体某个生命周期结束请求*/
    public static Observable getObservable(Observable baseObservable, LifecycleProvider<ActivityEvent> lifecycle, ActivityEvent event) {
        Observable observable = baseObservable
                .map(new ServerReponseFunction())
                .onErrorResumeNext(new HttpResponseFunction<>())
                .subscribeOn(Schedulers.io())
                .compose(lifecycle.bindUntilEvent(event))
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

}
