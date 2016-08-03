package com.example.lamphuong.simplemvp.presenters;

import android.os.Handler;

import com.example.lamphuong.simplemvp.User;
import com.example.lamphuong.simplemvp.views.MainView;
import com.lpphan.mvp.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lamphuong
 */
public class MainPresenter extends BasePresenter<MainView> implements MainView.Events {

    @Override
    public void loadUsers() {
        getView().showLoading();
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        if (isViewAttached())
                            getView().showData(getUsers());
                    }
                }
                , 2500);
    }

    private List<User> getUsers() {
        Random rd = new Random();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            int ss = rd.nextInt(50);
            User user = new User("I'm user " + ss, ss);
            users.add(user);
        }
        return users;
    }
}
