package com.base.common.rxbus;

import java.util.HashMap;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxBus2 {
    private HashMap<String, CompositeDisposable> mSubscriptionMap;
    private static volatile RxBus2 mRxBus2;
    private final Subject<Object> mSubject;

    public static RxBus2 getInstance() {
        if (mRxBus2 == null) {
            synchronized (RxBus2.class) {
                if (mRxBus2 == null) {
                    mRxBus2 = new RxBus2();
                }
            }
        }
        return mRxBus2;
    }


    private RxBus2() {
        mSubject = PublishSubject.create().toSerialized();
    }


    private <T> Flowable<T> getObservable(Class<T> type) {
        return mSubject.toFlowable(BackpressureStrategy.BUFFER)
                .ofType(type);
    }

    public void post(Object o) {
        mSubject.onNext(o);
    }

    public void addSubscription(Object o, Disposable disposable) {
        if (mSubscriptionMap == null) {
            mSubscriptionMap = new HashMap<>();
        }
        String key = o.getClass().getName();
        if (mSubscriptionMap.get(key) != null) {
            mSubscriptionMap.get(key).add(disposable);
        } else {
            CompositeDisposable disposables = new CompositeDisposable();
            disposables.add(disposable);
            mSubscriptionMap.put(key, disposables);
        }
    }

    public <T> Disposable doSubscribe(Class<T> type, Consumer<T> next) {
        return getObservable(type)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next);
    }

    public boolean hasObservers() {
        return mSubject.hasObservers();
    }


    public void unSubscribe(Object o) {
        if (mSubscriptionMap == null) {
            return;
        }

        String key = o.getClass().getName();
        if (!mSubscriptionMap.containsKey(key)) {
            return;
        }
        if (mSubscriptionMap.get(key) != null) {
            mSubscriptionMap.get(key).dispose();
        }

        mSubscriptionMap.remove(key);
    }
}
