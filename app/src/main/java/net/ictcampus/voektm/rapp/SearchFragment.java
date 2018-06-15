package net.ictcampus.voektm.rapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchFragment extends android.support.v4.app.Fragment {

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_search_fragment, container, false);
    }
    @Override
    public void onResume(){
        super.onResume();
        Button btn= getView().findViewById(R.id.button7);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input= getView().findViewById(R.id.textinput);
                TheMain main=(TheMain) getActivity();
                Intent myIntent = new Intent(main, SearchActivity.class);
                myIntent.putExtra("Suchbegriff",input.getText().toString());
                main.startActivity(myIntent);

            }
        });

            }

}