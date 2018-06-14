package net.ictcampus.voektm.rapp;

import android.app.Activity;
import android.content.Intent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;


import java.util.ArrayList;
import java.util.List;


public  class NewsActivity extends YoutubeFailureActivity{
    /*
    Activity mActivity;
    mActivity = this;*/
    ArrayList<String> ids=new ArrayList<String>();
    String id;
    int pos=0;
    SQLiteDatabase db;
    SQLiteOpenHelper manager;
    private ArrayList<String> channels=new ArrayList<String>();
    String view;
    public NewsActivity(){
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        manager = RAppDB.getInstance(this);
        db = manager.getWritableDatabase();
        /*
    for(int i=0;i<10;i++){
        view="yt"+i;
        YouTubePlayerView yt1 = (YouTubePlayerView) findViewById(R.id.(view));
        (view).initialize(DeveloperKey.DEVELOPER_KEY, this);
        }*/
        YouTubePlayerView yt1 = (YouTubePlayerView) findViewById(R.id.yt1);
        yt1.initialize(DeveloperKey.DEVELOPER_KEY, this);
/*
        YouTubePlayerView yt2 = (YouTubePlayerView) findViewById(R.id.yt2);
        yt2.initialize(DeveloperKey.DEVELOPER_KEY, this);

        YouTubePlayerView yt3 = (YouTubePlayerView) findViewById(R.id.yt3);
        yt3.initialize(DeveloperKey.DEVELOPER_KEY, this);

        YouTubePlayerView yt4 = (YouTubePlayerView) findViewById(R.id.yt4);
        yt4.initialize(DeveloperKey.DEVELOPER_KEY, this);


        YouTubePlayerView yt5 = (YouTubePlayerView) findViewById(R.id.yt5);
        yt5.initialize(DeveloperKey.DEVELOPER_KEY, this);

        YouTubePlayerView yt6 = (YouTubePlayerView) findViewById(R.id.yt6);
        yt6.initialize(DeveloperKey.DEVELOPER_KEY, this);

        YouTubePlayerView yt7 = (YouTubePlayerView) findViewById(R.id.yt7);
        yt7.initialize(DeveloperKey.DEVELOPER_KEY, this);

        YouTubePlayerView yt8 = (YouTubePlayerView) findViewById(R.id.yt8);
        yt8.initialize(DeveloperKey.DEVELOPER_KEY, this);

        YouTubePlayerView yt9 = (YouTubePlayerView) findViewById(R.id.yt9);
        yt9.initialize(DeveloperKey.DEVELOPER_KEY, this);

        YouTubePlayerView yt10 = (YouTubePlayerView) findViewById(R.id.yt10);
        yt10.initialize(DeveloperKey.DEVELOPER_KEY, this);
*/

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        if (!wasRestored) {
            //try{ id=ids.get(pos);}catch(Exception x){Log.e("asd",x.toString());}
            //Log.e("position",ids.get(pos));
            player.cueVideo(id);
            //pos++;
        }
    }
        protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.yt1);
    }

    @Override
    public void onResume() {
        super.onResume();
        String sq = ("");
        Search search = new Search();
        String sql = "Select * from rapp";
        Cursor c = db.rawQuery(sql,null);
        while (c.moveToNext()){
            channels.add(c.getString(1));
            Log.i("Test", c.getString(2));
        }
        List<SearchResult> lr = search.searchByString(sq, 11,channels);
        ArrayList<VideoEntry> vE = new ArrayList<VideoEntry>();
        for (SearchResult result : lr) {
            SearchResultSnippet s = result.getSnippet();
            VideoEntry e = new VideoEntry(1, s.getTitle(), 50, 5);
            //try{ids.add( result.getId().getVideoId());}catch(Exception x){}
            id=result.getId().getVideoId();
            vE.add(e);
            System.out.println(s);
            VideoEntryAdapter improvise_overcome_adapt = new VideoEntryAdapter(vE, this);
            ListView lastvew = (ListView) findViewById(R.id.listView);
            lastvew.setPadding(lastvew.getPaddingLeft(), lastvew.getHeight(), lastvew.getPaddingRight(), 0);
            lastvew.setAdapter(improvise_overcome_adapt);
            lastvew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });
        }
    }


}