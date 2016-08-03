package com.example.lamphuong.simplemvp;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lamphuong.simplemvp.presenters.MainPresenter;
import com.example.lamphuong.simplemvp.views.MainView;
import com.lpphan.mvp.MvpActivity;

import java.util.List;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView, SwipeRefreshLayout.OnRefreshListener {

    SwipeRefreshLayout refreshLayout;
    RecyclerView recyclerView;
    TextView errorView;
    ProgressBar loadingView;

    MainAdapter adapter;

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        refreshLayout.setOnRefreshListener(this);

        adapter = new MainAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        getPresenter().loadUsers();
    }

    private void bindViews() {
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.contentView);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        errorView = (TextView) findViewById(R.id.errorView);
        loadingView = (ProgressBar) findViewById(R.id.loadingView);
    }

    @Override
    public void showLoading() {
        errorView.setVisibility(View.GONE);
        if (!refreshLayout.isRefreshing()) {
            loadingView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(String error) {
        if (refreshLayout.isRefreshing())
            refreshLayout.setRefreshing(false);
        errorView.setText(error);
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showData(final List<User> users) {
        if (refreshLayout.isRefreshing())
            refreshLayout.setRefreshing(false);

        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

        adapter.getItems().clear();
        adapter.notifyDataSetChanged();

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                adapter.getItems().addAll(users);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onRefresh() {
        getPresenter().loadUsers();
    }
}
