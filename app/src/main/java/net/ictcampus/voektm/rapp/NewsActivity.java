package net.ictcampus.voektm.rapp;

import android.content.Intent;

import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;

import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;


public class NewsActivity extends Navigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //MainActivity nur ein mal anzeigen
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);
        if (isFirstRun) {
            //show auswahl activity
            startActivity(new Intent(NewsActivity.this, MainActivity.class));
        }
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_news);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.news);
        mActivity = this;
        Button button = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mActivity,TestActivity.class);
                startActivity(intent);
            }
        });

    }

}
