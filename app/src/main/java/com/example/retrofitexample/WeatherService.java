package com.example.retrofitexample;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("data/2.5/weather?")
    Call<WeatherResponse> getCurrentWeatherData(@Query("lat") String lat, @Query("lon") String lon, @Query("APPID") String APPID);

    @GET("data/2.5/weather?")
    Call<WeatherResponse> getCurrentWeatherDataC(@Query("q") String city, @Query("units") String unit,@Query("APPID") String APPID);
}
