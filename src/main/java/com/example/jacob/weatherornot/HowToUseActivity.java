package com.example.jacob.weatherornot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HowToUseActivity extends AppCompatActivity {

    TextView Header, Information, info2, info3, info4, info5, info6, info7, info8, info9, info10, info11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_use);
        ShowInstructions();
    }

    public void ShowInstructions(){
        Header = (TextView) findViewById(R.id.Header);
        Information = (TextView) findViewById(R.id.Information);
        info2 = (TextView) findViewById(R.id.info2);
        info3 = (TextView) findViewById(R.id.info3);
        info4 = (TextView) findViewById(R.id.info4);
        info5 = (TextView) findViewById(R.id.info5);
        info6 = (TextView) findViewById(R.id.info6);
        info7 = (TextView) findViewById(R.id.info7);
        info8 = (TextView) findViewById(R.id.info8);
        info9 = (TextView) findViewById(R.id.info9);
        info10 = (TextView) findViewById(R.id.info10);
        info11 = (TextView) findViewById(R.id.info11);
    }
}
