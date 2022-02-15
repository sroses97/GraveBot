package com.example.gravebot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Animation rotateAnimation;
    //private Handler mHandler = new Handler();
    private ImageView imageView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);


        imageView = (ImageView) findViewById(R.id.imageView);
        TextView username = (TextView) findViewById(R.id.txtUserName);
        TextView password = (TextView) findViewById(R.id.txtPassword);
        button = (Button) findViewById(R.id.btnLogin);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //only able to login with u:admin pass:admin
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {

                    // Rotation of the Image

                    //RotateAnimation rotateAnimation = new RotateAnimation(0,360, RotateAnimation.RELATIVE_TO_SELF,
                    //       .5f, RotateAnimation.RELATIVE_TO_SELF, .5f);
                    //rotateAnimation.setDuration(500);
                    //imageView.startAnimation(rotateAnimation);

                    Toast.makeText(LoginActivity.this, "LOGIN SUCCESSFULL", Toast.LENGTH_SHORT).show();
                    openMainMenu();



                } else
                    //incorrect
                    Toast.makeText(LoginActivity.this, "INCORRECT USERNAME/PASSWORD", Toast.LENGTH_SHORT).show();

            }




            });
        }

    //private void rotateAnimation() {
      //  rotateAnimation = AnimationUtils.loadAnimation(this,R.anim.rotate);
        //imageView.startAnimation(rotateAnimation);

    //}


    private void openMainMenu() {
        Intent i = new Intent(this, MainMenu.class);
        startActivity(i);
    }
}