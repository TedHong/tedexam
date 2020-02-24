package com.greatted.tedexam.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.greatted.tedexam.R;
import com.greatted.tedexam.interfaces.OnLoadingCallBack;
import com.greatted.tedexam.util.DBHelper;
import com.greatted.tedexam.util.DataManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoadingActivity extends Activity {

    DBHelper dbHelper;

    @BindView(R.id.LinearLayout_buttons)
    LinearLayout buttonLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ButterKnife.bind(this);

        dbHelper = new DBHelper(this);

        OnLoadingCallBack callback = getOnLoadingCallBack();

        DataManager.getInstance(this).setDBHelper(dbHelper);
        DataManager.getInstance(this).initDB(callback);
    }

    @OnClick(R.id.btn_start_activity)
    void onClick_BtnActivity()
    {
        startMainActivity(MainActivity.class);
    }

    @OnClick(R.id.btn_start_fragment)
    void onClick_BtnFragment()
    {
        startMainActivity(MainFragmentActivity.class);
    }

    // 이미지 주소 파싱 및 저장 작업이 끝난 후 호출 될 Delegate 함수 선언
    private OnLoadingCallBack getOnLoadingCallBack()
    {
        return new OnLoadingCallBack() {
            @Override
            public void onLoadingComplete() {
                Log.d("onLoadingComplete: ", "MainActivity.onLoadingComplete Callback !!");
                //startMainActivity();
                showButtons();
            }
        };
    }

    private void startMainActivity(Class<?> c){
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        this.finish();
    }

    void showButtons()
    {
        buttonLayout.setVisibility(View.VISIBLE);
    }

}
