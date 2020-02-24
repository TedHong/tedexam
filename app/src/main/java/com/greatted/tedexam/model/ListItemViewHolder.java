package com.greatted.tedexam.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.greatted.tedexam.R;
import com.greatted.tedexam.interfaces.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

// ViewHolder 클래스
public class ListItemViewHolder extends RecyclerView.ViewHolder{

    private Context mContext;
    private OnItemClickListener onItemClickListener;

    @BindView(R.id.list_image)
    ImageView imageView;

    public ListItemViewHolder(Context c, ViewGroup parent, OnItemClickListener onItemClickListener){
        super(LayoutInflater.from(c).inflate(R.layout.list_item, parent, false));
        this.mContext = c;
        this.onItemClickListener = onItemClickListener;
        ButterKnife.bind(this, itemView);
    }

    public void onBind(ImageItem item, final int index){
        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(index);
            }
        });

        Glide.with(mContext)
                .load(item.getImg_url())
                //.placeholder(R.mipmap.ic_launcher)
                .into(imageView);
    }

}
