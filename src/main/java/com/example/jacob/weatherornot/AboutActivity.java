package com.example.jacob.weatherornot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    TextView AboutMe, AboutIcons, IconLink, WeatherParserHelp, WeatherParserHelper, ParserHelpLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        BuildAboutPage();
    }

    public void BuildAboutPage(){
        AboutMe = (TextView) findViewById(R.id.AboutMe);
        AboutIcons = (TextView) findViewById(R.id.AboutIcons);
        IconLink = (TextView) findViewById(R.id.IconLink);
        WeatherParserHelp = (TextView) findViewById(R.id.WeatherParserHelp);
        WeatherParserHelper = (TextView) findViewById(R.id.WeatherParserHelper);
        ParserHelpLink = (TextView) findViewById(R.id.ParserHelpLink);
    }
}
