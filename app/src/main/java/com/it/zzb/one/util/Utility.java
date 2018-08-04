package com.it.zzb.one.util;

import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.it.zzb.one.MainActivity;
import com.it.zzb.one.gson.OneData;
import com.it.zzb.one.gson.Resouce;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.SimpleTimeZone;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zzb on 2017/12/30.
 */

public class Utility {
    public static OneData handleResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            String text = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(text,OneData.class);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static List<OneData> handleOnedata(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            String text = jsonArray.toString();
            List<OneData> dataList = new Gson().fromJson(text,new TypeToken<List<OneData>>(){}.getType());
            return dataList;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
