package com.greatted.tedexam.interfaces;

import com.greatted.tedexam.model.ImageItem;

import java.util.List;

// 리스트에 사용할 인터페이스
public interface ListItemContract {
    interface View{
        void notifyAdapter();
        void setOnClickListener(OnItemClickListener clickListener);
    }

    interface Model{
        void addItems(List<ImageItem> items);
        void clearItems();
        ImageItem getItem(int index);
    }
}
