package net.ictcampus.voektm.rapp;

import android.app.Activity;
import android.app.NativeActivity;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class Navigation extends LightSensitivActivity {
    Activity mActivity;

    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.play:
                    Intent intent=new Intent(mActivity,AbosActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.news:
                    Intent intent2=new Intent(mActivity,NewsActivity.class);
                    startActivity(intent2);
                    return true;
                case R.id.favorites:
                    Intent intent3=new Intent(mActivity,FavoritenActivity.class);
                    startActivity(intent3);
                    return true;
                case R.id.search:
                    Intent intent4=new Intent(mActivity,SearchActivity.class);
                    startActivity(intent4);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onResume() {
        super.onResume();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }
}


