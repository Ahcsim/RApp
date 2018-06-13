package net.ictcampus.voektm.rapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends LightSensitivActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
    protected void onResume() {
        super.onResume();
        String sq = ("Capital Bra");
        Search search = new Search();
        List<SearchResult> lr = search.searchByString(sq,10);
        ArrayList<VideoEntry> vE = new ArrayList<VideoEntry>();
        for (SearchResult result : lr) {
            SearchResultSnippet s = result.getSnippet();
            VideoEntry e = new VideoEntry(1, s.getTitle(), 50, 5);
            vE.add(e);
            System.out.println(s);
            VideoEntryAdapter improvise_overcome_adapt = new VideoEntryAdapter(vE,this);
            ListView lastvew = (ListView) findViewById(R.id.listView);
            lastvew.setPadding(lastvew.getPaddingLeft(),lastvew.getHeight(),lastvew.getPaddingRight(),0);
            lastvew.setAdapter(improvise_overcome_adapt);
            lastvew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });
        }


    }
}