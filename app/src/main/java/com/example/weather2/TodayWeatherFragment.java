package com.example.weather2;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weather2.Common.Common;
import com.example.weather2.Model.WeatherResult;
import com.example.weather2.Retrofit.IOpenWeatherMap;
import com.example.weather2.Retrofit.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class TodayWeatherFragment extends Fragment {

    ImageView img_weather;
    TextView txt_city_name, txt_humidity, txt_sunrise, txt_sunset, txt_pressure, txt_temperature, txt_description, txt_date_time, txt_wind,txt_feels_like ,txt_geo_coord;
    LinearLayout weather_panel;
    ProgressBar loading;
    RelativeLayout relativeLayout;

    CompositeDisposable compositeDisposable;
    IOpenWeatherMap mService;


    static TodayWeatherFragment instance;

    public static TodayWeatherFragment getInstance(){
        if(instance == null)
            instance = new TodayWeatherFragment();
        return instance;

    }

    public TodayWeatherFragment() {
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getInstance();
        mService = retrofit.create(IOpenWeatherMap.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_today_weather, container, false);

        img_weather = itemView.findViewById(R.id.img_weather);
        txt_city_name = itemView.findViewById(R.id.txt_city_name);
        txt_humidity = itemView.findViewById(R.id.txt_humidity);
        txt_sunrise= itemView.findViewById(R.id.txt_sunrise);
        txt_sunset = itemView.findViewById(R.id.txt_sunset);
        txt_pressure = itemView.findViewById(R.id.txt_pressure);
        txt_temperature = itemView.findViewById(R.id.txt_temprature);
        txt_description = itemView.findViewById(R.id.txt_description);
        txt_date_time = itemView.findViewById(R.id.txt_date_time);
        txt_wind = itemView.findViewById(R.id.txt_wind);
        txt_geo_coord = itemView.findViewById(R.id.txt_geo_coord);
        txt_feels_like = itemView.findViewById(R.id.txt_feels_like);
        relativeLayout = itemView.findViewById(R.id.today_weather);

        weather_panel = itemView.findViewById(R.id.weather_panel);
        loading = itemView.findViewById(R.id.loading);

        getWeatherInformation();


        return itemView;
    }


    private void getWeatherInformation() {
        compositeDisposable.add(mService.getWeatherByLatLng(String.valueOf(Common.current_location.getLatitude()),
                String.valueOf(Common.current_location.getLongitude()),
                Common.APP_ID,
                "metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherResult>() {
                    @Override
                    public void accept(WeatherResult weatherResult) throws Exception {
                        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/")
                                .append(weatherResult.getWeather().get(0).getIcon())
                        .append(".png").toString()).into(img_weather);


                        txt_city_name.setText(weatherResult.getName());
                        txt_temperature.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getTemp())).append("°C").toString());
                        txt_description.setText(new StringBuilder("Weather in ").append(weatherResult.getName().toString()));
                        txt_date_time.setText(Common.convertUnixToDate(weatherResult.getDt()));
                        txt_pressure.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getPressure())).append("hpa").toString());
                        txt_humidity.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getHumidity())).append("%").toString());
                        txt_sunrise.setText(Common.convertUnixToHour(weatherResult.getSys().getSunrise()));
                        txt_sunset.setText(Common.convertUnixToHour(weatherResult.getSys().getSunset()));
                        txt_geo_coord.setText(new StringBuilder(weatherResult.getCoord().toString()).toString());
                        txt_feels_like.setText(new StringBuilder("Feels Like ").append(weatherResult.getMain().getFeels_like()).append("°C").toString());

                        /*if(Common.checktimings(Common.convertUnixToHour(weatherResult.getDt()),"12:00")){
                            relativeLayout.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.day1));
                        }
                        else{
                            relativeLayout.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.night));

                        }*/

                        if(Common.checktimings1(Common.convertUnixToHour(weatherResult.getDt()))){
                            relativeLayout.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.day1));
                        }
                        else{
                            relativeLayout.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.night));
                        }

                        weather_panel.setVisibility(View.VISIBLE);
                        loading.setVisibility(View.GONE);


                    }
                } ,new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Toast.makeText(getActivity(), ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        })


        );
    }
    //when app stops

    @Override
    public void onDestroy(){
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    public void onStop(){
        compositeDisposable.clear();
        super.onStop();
    }
}