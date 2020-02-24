package com.greatted.tedexam.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.greatted.tedexam.R;
import com.greatted.tedexam.model.ImageItem;
import com.greatted.tedexam.util.DataManager;

public class DetailFragment extends Fragment {

    TextView detail_Title;
    ImageView detail_Image;

    Context mContext;

    public DetailFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mContext = container.getContext();
        View view = inflater.inflate(R.layout.fragment_detail, null);
        detail_Title = view.findViewById(R.id.detail_title_frag);
        detail_Image = view.findViewById(R.id.detail_image_frag);

        Bundle bundle = getArguments();
        if(bundle != null)
        {
            int index = bundle.getInt("index", 0);
            ImageItem item = DataManager.getInstance().getImageItem(index);
            detail_Title.setText(item.getTitle());

            Glide.with(this).load(item.getImg_url()).into(detail_Image);
        }

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
