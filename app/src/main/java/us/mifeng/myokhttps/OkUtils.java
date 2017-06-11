package us.mifeng.myokhttps;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 黑夜之火 on 2017/6/11.
 */

public class OkUtils {
//http://liuwangshu.cn/application/network/6-okhttp3.html
    /**
     * 使用Get请求
     */
    public void getAsynHttp(final Context context){
        //创建OkHttpClient的对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建请求方式对象
        Request.Builder requestBuilder = new Request.Builder()
                .url("http://www.baidu.com");
        //设置请求方式
        requestBuilder.method("GET",null);
        //创建请求对象
        Request request = requestBuilder.build();
        //
        Call mcall = mOkHttpClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.print("=========请求失败"+call.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (null!=response.cacheResponse()){
                    String str = response.cacheResponse().toString();
                    Log.i("tag","========cache======"+str);
                }else{
                    String ss = response.body().toString();
                    Log.i("tag","========response.body()======"+ss);
                    String str = response.networkResponse().toString();
                    Log.i("tag","==========newwork======"+str);
                }
                ((MainActivity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context,"请求成功",Toast.LENGTH_LONG).show();;
                    }
                });
            }
        });

    }
}
