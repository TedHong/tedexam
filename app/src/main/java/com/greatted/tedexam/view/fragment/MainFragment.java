package com.greatted.tedexam.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;

import com.greatted.tedexam.R;
import com.greatted.tedexam.interfaces.MainContract;
import com.greatted.tedexam.model.ImageItem;
import com.greatted.tedexam.model.ListItemAdapter;
import com.greatted.tedexam.presenter.MainPresenter;

import java.util.List;

public class MainFragment extends Fragment implements MainContract.View{

    private MainContract.Presenter presenter;
    private ListItemAdapter listAdapter;

    Context mContext;
    RecyclerView recyclerView;

    public MainFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        mContext = container.getContext();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
    }

    private void init()
    {
        recyclerView = getView().findViewById(R.id.fragment_recycler_view);
        listAdapter = new ListItemAdapter(mContext);
        presenter = new MainPresenter();
        presenter.attachView(this);
        presenter.setImageAdapterModel(listAdapter);
        presenter.setImageAdapterView(listAdapter);
        recyclerView.setAdapter(listAdapter);

        StaggeredGridLayoutManager staggeredGridLayoutManager
                = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        updateView(presenter.loadItems(mContext, true));

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
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);

        Fragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container_fragment, detailFragment);
        transaction.addToBackStack(null);
        transaction.setCustomAnimations(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
        transaction.commit();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }


}
