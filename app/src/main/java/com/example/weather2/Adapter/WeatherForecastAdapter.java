package com.example.weather2.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather2.Common.Common;
import com.example.weather2.Model.WeatherForecastResult;
import com.example.weather2.Model.WeatherResult;
import com.example.weather2.R;
import com.squareup.picasso.Picasso;

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.MyViewHolder> {

    Context context;
    WeatherForecastResult weatherForecastResult;


    public WeatherForecastAdapter(Context context, WeatherForecastResult weatherForecastResult) {
        this.context = context;
        this.weatherForecastResult = weatherForecastResult;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_weather_forecast, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/")
                .append(weatherForecastResult.list.get(position).weather.get(0).getIcon())
                .append(".png").toString()).into(holder.img_weather);

        holder.txt_date_time.setText(new StringBuilder(Common.convertUnixToDate(weatherForecastResult
        .list.get(position).dt)));

        holder.txt_description.setText( new StringBuilder(weatherForecastResult.list.get(position)
                .weather.get(0).getDescription()));

        holder.txt_temperature.setText(new StringBuilder(String.valueOf(weatherForecastResult.list.get(position).main.getTemp())).append("°C"));

        holder.txt_sunset.setText(Common.convertUnixToHour(weatherForecastResult.city.sunrise));
        holder.txt_sunset.setText(Common.convertUnixToHour(weatherForecastResult.city.sunset));

        holder.txt_humidity.setText(new StringBuilder(String.valueOf(weatherForecastResult.list.get(position).main.getHumidity())).append("%"));

        holder.txt_wind.setText(new StringBuilder(String.valueOf(weatherForecastResult.list.get(position).wind.getSpeed())).append("mph"));

        holder.txt_pressure.setText(new StringBuilder(String.valueOf(weatherForecastResult.list.get(position).main.getPressure())).append("hpa"));

        holder.txt_geo_coord.setText(new StringBuilder(String.valueOf(weatherForecastResult.city.coord)));



    }

    @Override
    public int getItemCount() {
        return weatherForecastResult.list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_city_name, txt_humidity, txt_sunrise, txt_sunset, txt_pressure, txt_temperature, txt_description, txt_date_time, txt_wind, txt_geo_coord;
        ImageView img_weather;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img_weather= itemView.findViewById(R.id.img_weather_5);
            txt_date_time = itemView.findViewById(R.id.txt_date_5);
            txt_description = itemView.findViewById(R.id.txt_description_5);
            txt_temperature = itemView.findViewById(R.id.txt_temperature_5);
            txt_city_name = itemView.findViewById(R.id.txt_city_name_5);
            txt_humidity = itemView.findViewById(R.id.txt_humidity_5);
            txt_sunrise = itemView.findViewById(R.id.txt_sunrise_5);
            txt_sunset = itemView.findViewById(R.id.txt_sunset_5);
            txt_pressure = itemView.findViewById(R.id.txt_pressure_5);
            txt_wind = itemView.findViewById(R.id.txt_wind_5);
            txt_geo_coord = itemView.findViewById(R.id.txt_geo_coord_5);

        }
    }
}
