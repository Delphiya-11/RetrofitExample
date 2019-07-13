package com.example.retrofitexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static String BaseUrl="http://api.openweathermap.org/";
    public static String Appid="2e65127e909e178d0af311a81f39948c";
    public static String lat="22.480560";
    public static String lon="88.314866";
    public static String city="Kolkata,IN";
    public static String unit="metric";


    TextView weatherData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherData=findViewById(R.id.weatherData);
    }

    public void getCurrentData(View view){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherService service=retrofit.create(WeatherService.class);

//        Call<WeatherResponse> call=service.getCurrentWeatherDataC(city,unit,Appid);
        Call<WeatherResponse> call=service.getCurrentWeatherData(lat,lon,Appid);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if(response.code()==200){
                    WeatherResponse weatherResponse=response.body();
                    assert weatherResponse!=null;

                    String stringBuilder="Country: "+
                            weatherResponse.sys.country+
                            "\n"+
                            "Temperature: "+
                            weatherResponse.main.temp+
                            "\n"+
                            "Temperature(Min): "+
                            weatherResponse.main.tempMin+
                            "\n"+
                            "Temperature (Max): "+
                            weatherResponse.main.tempMax+
                            "\n"+
                            "Humidity: "+
                            weatherResponse.main.humidity+
                            "\n"+
                            "Pressure: "+
                            weatherResponse.main.pressure;

                    weatherData.setText(stringBuilder);
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                weatherData.setText(t.getMessage());
            }
        });
    }
}
