package net.skhu.notice;

import retrofit2.converter.gson.GsonConverterFactory;

public class test {
    retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
            .baseUrl("http://192.168.0.8:8088/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();



}
