package com.example.lamphuong.simplemvp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lamphuong
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainVH> {
    private List<User> items;

    public MainAdapter(){
        items = new ArrayList<>();
    }

    @Override
    public MainVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv,parent,false);
        return new MainVH(view);
    }

    @Override
    public void onBindViewHolder(MainVH holder, int position) {
        final User user = items.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<User> getItems(){
        return items;
    }
    static class MainVH extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvAge;

        public MainVH(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvAge = (TextView) itemView.findViewById(R.id.tvAge);
        }

        public void bind(User user){
            tvName.setText(user.getName());
            tvAge.setText("Age : " + user.getAge());
        }
    }
}
