package com.example.jacob.weatherornot;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuPopupHelper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import Information.Information_Directory;
import Information.JSON_Parser;
import Information.Weather_Client;
import Structure_Model.Optimal_Condtions_Model;
import Structure_Model.Weather_Hub_Model;
import Utility.CONDITION_Utility;
import Utility.DISPLAY_Utility;

import static java.sql.DriverManager.println;

public class ActivityInfo extends AppCompatActivity {

    TextView DegreeSymbol, Location, ActivityTitle, Wind, WindSpeed, Mph, Temperature, Temp, Condition, CurrentCondition, WeatherReport;
    ImageView ImageIcon;
    Weather_Hub_Model WeatherForActivity = new Weather_Hub_Model();
    SwipeRefreshLayout SwipeDownActivity;
    SharedPreferences ActivitySaver;
    String IconNeededForActivity, DescriptionNeeded;
    int WindMod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ActivitySaver = getPreferences(MODE_PRIVATE);
        SwipeDownActivity = (SwipeRefreshLayout) findViewById(R.id.SwipeDownActivity);
        SwipeDownActivity.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                BuildDisplay();
                SwipeDownActivity.setRefreshing(true);
            }
        });
        BuildDisplay();
    }

    private void BuildDisplay(){
        DegreeSymbol = (TextView) findViewById(R.id.DegreeSymbol);
        Location = (TextView) findViewById(R.id.Location);
        ActivityTitle = (TextView) findViewById(R.id.Activity);
        Wind = (TextView) findViewById(R.id.Wind);
        WindSpeed = (TextView) findViewById(R.id.WindSpeed);
        Mph = (TextView) findViewById(R.id.MPH);
        Temperature = (TextView) findViewById(R.id.Temperature);
        Temp = (TextView) findViewById(R.id.Temp);
        Condition = (TextView) findViewById(R.id.Condition);
        CurrentCondition = (TextView) findViewById(R.id.CurrentCondition);
        WeatherReport = (TextView) findViewById(R.id.WeatherReport);
        ImageIcon = (ImageView) findViewById(R.id.ImageIcon);
        DisplayActivityName();
    }

    public void DisplayActivityName(){
        Intent Activity = getIntent();
        SharedPreferences.Editor NewSave = ActivitySaver.edit();
        String SelectedActivity = Activity.getStringExtra(Information_Directory.PassedString);
        NewSave.putString("Saved Activity", SelectedActivity);
        NewSave.commit();
        String PassedGPSCoordinates = Activity.getStringExtra(Information_Directory.PassedStringExtra);
        ActivityTitle.setText(SelectedActivity);
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
            SwipeDownActivity.setRefreshing(false);
            ImageIcon.setImageResource(new DISPLAY_Utility().applyIcon(WeatherForActivity.currentCondition.getCondtion()));
            CurrentCondition.setText(new DISPLAY_Utility().formatDescription(WeatherForActivity.currentCondition.getDescription()));
            Temperature.setText(new DISPLAY_Utility().KELVIN_TO_FAHRENHEIT(WeatherForActivity.currentCondition.getTemp()));
            Location.setText(WeatherForActivity.location.getCity());
            WindSpeed.setText(new DISPLAY_Utility().FloatToString(WeatherForActivity.wind.getSpeed()));
            ImageIcon.setImageResource(new DISPLAY_Utility().ICON_CORRECTION_CHECK(WeatherForActivity.currentCondition.getDescription(),
                    new DISPLAY_Utility().applyIcon(WeatherForActivity.currentCondition.getCondtion())));

            OptimalConditionStatus(new CONDITION_Utility().TEMP_CHECK(new DISPLAY_Utility().KELVIN_TO_FAHRENHEIT_INTEGER(WeatherForActivity.currentCondition.getTemp())),
                    new CONDITION_Utility().CONDITION_CHECK(WeatherForActivity.currentCondition.getCondtion()),
                    new CONDITION_Utility().WIND_CHECK(new DISPLAY_Utility().FloatToInteger(WeatherForActivity.wind.getSpeed())));
        }
    }

    public void OptimalConditionStatus(int temp, int condition, int wind){
        String SavedActivity = ActivitySaver.getString("Saved Activity", "");
        WeatherReport.setText(new Optimal_Condtions_Model().Optimal_Activity(SavedActivity, temp, condition, wind));
    }



    public void FailSafe() {
        Toast.makeText(ActivityInfo.this,"Error Connecting to Server", Toast.LENGTH_LONG).show();
    }
}
