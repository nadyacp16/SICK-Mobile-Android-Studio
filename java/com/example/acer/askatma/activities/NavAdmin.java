package com.example.acer.askatma.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.acer.askatma.Fragment.DataKeluhanAdmin;
import com.example.acer.askatma.Fragment.HomeAdmin;
import com.example.acer.askatma.R;
import com.example.acer.askatma.storage.SharedPrefManager;

public class NavAdmin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String temp;
    private SharedPrefManager sharedPrefManager;
    private TextView textuserheader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        sharedPrefManager = new SharedPrefManager(getApplicationContext());
//        temp = sharedPrefManager.getSpIdPel();
//        textuserheader = findViewById(R.id.txtUsernameHeaderAdmin);
//        textuserheader.setText(temp);

        setContentView(R.layout.activity_nav_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        displaySelectedScreen(R.id.nav_homeAdmin);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void displaySelectedScreen(int itemId) {
        Fragment fragment = null;
        switch (itemId) {
            case R.id.nav_homeAdmin:
                fragment = new HomeAdmin();
                setTitle("Home");
                break;
            case R.id.nav_keluhanAdmin:
                setTitle("Complaints");
                fragment = new DataKeluhanAdmin();
                break;
            case R.id.nav_logout:
                logooutAdmin();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentplaceAdmin, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_admin, menu);
        return true;
    }

    public void logooutAdmin(){
        SharedPrefManager.getInstance(getApplicationContext()).logout();
        startActivity(new Intent(getApplicationContext(), WelcomeScreen.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logooutAdmin();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }
}
