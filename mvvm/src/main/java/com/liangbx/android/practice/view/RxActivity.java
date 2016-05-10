package com.liangbx.android.practice.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.liangbx.android.practice.thread.MainThreadUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

/**
 * Author liangbx
 * Date 2016/1/10
 * DESC 用于测试RxJava相关语法
 */
public class RxActivity extends AppCompatActivity {

    private static final String TAG = "RxActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rxSimpleSample();
        rxFromSample();
        rxEmptySample();
        rxJustSample();
        rxNeverSample();
        rxRepeatSample();
        rxSchedulersSample();
    }

    private void rxSimpleSample() {
        // 创建可观察者
        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {

                for(int i=0; i<5; i++) {
                    subscriber.onNext(i);
                }

                subscriber.onCompleted();
            }
        });

        // 创建订阅者
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext:" + integer);
            }
        };

        // 添加订阅者
        observable.subscribe(subscriber);

        // 订阅在IO线程
        observable.subscribeOn(Schedulers.io());

        // 观察在主线程
        observable.observeOn(AndroidSchedulers.mainThread());
    }

    private void rxFromSample() {
        List<Integer> items = new ArrayList<>();
        items.add(1);
        items.add(10);
        items.add(100);
        items.add(200);

        Observable.from(items)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, "From -> Item is:" + integer);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    private void rxEmptySample() {
        Observable.empty()
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "This is empty");
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });
    }

    private void rxJustSample() {
        Observable.just(helloWorld())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d(TAG, "Just -> " + s);
                    }
                });
    }

    private void rxNeverSample() {
        Observable.never()
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        Log.d(TAG, "Never -> " + o.toString());
                    }
                });
    }

    private void rxRepeatSample() {
        Observable.just(10, 20, 30)
                .repeat(3)
                .subscribe(count -> {
                    Log.d(TAG, "Repeat count:" + count);
                });
    }

    private void rxSchedulersSample() {
        Observable.just("")
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnNext(s -> Log.d(TAG, "doOnNext 1 is main thread -->" + MainThreadUtil.isMainThread()))
                .flatMap(s -> Observable.just(MainThreadUtil.isMainThread())
                        .subscribeOn(Schedulers.io())
                        .doOnNext(aBoolean -> Log.d(TAG, "doOnNext 1.1 is main thread -->" + MainThreadUtil.isMainThread())))
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnNext(s -> {
                    Log.d(TAG, "doOnNext 2 is main thread -->" + MainThreadUtil.isMainThread());
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Log.d(TAG, "subscribe is main thread -->" + MainThreadUtil.isMainThread());
                });


    }

    private void rxPublishSample() {
        ConnectableObservable publish = Observable.just(null).publish();
        publish.subscribeOn(Schedulers.io());
//        publish.observeOn(AndroidSchedulers.io());
        publish.connect();

        publish.subscribe(new Action1() {
            @Override
            public void call(Object o) {
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });

        publish.observeOn(Schedulers.io());
        publish.subscribe(new Action1() {
            @Override
            public void call(Object o) {
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });

    }

    private String helloWorld(){
        return "Hello World";
    }
}
