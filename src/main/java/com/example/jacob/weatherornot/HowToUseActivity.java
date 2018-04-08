package com.example.jacob.weatherornot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HowToUseActivity extends AppCompatActivity {

    TextView Header, Information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_use);
        ShowInstructions();
    }

    public void ShowInstructions(){
        Header = (TextView) findViewById(R.id.Header);
        Information = (TextView) findViewById(R.id.Information);
    }
}
