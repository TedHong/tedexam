package com.greatted.tedexam.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.greatted.tedexam.R;
import com.greatted.tedexam.interfaces.OnLoadingCallBack;
import com.greatted.tedexam.model.ImageItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//
public class ImageInfoAsync extends AsyncTask<Void, Void, Void> {
    Context mContext;
    private ProgressDialog progressDialog;
    private OnLoadingCallBack onLoadinComplete; // Callback Interface
    private String mUrl;

    public void setContext(Context c){
        mContext = c;
    }

    public void setUrl(String url){
        mUrl = url;
    }

    public void setLoadingCallback(OnLoadingCallBack m){
        this.onLoadinComplete = m;
    }


    @Override
    protected void onPreExecute(){
        super.onPreExecute();

        //로딩 다이얼로그 호출 시작
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setProgressStyle(R.style.CustomProgressBar);
        progressDialog.setMessage("데이터 파싱중");
        progressDialog.show();

    }

    @Override
    protected Void doInBackground(Void... params){
        try{
            getImageListFromSite(mUrl);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        //super.onPostExecute(aVoid);
        progressDialog.setMessage("시작");
        if(onLoadinComplete != null)
            onLoadinComplete.onLoadingComplete(); //작업 종료 후 Callback 호출

        progressDialog.dismiss();
    }

    void getImageListFromSite(String site)
    {
        List<ImageItem> list = new ArrayList<>();

        try{
            Log.d("IMAGE: ", "site = "+ site);
            Document doc = Jsoup.connect(site).timeout(5000).get();
            Elements mElementDatas = doc.select("div[class=item-wrapper]");//"img[class=jq-lazy]");

            Log.d("IMAGE : ", "mElementDatas size = "+ mElementDatas.size());
            progressDialog.setMessage("주소 추출 중");
            for(Element item : mElementDatas){
                Element item_img = item.selectFirst("img[class=jq-lazy]");
                String img_src = item_img.attr("abs:data-src");

                Element item_text = item.selectFirst("div[class=text-wrapper mt-auto]");
                String text_title = item_text.selectFirst("h5[class=image-title]").text();

                ImageItem imgItem = new ImageItem(text_title, img_src);
                list.add(imgItem);

                //Log.d("IMAGE : ", "imgItem = "+ imgItem.toString());
            }
            progressDialog.setMessage("DB에 저장 중");
            DataManager.getInstance().addImageItemsToDB(list);

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}

