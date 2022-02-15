package com.example.gravebot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    private Button button_ManuelMode;
    private Button button_AutomaticMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main_menu);

        button_ManuelMode = (Button) findViewById(R.id.btnManualMode);
        button_AutomaticMode = (Button) findViewById(R.id.btnAutomaticMode);

        button_AutomaticMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAutomaticMode();

            }
        });

        button_ManuelMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openManualMode();

            }
        });

    }

    private void openAutomaticMode() {
        Intent i = new Intent(this, AutomaticMode.class);
        startActivity(i);
    }

    private void openManualMode() {
        Intent i = new Intent(this, NavigationActivity.class);
        startActivity(i);
    }
}