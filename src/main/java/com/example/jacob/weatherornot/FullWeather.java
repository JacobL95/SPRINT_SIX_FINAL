package com.example.jacob.weatherornot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import Information.Information_Directory;
import Information.JSON_Parser;
import Information.Weather_Client;
import Structure_Model.Optimal_Condtions_Model;
import Structure_Model.Weather_Hub_Model;
import Utility.CONDITION_Utility;
import Utility.DISPLAY_Utility;

public class FullWeather extends AppCompatActivity {

    TextView name, cc, ct, ctd, mt, mtd, mint, mintd, hum, humd, ws, wsd;
    Weather_Hub_Model WeatherForActivity = new Weather_Hub_Model();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_weather);
        BuildDisplay();
    }

    private void BuildDisplay(){
        name = (TextView) findViewById(R.id.name);
        cc = (TextView) findViewById(R.id.cc);
        ct = (TextView) findViewById(R.id.ct);
        ctd = (TextView) findViewById(R.id.ctd);
        mt = (TextView) findViewById(R.id.mt);
        mtd = (TextView) findViewById(R.id.mtd);
        mint = (TextView) findViewById(R.id.mint);
        mintd = (TextView) findViewById(R.id.mintd);
        hum = (TextView) findViewById(R.id.hum);
        humd = (TextView) findViewById(R.id.humd);
        ws = (TextView) findViewById(R.id.ws);
        wsd = (TextView) findViewById(R.id.wsd);
        DisplayActivityName();
    }

    public void DisplayActivityName(){
        Intent Activity = getIntent();
        String PassedGPSCoordinates = Activity.getStringExtra(Information_Directory.PassedStringExtra);
        ReceiveWeatherInfoForActivity(PassedGPSCoordinates);
    }

    private void ReceiveWeatherInfoForActivity(String Coordinates) {
        WeatherTask weatherTask = new WeatherTask();
        if(Coordinates == null){
            FailSafe();
        }
        else {
            weatherTask.execute(new String[]{Coordinates + "&unites=imperial"});
        }
    }

    private class WeatherTask extends AsyncTask<String, Void, Weather_Hub_Model> {

        @Override
        protected Weather_Hub_Model doInBackground(String... params) {
            String data = ((new Weather_Client()).GetWeatherData(params[0]));
            WeatherForActivity = JSON_Parser.getWeather(data);
            return WeatherForActivity;
        }

        @Override
        protected void onPostExecute(Weather_Hub_Model weather_hub_model) {

            super.onPostExecute(weather_hub_model);
            name.setText(WeatherForActivity.location.getCity());
            cc.setText(new DISPLAY_Utility().formatDescription(WeatherForActivity.currentCondition.getDescription()));
            ctd.setText(new DISPLAY_Utility().KELVIN_TO_FAHRENHEIT(WeatherForActivity.currentCondition.getTemp()));
            mtd.setText(new DISPLAY_Utility().KELVIN_TO_FAHRENHEIT(WeatherForActivity.currentCondition.getMaxTemp()));
            mintd.setText(new DISPLAY_Utility().KELVIN_TO_FAHRENHEIT(WeatherForActivity.currentCondition.getMinTemp()));
            humd.setText(new DISPLAY_Utility().FloatToString(WeatherForActivity.currentCondition.getHumidty()));
            wsd.setText(new DISPLAY_Utility().FloatToString(WeatherForActivity.wind.getSpeed()));
        }
    }

    public void FailSafe() {
        Toast.makeText(FullWeather.this,"Error Connecting to Server", Toast.LENGTH_LONG).show();
    }

}
