package com.greatted.tedexam.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.greatted.tedexam.R;
import com.greatted.tedexam.model.ImageItem;
import com.greatted.tedexam.util.DataManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailViewActivity extends AppCompatActivity {

    @BindView(R.id.detail_title)
    TextView detail_Title;

    @BindView(R.id.detail_image)
    ImageView detail_Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        int index = intent.getIntExtra("index", 0);

        ImageItem item = DataManager.getInstance().getImageItem(index);
        detail_Title.setText(item.getTitle());

        Glide.with(this).load(item.getImg_url()).into(detail_Image);
    }
}
