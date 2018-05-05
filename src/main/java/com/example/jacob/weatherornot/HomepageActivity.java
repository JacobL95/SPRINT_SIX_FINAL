package com.example.jacob.weatherornot;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import Information.Information_Directory;
import Information.JSON_Parser;
import Information.Postcode_Client;
import Information.Weather_Client;
import Structure_Model.Weather_Hub_Model;
import Structure_Model.Zipcode_model;
import Utility.DISPLAY_Utility;
import Utility.GPS_Utility;
import Utility.Utilities;
import Utility.Zipcode_Check;

import static Information.Information_Directory.PassedInt;
import static Information.Information_Directory.PassedString;
import static Information.Information_Directory.PassedStringExtra;
import static java.sql.DriverManager.println;

public class HomepageActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextView temptv, citytv, degreetv, conditiontv;
    ImageView ICON;
    Weather_Hub_Model weather = new Weather_Hub_Model();
    Zipcode_model ZipcodeCheck = new Zipcode_model();
    SharedPreferences LocationSaver;
    String Coordinates, Zipcode;
    Spinner ActivityList;
    SwipeRefreshLayout SwipeDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        LocationSaver = getPreferences(MODE_PRIVATE);
        temptv = (TextView) findViewById(R.id.temptv);
        citytv = (TextView) findViewById(R.id.citytv);
        degreetv = (TextView) findViewById(R.id.degreetv);
        conditiontv = (TextView) findViewById(R.id.conditiontv);
        ICON = (ImageView) findViewById(R.id.ICON);
        SwipeDown = (SwipeRefreshLayout) findViewById(R.id.SwipeDown);
        ActivityList = (Spinner) findViewById(R.id.spinner);
        SwipeDown.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SwipeDown.setRefreshing(true);
                receiveGpsLocation();
            }
        });
        receiveGpsLocation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.Instructions){
            Intent GoToHowToUseActivity = new Intent(this, HowToUseActivity.class);
            startActivity(GoToHowToUseActivity);
        }
        if(item.getItemId() == R.id.ChangeLocation){
            View PopUp = LayoutInflater.from(HomepageActivity.this).inflate(R.layout.activity_location_change,null);
            TextView ZipPrompt = (TextView) PopUp.findViewById(R.id.ZipPrompt);
            final EditText ZipInput = (EditText) PopUp.findViewById(R.id.ZipInput);
            AlertDialog.Builder NewPopUp = new AlertDialog.Builder(HomepageActivity.this);
            NewPopUp.setView(PopUp).setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Zipcode = ZipInput.getText().toString();
                    int ZipCheck = Zipcode.length();
                    if (ZipCheck == Information_Directory.ZipcodeLength) {
                        ZipcodeTask zipcodeTask = new ZipcodeTask();
                        zipcodeTask.execute(Zipcode);
                    } else {
                        Toast.makeText(HomepageActivity.this, "Not a Valid ZipCode", Toast.LENGTH_LONG);
                    }
                }
            }).setNegativeButton("Cancel", null).setCancelable(false);

            AlertDialog ZipForm = NewPopUp.create();
            ZipForm.show();
        }
        if(item.getItemId() == R.id.About){
            Intent GoToAboutAcitvity = new Intent(this, AboutActivity.class);
            startActivity(GoToAboutAcitvity);
        }
        if(item.getItemId() == R.id.Details){
            Intent GoToWeatherDetails = new Intent(this, FullWeather.class);
            Coordinates = LocationSaver.getString("saved coordinates", "");
            GoToWeatherDetails.putExtra(PassedStringExtra, Coordinates);
            startActivity(GoToWeatherDetails);
        }
        if(item.getItemId() == R.id.Forecast){
            Intent GoToWeatherDetails = new Intent(this, ForecastActivity.class);
            Coordinates = LocationSaver.getString("saved coordinates", "");
            GoToWeatherDetails.putExtra(PassedStringExtra, Coordinates);
            startActivity(GoToWeatherDetails);
        }
        if(item.getItemId() == R.id.Clothing){
            Intent GoToDressCodeAcitvity = new Intent(this, DressCodeActivity.class);
            Coordinates = LocationSaver.getString("saved coordinates", "");
            GoToDressCodeAcitvity.putExtra(PassedStringExtra, Coordinates);
            startActivity(GoToDressCodeAcitvity);
        }
        return super.onOptionsItemSelected(item);
    }

    public void receiveGpsLocation() {

        ActivityCompat.requestPermissions(HomepageActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2018);
        GPS_Utility GPS = new GPS_Utility(getApplicationContext());
        Location GPS_location = GPS.getGPS_LOCATION();
        SharedPreferences.Editor NewSave = LocationSaver.edit();

        if (GPS_location != null) {

            double LATITUDE = GPS_location.getLatitude();
            double LONGITUDE = GPS_location.getLongitude();

//          Formats API URL
            Coordinates = new String("lat=" + LATITUDE + "&lon=" + LONGITUDE);
            NewSave.putString("saved coordinates", Coordinates);
            NewSave.commit();
            receiveWeatherInformation(Coordinates);

        } else {
            String LastKnowCoordinates = LocationSaver.getString("saved coordinates", "");
            receiveWeatherInformation(LastKnowCoordinates);
        }
    }

    public void receiveWeatherInformation(String Coordinates) {

        WeatherTask weatherTask = new WeatherTask();
        if(Coordinates == null){
            FailSafe();
        }else {
            weatherTask.execute(new String[]{Coordinates + "&unites=imperial"});
        }
    }

    private class WeatherTask extends AsyncTask<String, Void, Weather_Hub_Model> {

        @Override
        protected Weather_Hub_Model doInBackground(String... params) {

            String data = ((new Weather_Client()).GetWeatherData(params[0]));
            weather = JSON_Parser.getWeather(data);
            return weather;
        }

        @Override
        protected void onPostExecute(Weather_Hub_Model weather_hub_model) {
            super.onPostExecute(weather_hub_model);
            ICON.setImageResource(new DISPLAY_Utility().applyIcon(weather.currentCondition.getCondtion()));
            conditiontv.setText(new DISPLAY_Utility().formatDescription(weather.currentCondition.getDescription()));
            ICON.setImageResource(new DISPLAY_Utility().ICON_CORRECTION_CHECK(weather.currentCondition.getDescription(),
               new DISPLAY_Utility().applyIcon(weather.currentCondition.getCondtion())));
            temptv.setText(new DISPLAY_Utility().KELVIN_TO_FAHRENHEIT(weather.currentCondition.getTemp()));
            citytv.setText(weather.location.getCity());
            ArrayAdapter<String> ArrayForActivityList = new ArrayAdapter<String>(HomepageActivity.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.Activities));
            ArrayForActivityList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ActivityList.setAdapter(ArrayForActivityList);
            ActivityList.setOnItemSelectedListener(HomepageActivity.this);
            SwipeDown.setRefreshing(false);
        }
    }

    public void FailSafe() {
        Toast.makeText(getApplicationContext(),"Error Connecting to Server", Toast.LENGTH_LONG);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String SelectedActivity = adapterView.getItemAtPosition(i).toString();

        if(i == 0){}
        else {
            Toast.makeText(HomepageActivity.this, "Here", Toast.LENGTH_LONG);
            Intent GoToActivity = new Intent(this, ActivityInfo.class);
            GoToActivity.putExtra(PassedString, SelectedActivity);
            Coordinates = LocationSaver.getString("saved coordinates", "");
            GoToActivity.putExtra(PassedStringExtra, Coordinates);
            startActivity(GoToActivity);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private class ZipcodeTask extends AsyncTask<String, Void, Zipcode_model> {
        @Override
        protected Zipcode_model doInBackground(String... params) {

            String data = ((new Postcode_Client()).Getzipcode(params[0]));
            ZipcodeCheck = Zipcode_Check.CheckZipcode(data);
            return ZipcodeCheck;
        }

        @Override
        protected void onPostExecute(Zipcode_model zipcode_model) {
            super.onPostExecute(zipcode_model);

            if (ZipcodeCheck.getTOF() == "true"){
                Coordinates = new String("zip=" + Zipcode + ",us");
                SharedPreferences.Editor NewSave = LocationSaver.edit();
                NewSave.putString("saved coordinates", Coordinates);
                NewSave.commit();
                receiveWeatherInformation(Coordinates);
            }else {
               Toast toast = Toast.makeText(HomepageActivity.this, "Not a Valid ZipCode", Toast.LENGTH_LONG);
               toast.setGravity(Gravity.TOP,0,850);
               toast.show();
            }
        }
    }
}


