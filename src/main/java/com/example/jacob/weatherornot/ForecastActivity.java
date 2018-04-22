package com.example.jacob.weatherornot;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import Information.Forecast_Client;
import Information.Forecast_Parser;
import Information.Information_Directory;
import Information.JSON_Parser;
import Information.Weather_Client;
import Structure_Model.Forecast_Model;
import Structure_Model.Weather_Hub_Model;
import Utility.DISPLAY_Utility;
import Utility.Utilities;

public class ForecastActivity extends AppCompatActivity {
    Forecast_Model Forecast = new Forecast_Model();
    TextView Location, DATE1, MAX1, Description1, DATE2, MAX2, Description2, DATE3, MAX3, Description3, DATE4, MAX4, Description4, DATE5, MAX5, Description5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        BuildDisplay();
    }

    private void BuildDisplay(){
        Location = (TextView) findViewById(R.id.Location);
        DATE1 = (TextView) findViewById(R.id.DATE1);
        MAX1 = (TextView) findViewById(R.id.MAX1);
        Description1 = (TextView) findViewById(R.id.Description1);
        DATE2 = (TextView) findViewById(R.id.DATE2);
        MAX2 = (TextView) findViewById(R.id.MAX2);
        Description2 = (TextView) findViewById(R.id.Description2);
        DATE3 = (TextView) findViewById(R.id.DATE3);
        MAX3 = (TextView) findViewById(R.id.MAX3);
        Description3 = (TextView) findViewById(R.id.Description3);
        DATE4 = (TextView) findViewById(R.id.DATE4);
        MAX4 = (TextView) findViewById(R.id.MAX4);
        Description4 = (TextView) findViewById(R.id.Description4);
        DATE5 = (TextView) findViewById(R.id.DATE5);
        MAX5 = (TextView) findViewById(R.id.MAX5);
        Description5 = (TextView) findViewById(R.id.Description5);
        DisplayActivityName();
    }

    public void DisplayActivityName(){
        Intent Activity = getIntent();
        String PassedGPSCoordinates = Activity.getStringExtra(Information_Directory.PassedStringExtra);
        ReceiveWeatherInfoForActivity(PassedGPSCoordinates);
    }

    private void ReceiveWeatherInfoForActivity(String Coordinates) {
        ForecastTask ForecastTask = new ForecastTask();
        if(Coordinates == null){
            FailSafe();
        }
        else {
            ForecastTask.execute(new String[]{Coordinates + "&unites=imperial"});
        }
    }

    private class ForecastTask extends AsyncTask<String, Void, Forecast_Model> {

        @Override
        protected Forecast_Model doInBackground(String... params) {
            String data = ((new Forecast_Client()).GetWeatherData(params[0]));
            Forecast = Forecast_Parser.GatherForecast(data);
            return Forecast;
        }

        @Override
        protected void onPostExecute(Forecast_Model forecast_model) {

            super.onPostExecute(forecast_model);
            Location.setText(Forecast.getCity());
            DATE1.setText(new DISPLAY_Utility().date_modifier(Forecast.getDate()));
            MAX1.setText(new DISPLAY_Utility().KELVIN_TO_FAHRENHEIT(Forecast.getTemp()));
            Description1.setText(new DISPLAY_Utility().formatDescription(Forecast.getDescription()));
            DATE2.setText(new DISPLAY_Utility().date_modifier(Forecast.getDate2()));
            MAX2.setText(new DISPLAY_Utility().KELVIN_TO_FAHRENHEIT(Forecast.getTemp2()));
            Description2.setText(new DISPLAY_Utility().formatDescription(Forecast.getDescription2()));
            DATE3.setText(new DISPLAY_Utility().date_modifier(Forecast.getDate3()));
            MAX3.setText(new DISPLAY_Utility().KELVIN_TO_FAHRENHEIT(Forecast.getTemp3()));
            Description3.setText(new DISPLAY_Utility().formatDescription(Forecast.getDescription3()));
            DATE4.setText(new DISPLAY_Utility().date_modifier(Forecast.getDate4()));
            MAX4.setText(new DISPLAY_Utility().KELVIN_TO_FAHRENHEIT(Forecast.getTemp4()));
            Description4.setText(new DISPLAY_Utility().formatDescription(Forecast.getDescription4()));
            DATE5.setText(new DISPLAY_Utility().date_modifier(Forecast.getDate5()));
            MAX5.setText(new DISPLAY_Utility().KELVIN_TO_FAHRENHEIT(Forecast.getTemp5()));
            Description5.setText(new DISPLAY_Utility().formatDescription(Forecast.getDescription5()));

        }
    }

    public void FailSafe() {
        Toast.makeText(ForecastActivity.this,"Error Connecting to Server", Toast.LENGTH_LONG).show();
    }
}

