package com.example.gravebot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class NavigationActivity extends AppCompatActivity {

    // UI properties
    public ImageButton btnForward;
    public ImageButton btnRight;
    public ImageButton btnLeft;
    public ImageButton btnBack;
    private Button btnExMan;
    public WebView wVStream;
    public String msg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_navigation);

        // link UI widgets
        btnForward = (ImageButton) findViewById(R.id.btnForward);
        btnRight = (ImageButton) findViewById(R.id.btnRight);
        btnLeft = (ImageButton) findViewById(R.id.btnLeft);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnExMan = (Button) findViewById(R.id.btnTerminateManual);
        wVStream = (WebView) findViewById(R.id.wVStream);

        SystemClock.sleep(1000);
        wVStream.loadUrl("http://192.168.13.117:5000");
        wVStream.setInitialScale(160);

        // Click on Listener for the navigation buttons
        btnForward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                msg = "front";
                if (event.getAction()==MotionEvent.ACTION_DOWN)
                    send(msg);
                else
                    send("stop");
                return true;
            }
        });

        btnRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){

                msg = "right";
                if (event.getAction()==MotionEvent.ACTION_DOWN)
                    send(msg);
                else
                    send("stop");
                return true;
            }
        });

        btnLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){

                msg = "left";
                if (event.getAction()==MotionEvent.ACTION_DOWN)
                    send(msg);
                else
                    send("stop");
                return true;
            }
        });

        btnBack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){

                msg = "back";
                if (event.getAction()==MotionEvent.ACTION_DOWN)
                    send(msg);
                else
                    send("stop");
                return true;
            }
        });

        // Click on Listener for the MainMenu Button
        btnExMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg = "XMan";
                send_Main(msg);
                openMainMenu();
            }
        });
    }

    public void send_Main (String message){
        client messageSender = new client();
        messageSender.execute(message);
    }
    public void send (String message){
        client_manual messageSender = new client_manual();
        messageSender.execute(message);
    }

    private void openMainMenu() {
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