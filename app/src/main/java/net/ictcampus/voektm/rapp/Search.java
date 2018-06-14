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
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class Search{
    private YouTube.Search.List search;
    private static final String API_KEY = "AIzaSyCQJb_d69w9cusXoYHdG0JQFE54iuxuui4";
    private List<SearchResult> searchResultList;
    public Search(){

    }
    public List<SearchResult> searchByString(String searchString, long maxResults) {

        YouTube youtube;

        try {
            youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("Videos").build();

            search = youtube.search().list("id,snippet");

            search.setKey(API_KEY);
            search.setQ(searchString);
search.setChannelId("UCGU9EqK5V5m141sNPCOfRBg");
            search.setType("video");
            search.setOrder("date");
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
                    Log.e("result",list.toString());
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
