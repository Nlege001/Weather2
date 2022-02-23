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

    }

    @Override
    public int getItemCount() {
        return weatherForecastResult.list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_date_time, txt_description, txt_temperature;
        ImageView img_weather;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img_weather= itemView.findViewById(R.id.img_weather_5);
            txt_date_time = itemView.findViewById(R.id.txt_date_5);
            txt_description = itemView.findViewById(R.id.txt_description_5);
            txt_temperature = itemView.findViewById(R.id.txt_temperature_5);
        }
    }
}
