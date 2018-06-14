package net.ictcampus.voektm.rapp;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.NativeActivity;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Navigation extends LightSensitivActivity {
    Activity mActivity;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;
    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction trans = getFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.play:
                    Log.e("play","play");
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.news:
                    Log.e("news","news");
                    viewPager.setCurrentItem(0);
                    viewPagerAdapter.notifyDataSetChanged();
                    return true;
                case R.id.favorites:
                    Log.e("favorites","favorites");
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.search:
                    Log.e("search","search");
                    viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        viewPager = findViewById(R.id.viewPager);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        List<Fragment> listFragment = new ArrayList<>();
        listFragment.add(new NewsFragment());
        listFragment.add(new AbosFragment());
        listFragment.add(new FavoritenActivity());
        listFragment.add(new SearchFragment());

        List<String> listTitles = new ArrayList<>();
        listTitles.add("Fragment One");
        listTitles.add("Fragment Two");
        listTitles.add("Fragment Three");
        listTitles.add("Fragment Four");
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),
                listFragment, listTitles);
        viewPager.setAdapter(viewPagerAdapter);
    }
}


