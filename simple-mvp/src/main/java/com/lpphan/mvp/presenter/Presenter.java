package com.lpphan.mvp.presenter;

import com.lpphan.mvp.view.MvpView;

/**
 * Created by lamphuong on 14/12/2015.
 */
public interface Presenter<V extends MvpView> {
    void attachView(V view);
    void detachView();
}
