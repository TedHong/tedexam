package com.greatted.tedexam.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.greatted.tedexam.interfaces.ListItemContract;
import com.greatted.tedexam.interfaces.OnItemClickListener;

import java.util.List;

// 리스트에 사용할 어댑터 클래스
public class ListItemAdapter extends RecyclerView.Adapter<ListItemViewHolder> implements ListItemContract.Model, ListItemContract.View {
    private Context mContext;
    private OnItemClickListener onItemClickListener;
    private List<ImageItem> imageItems;

    public ListItemAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public void addItems(List<ImageItem> items) {
        imageItems = items;
    }

    @Override
    public void clearItems(){
        if(imageItems != null) imageItems.clear();
    }

    @Override
    public ImageItem getItem(int index){
        return imageItems.get(index);
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ListItemViewHolder(mContext, viewGroup, onItemClickListener);
    }

    @Override
    public void setOnClickListener(OnItemClickListener clickListener) {
        this.onItemClickListener = clickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder listItemViewHolder, int i) {
        if(listItemViewHolder == null) return;

        listItemViewHolder.onBind(getItem(i), i);
    }

    @Override
    public int getItemCount(){
        return (imageItems != null)? imageItems.size() : 0;
    }
}
