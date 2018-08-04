package com.it.zzb.one;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.it.zzb.one.gson.OneData;

import com.it.zzb.one.util.HttpUtil;
import com.it.zzb.one.util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    OneAdapter adapter;
    List<OneData> dataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        recyclerView = findViewById(R.id.recycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }
    public void init(){
        String url = "http://v3.wufazhuce.com:8000/api/hp/more/0?version=v3.5.3";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"获取信息失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final List<OneData> data  = Utility.handleOnedata(responseText);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //更新界面
                        showInfo(data);
                    }
                });

            }
        });

    }
    public void showInfo(List<OneData> data){
        for(int i = 0;i<data.size();i++){
            dataList.add(data.get(i));
        }
        adapter = new OneAdapter(dataList);
        recyclerView.setAdapter(adapter);

    }
}
