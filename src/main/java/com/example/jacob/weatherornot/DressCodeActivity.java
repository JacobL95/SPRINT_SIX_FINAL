package com.example.jacob.weatherornot;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import Information.Information_Directory;
import Information.JSON_Parser;
import Information.Weather_Client;
import Structure_Model.Weather_Hub_Model;
import Utility.DISPLAY_Utility;

public class DressCodeActivity extends AppCompatActivity {
    TextView dresscode, optionaldresscode;
    Weather_Hub_Model WeatherForActivity = new Weather_Hub_Model();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dress_code);
        BuildDisplay();
    }

    private void BuildDisplay(){
        dresscode = (TextView) findViewById(R.id.dresscode);
        optionaldresscode = (TextView) findViewById(R.id.optionaldresscode);
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
            dresscode.setText(new DISPLAY_Utility().What_To_Wear(new DISPLAY_Utility().KELVIN_TO_FAHRENHEIT_INTEGER(weather_hub_model.currentCondition.getTemp())));
            optionaldresscode.setText(new DISPLAY_Utility().Umbrella_Check(weather_hub_model.currentCondition.getDescription()));
        }
    }

    public void FailSafe() {
        Toast.makeText(DressCodeActivity.this,"Error Connecting to Server", Toast.LENGTH_LONG).show();
    }
}
