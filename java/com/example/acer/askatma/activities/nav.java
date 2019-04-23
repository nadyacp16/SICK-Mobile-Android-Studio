package com.example.acer.askatma.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.askatma.Fragment.About;
import com.example.acer.askatma.Fragment.History;
import com.example.acer.askatma.Fragment.Home;
import com.example.acer.askatma.Fragment.Profile;
import com.example.acer.askatma.R;
import com.example.acer.askatma.storage.SharedPrefManager;

public class  nav extends AppCompatActivity {
    //View Variable
    private boolean isUserClickedBackButton=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentplace, new Home()).commit();

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.navigation_home:
                    selectedFragment = new Home();
                    setTitle("Home");
                    break;
                case R.id.navigation_history:
                    selectedFragment = new History();
                    setTitle("History");
                    break;
                case R.id.navigation_profile:
                    selectedFragment = new Profile();
                    setTitle("Profile");
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentplace,selectedFragment).commit();
            return true;
        }
    };


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment selectedFragment = null;
        switch (item.getItemId()){
            case R.id.navigation_home:
                selectedFragment = new Home();
                setTitle("Home");
                break;
            case R.id.navigation_history:
                selectedFragment = new History();
                setTitle("History");
                break;
            case R.id.navigation_profile:
                selectedFragment = new Profile();
                setTitle("Profile");
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentplace,selectedFragment).commit();
        return true;
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
}
