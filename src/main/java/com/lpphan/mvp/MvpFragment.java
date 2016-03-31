package com.lpphan.mvp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.lpphan.mvp.presenter.BasePresenter;
import com.lpphan.mvp.view.MvpView;

/**
 * Created by lamphuong on 14/12/2015.
 */
public abstract class MvpFragment<V extends MvpView,P extends BasePresenter<V>> extends Fragment{
    protected P mPresenter;

    protected P getPresenter(){
        return mPresenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter == null) mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }

    public abstract P createPresenter();
}
