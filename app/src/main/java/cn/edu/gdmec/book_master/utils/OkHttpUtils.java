package cn.edu.gdmec.book_master.utils;


import android.telecom.Call;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.edu.gdmec.book_master.BookBean;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtils {
    String res = null;
    private static OkHttpUtils okHttpUtils;

    private synchronized static OkHttpUtils getInstance(){
        if (okHttpUtils == null){
            okHttpUtils = new OkHttpUtils();
        }
        return okHttpUtils;
    }

    public static void getResultCallback(String url, ResultCallback resultCallback){
        getInstance().sendRequest(url, resultCallback);
    }

    private void sendRequest(String url, final ResultCallback resultCallback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.SECONDS)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                if (resultCallback != null){
                    resultCallback.onFailure(e);
                }
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                res = response.body().string();
                Log.i("res",res);
                BookBean bean = JsonUtils.getBook(res);
                if (resultCallback != null){
                    resultCallback.getBook(bean);
                }
            }
        });
            }


    public interface ResultCallback{
        void getBook(BookBean bookBean);

        void onFailure(Exception e);
    }
}