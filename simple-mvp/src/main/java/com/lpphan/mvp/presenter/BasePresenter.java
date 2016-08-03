package com.lpphan.mvp.presenter;

import com.lpphan.mvp.view.MvpView;

import java.lang.ref.WeakReference;

/**
 * Created by lamphuong on 14/12/2015.
 */
public abstract class BasePresenter<V extends MvpView> implements Presenter<V> {
    protected WeakReference<V> mView;

    @Override
    public void attachView(V view) {
        mView = new WeakReference<>(view);
    }

    public V getView(){
        return mView == null ? null : mView.get();
    }

    public boolean isViewAttached(){
        return mView != null && mView.get() != null;
    }

    @Override
    public void detachView() {
        if( mView != null) {
            mView.clear();
            mView = null;
        }
    }
}
