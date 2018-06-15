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


public  class SearchActivity extends YoutubeFailureActivity{
    /*
    Activity mActivity;
    mActivity = this;*/
    private ArrayList<String> ids=new ArrayList<String>();
    private String id;
    private String suchbegriff;
    private ArrayList<String> channels=new ArrayList<String>();
    private String view;
    public SearchActivity(){
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
        suchbegriff=intent.getStringExtra("Suchbegriff");
        YouTubePlayerView yts = (YouTubePlayerView) findViewById(R.id.yts);
        yts.initialize(DeveloperKey.DEVELOPER_KEY, this);

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
        String sq = (suchbegriff);
        Log.e("iuhad",suchbegriff);
        Search search = new Search();
        List<SearchResult> lr = search.searchByString(sq, 1,channels);
        ArrayList<VideoEntry> vE = new ArrayList<VideoEntry>();
        for (SearchResult result : lr) {
            SearchResultSnippet s = result.getSnippet();
            VideoEntry e = new VideoEntry(1, s.getTitle(), 50, 5);
            //try{ids.add( result.getId().getVideoId());}catch(Exception x){}
            id=result.getId().getVideoId();
            vE.add(e);
            System.out.println(s);
            VideoEntryAdapter improvise_overcome_adapt = new VideoEntryAdapter(vE, this);
            ListView lastvew = (ListView) findViewById(R.id.lists);
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