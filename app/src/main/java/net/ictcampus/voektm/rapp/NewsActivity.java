package net.ictcampus.voektm.rapp;

import android.app.Activity;
import android.content.Intent;

import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;



public class NewsActivity extends android.support.v4.app.Fragment {
    /*
    Activity mActivity;
    mActivity = this;*/
    public NewsActivity(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_news, container, false);
        /*
        Button button = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mActivity,TestActivity.class);
                startActivity(intent);
            }
        });
*/
    }


}