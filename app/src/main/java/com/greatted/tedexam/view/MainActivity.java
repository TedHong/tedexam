package com.greatted.tedexam.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.greatted.tedexam.R;
import com.greatted.tedexam.interfaces.MainContract;
import com.greatted.tedexam.model.ImageItem;
import com.greatted.tedexam.model.ListItemAdapter;
import com.greatted.tedexam.presenter.MainPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.Presenter presenter;
    private ListItemAdapter listAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init()
    {
        Log.d("Main : ", "init");

        listAdapter = new ListItemAdapter(this);
        presenter = new MainPresenter();
        presenter.attachView(this);
        presenter.setImageAdapterModel(listAdapter);
        presenter.setImageAdapterView(listAdapter);
        recyclerView.setAdapter(listAdapter);

        StaggeredGridLayoutManager staggeredGridLayoutManager
                = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        updateView(presenter.loadItems(this, true));

    }

    @Override
    public void updateView(List<ImageItem> items) {
        if(items != null)
        {
            listAdapter.addItems(items);
            listAdapter.notifyAdapter();
        }
    }

    @Override
    public void onItemClick(int index) {
        Intent intent = new Intent(this, DetailViewActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }



}
