package net.ictcampus.voektm.rapp;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class Search{
    private YouTube.Search.List search;
    private static final String API_KEY = "AIzaSyCQJb_d69w9cusXoYHdG0JQFE54iuxuui4";
    private List<SearchResult> searchResultList;
    private ArrayList<String> channels;
    private String channel="";
    private Random r = new Random();
    public Search(){

    }
    public List<SearchResult> searchByString(String searchString, long maxResults,ArrayList<String> Channels) {
        channels=Channels;
        YouTube youtube;

        try {
            youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("Videos").build();

            search = youtube.search().list("id,snippet");
            if(searchString.equals("b")){

            int rnd=r.nextInt(17-1)-1;
                channel=channels.get(rnd);
                Log.e("asdhn",channel);
                Log.e("asdhui",channel);
                Log.e("asjdhf",searchString);
                search.setChannelId(channel);
                search.setQ("");
                search.setOrder("date");}

            else if(searchString.equals("a")){
                for(int i=0;i<channels.size();i++){
                    channel+=channels.get(i)+"|";
                }
                Log.e("asjdhf",channel);
                search.setQ(channel);
            }
            else{
                search.setQ(searchString);
                search.setOrder("viewCount");

            }
            search.setKey(API_KEY);


            search.setType("video");

            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(maxResults);


            new AsyncTask<String,String,String>() {
                @Override
                protected String doInBackground(String ... badi) {
                    SearchListResponse list;
                    try{
                     list=search.execute();
                    }
                    catch(IOException e){
                        list=null;
                    }
                    if(list!=null){
                    List<SearchResult> searchResultList = list.getItems();
                    setList(searchResultList);
                    }
                    return "hallo";
                }/*
                protected void onPostExecute(SearchListResponse result) {
                    Log.e("result",result.toString());

                }*/
            }.execute();
            while(searchResultList==null){
               // Log.e("What","FUCK");
            }
            return searchResultList;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public void setList(List<SearchResult> list){
        this.searchResultList=list;
    }
}
