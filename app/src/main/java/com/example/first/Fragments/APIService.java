package com.example.first.Fragments;

import com.example.first.Notifications.MyResponse;
import com.example.first.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAA2eVfa5M:APA91bHF0Biw3U2vrsK6V-fmh2giYbj6Hz3qynaDwJYWsoULmztEREieQ-LVM3FQlPl-7fbAH3nibW7XhspptZzRRYeXGQGnki7NTbKtcC9YOreieem5EihjNKQlO5vsBfQyV8z6LxHl"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotifications(@Body Sender body);
}
