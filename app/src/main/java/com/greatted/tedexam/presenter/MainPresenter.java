package com.greatted.tedexam.presenter;

import android.content.Context;

import com.greatted.tedexam.interfaces.ListItemContract;
import com.greatted.tedexam.interfaces.MainContract;
import com.greatted.tedexam.interfaces.OnItemClickListener;
import com.greatted.tedexam.model.ImageItem;
import com.greatted.tedexam.util.DataManager;

import java.util.List;

public class MainPresenter implements MainContract.Presenter, OnItemClickListener {
    private MainContract.View mainView;
    private ListItemContract.View adapterView;
    private ListItemContract.Model adapterModel;


    @Override
    public void attachView(MainContract.View view) {
        mainView = view;
    }

    @Override
    public void setImageAdapterModel(ListItemContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void setImageAdapterView(ListItemContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnClickListener(this);
    }

    @Override
    public void detachView() {
        mainView = null;
    }

    @Override
    public List<ImageItem> loadItems(Context context, boolean isClear ) {
        if(isClear) adapterModel.clearItems();
        List<ImageItem> images =  DataManager.getInstance().getImageItemsFromDB();
        return images;
    }

    @Override
    public void onItemClick(int position) {
        mainView.onItemClick(position);
    }
}

