package net.skhu.notice;

import android.util.Log;

import net.skhu.schedule.Schedule;
import net.skhu.schedule.ScheduleApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;



public class RetrofitService {

    public NoticeApi getRetrofit() {
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
//                        .baseUrl("http://10.0.2.2:8088/")
//                .baseUrl("http://192.168.0.8:8088/")    //집에서 할 때 로컬에 연결
                .baseUrl("http://54.180.145.180:8088/")    //서버에 연결
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(NoticeApi.class);
    }

    public ScheduleApi getScheduleRetrofit() {
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
//                        .baseUrl("http://10.0.2.2:8088/")
                .baseUrl("http://192.168.0.8:8088/")    //집에서 할 때 로컬에 연결
//                .baseUrl("http://54.180.145.180:8088/")    //서버에 연결
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ScheduleApi.class);
    }

}
