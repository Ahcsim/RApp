package net.ictcampus.voektm.rapp;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceConfigurationError;

public class MainActivity extends LightSensitivActivity{
    private GridView gridView;
    private GridViewAdapter gridAdapter;
    TextView textView;
    private String[] namesofrappers = {"Azet","Capital Bra","Farid Bang","Dardan","Kollegah","Kontra K","Miami Yacine","Raf Camora","187","UFO361","Zuna","Luciano","18 Karat","Bushido","Capo","Eno"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Activity mActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView v = findViewById(R.id.logoView);
        textView = findViewById(R.id.textView);
        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData());
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

            }
        });
        TypedValue typedValue = new TypedValue();
        mActivity.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        int primaryColor = typedValue.data;
        gridView.setBackgroundColor(primaryColor);

    }

    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            imageItems.add(new ImageItem(bitmap,namesofrappers[i]));
        }
        return imageItems;
    }

    @Override
    protected void onResume() {
        super.onResume();
}


    @Override
    protected void onPause() {
        super.onPause();
    }


}
