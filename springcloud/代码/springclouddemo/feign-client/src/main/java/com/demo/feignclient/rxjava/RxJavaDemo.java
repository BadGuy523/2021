package com.demo.feignclient.rxjava;

import rx.Observable;
import rx.Observer;
import rx.functions.Action0;
import rx.functions.Func0;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
  * @Description: ReactiveX Java响应编程demo(观察者模式)
  * @Author: zhangjunqiang
  * @Date: 2021/8/1 21:37
  * @version v1.0
  */
public class RxJavaDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        final String[] datas = new String[]{"登录","进入课堂","上课","答疑"};

        final Action0 onCompleted = new Action0() {
            @Override
            public void call() {
                System.out.println("on Completed");
            }
        };
        // 被观察者
        Observable<String> observable = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                Observable observable1 = Observable.from(datas);
                return observable1.doOnCompleted(onCompleted);
            }
        });
        // 观察者
        Observer observer = new Observer() {
            @Override
            public void onCompleted() {
                System.out.println("Observer: onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Observer: onError");
            }

            @Override
            public void onNext(Object o) {
                System.out.println("Observer: onNext" + o);
            }
        };
        // 订阅
//        observable.subscribe(observer);
        // 也可订阅，直接执行被观察者的call方法
        Future<String> stringFuture = observable.toBlocking().toFuture();
        stringFuture.get();
    }
}
