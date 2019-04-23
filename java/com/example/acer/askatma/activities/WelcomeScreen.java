package com.example.acer.askatma.activities;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.acer.askatma.R;

public class WelcomeScreen extends AppCompatActivity {

    private Button getstarted;
    private boolean isUserClickedBackButton=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        setAtribut();

        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    finish();
                    startActivity(new Intent(WelcomeScreen.this, MainActivity.class));
            }
        });
    }

    public void onBackPressed(){
        if(!isUserClickedBackButton){
            Toast.makeText(this,"Press back again to exit",Toast.LENGTH_LONG).show();
            isUserClickedBackButton=true;
        }else{
            super.onBackPressed();
            System.exit(1);
            finish();
        }
        new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long l) {
            }
            @Override
            public void onFinish() {
                isUserClickedBackButton=false;
            }
        }.start();
    }

    private void setAtribut()
    {
        getstarted = (Button)findViewById(R.id.btn_getstarted);
    }
}
