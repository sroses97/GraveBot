package com.example.gravebot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class AutomaticMode extends AppCompatActivity {

    // UI properties
    public Button btnExAuto;
    public String msg;
    public WebView wVStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_automatic_mode);

        // link UI widgets
        wVStream = (WebView) findViewById(R.id.wVStream);
        btnExAuto = (Button) findViewById(R.id.btnTerminateAuto);

        // load URL to webView
        SystemClock.sleep(1000);
        wVStream.loadUrl("http://192.168.13.117:5000");
        wVStream.setInitialScale(160);

        // Click on Listener for the MainMenu Button
        btnExAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg = "XAuto";
                send(msg);
                openMainMenu();
            }
        });
    }

    public void send (String message){
        client messageSender = new client();
        messageSender.execute(message);
    }

    public void openMainMenu() {
        MainMenu.progr_water -= 20;
        MainMenu.progr_elec = loadData();
        Intent i = new Intent(this, MainMenu.class);
        startActivity(i);
    }

    public int loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(MainMenu.SHARED_PREFS, MODE_PRIVATE);
        MainMenu.progr_elec = sharedPreferences.getInt(MainMenu.BAR, MainMenu.progr_elec);
        return MainMenu.progr_elec;
    }
}