package net.skhu.schedule;

import net.skhu.notice.Notice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ScheduleApi {

    @GET("schedule/user/{userid}")
    Call<List<Schedule>> getScheduleList(@Path("userid") int userid);
    //Call<Schedule> getScheduleList(@Path("userid") int userid);

    @POST("schedule/create")
    Call<Schedule> setSchedule(@Body Schedule schedule);

    @POST("schedule/delete")
    Call<Schedule> deleteSchedule(@Body int idx);

    @POST("schedule/edit")
    Call<Schedule> editSchedule(@Body Schedule schedule);

}
