package net.ictcampus.voektm.rapp;

import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;


public class NewsActivity extends Navigation{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.news);
        mActivity = this;
    }
}
