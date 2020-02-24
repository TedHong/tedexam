package com.greatted.tedexam.interfaces;

import android.content.Context;

import com.greatted.tedexam.model.ImageItem;

import java.util.List;

// Main View 와 Presenter 에 사용될 인터페이스 선언
public interface MainContract {
    interface View{
        void updateView(List<ImageItem> items);
        void onItemClick(int index);
    }

    interface  Presenter{
        List<ImageItem> loadItems(Context context, boolean isClear);

        void attachView(View view);

        void setImageAdapterModel(ListItemContract.Model adapterModel);

        void setImageAdapterView(ListItemContract.View adapterView);

        void detachView();
    }
}
