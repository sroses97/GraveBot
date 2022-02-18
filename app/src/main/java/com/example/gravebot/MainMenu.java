package com.example.gravebot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainMenu extends AppCompatActivity {

    // Buttons
    private Button button_ManualMode;
    private Button button_AutomaticMode;

    // save state of progress bars
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String BAR = "bar";

    // Pop-Up
    public ImageButton btnProfil;

    // Progress bar
    public static int progr_water = 100;
    public static int progr_elec = 100;

    public static ProgressBar progressBar_water;
    public static ProgressBar progressBar_elec;
    public TextView level_water;
    public TextView level_elec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_menu);

        // Pop-Up
        btnProfil = findViewById(R.id.imgBtnProfil);

        btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        // Water level
        progressBar_water = findViewById(R.id.progressBar_water);
        level_water = (TextView) findViewById(R.id.txtWaterLevel);

        // Electricity level
        progressBar_elec = findViewById(R.id.progressBar_electricity);
        level_elec = findViewById(R.id.txtElectricityLevel);

        progressBar_water.setProgress(progr_water);
        level_water.setText(String.valueOf(progr_water)+"%");

        // Countdown timer for electricity level
        CountDownTimer countDownTimer_elec = new CountDownTimer(100*2000,2000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // progress bar for the electricity level
                progr_elec -= 1;
                progressBar_elec.setProgress(progr_elec);
                level_elec.setText(String.valueOf(progr_elec)+"%");
            }
            @Override
            public void onFinish() {
            }
        };
        countDownTimer_elec.start();

        button_ManualMode = (Button) findViewById(R.id.btnManualMode);
        button_AutomaticMode = (Button) findViewById(R.id.btnAutomaticMode);

        // on click listener for the buttons
        button_AutomaticMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                openAutomaticMode();
                send("auto");
            }
        });

        button_ManualMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                openManualMode();
                send("man");
            }
        });
    }

    private void showDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_pop_up);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void openAutomaticMode() {
        Intent i = new Intent(this, AutomaticMode.class);
        startActivity(i);
    }

    private void openManualMode() {
        Intent i = new Intent(this, NavigationActivity.class);
        startActivity(i);
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(BAR, progr_elec);
        editor.apply();
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }

    public void send (String message){
        client messageSender = new client();
        messageSender.execute(message);
    }
}