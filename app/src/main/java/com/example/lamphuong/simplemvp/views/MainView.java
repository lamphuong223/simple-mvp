package com.example.lamphuong.simplemvp.views;

import com.example.lamphuong.simplemvp.User;
import com.lpphan.mvp.view.MvpView;

import java.util.List;

/**
 * Created by lamphuong
 */
public interface MainView extends MvpView{
    void showLoading();
    void showError(String error);
    void showData(List<User> users);

    interface Events{
        void loadUsers();
    }
}
