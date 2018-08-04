package com.it.zzb.one.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zzb on 2017/12/30.
 */

public class Resouce {

    public String res;

    @SerializedName("data")
    public List<OneData> dataList;
}
