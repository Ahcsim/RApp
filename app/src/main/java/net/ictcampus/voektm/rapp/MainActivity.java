package net.ictcampus.voektm.rapp;

import android.app.Activity;
import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceConfigurationError;

public class MainActivity extends LightSensitivActivity{
    private GridView gridView;
    private GridViewAdapter gridAdapter;
    TextView textView;
    Button btnFertig;
    SQLiteDatabase db;
    SQLiteOpenHelper manager;
    ArrayList<ImageItem> data;
    //Namen der Rapper in der Reihenfolge, wie die Bilder
    private String[] namesofrappers = {"Azet","Capital Bra","Farid Bang","Dardan","Kollegah","Kontra K","Miami Yacine","Raf Camora","187","UFO361","Zuna","Luciano","18 Karat","Bushido","Capo","Eno","Class X","Nimo"};
    //IDs der Rapper in der Reihenfolge wie die Namen / Bilder
    private String[] idsofrappers = {"UCDLPHv0SrvyUzct8UYQ9R_g","UCGU9EqK5V5m141sNPCOfRBg","UCbDNCzgdLlvYY9dk5M8063A", "UCqv9TYpXUAo-Qy_tOPPSUYg","UCzmO7GegLke-jb5uZSQ9_HA", "UCIgCb1dYprH140Ds0mgeAUA", "UCDLPHv0SrvyUzct8UYQ9R_g", "UChqcJ_MhP9a4bXy1jQ0QPzQ", "UCGh8tmH9x9njaI2mXfh2fyg", "UCXK2490SNd8EOm84Es6USjw", "UCDLPHv0SrvyUzct8UYQ9R_g", "UCVWm9bTQLmMwHI0To5zQFGA", "UCbDNCzgdLlvYY9dk5M8063A", "UCMHWpfahk3Kt8Us28Jg51nw","UCpIHbzhK3-sHxuGsahKacNA","UCqbKhBvxM3qWzC8huPiifYg","","UCvnCXuh_zhm75EJ89qJ95Kw"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Activity mActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Datenbank
        manager = RAppDB.getInstance(this);
        db = manager.getWritableDatabase();

        //Textview Titel
        TextView auswahl = findViewById(R.id.txtAuswahl);
        auswahl.setText("Wählen Sie Ihre  Lieblingsrapper aus");

        //Button Fertig
        btnFertig = (Button) findViewById(R.id.btnFertig);
        btnFertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fertig_click();
            }
        });

        //Gridview
        gridView = (GridView) findViewById(R.id.gridView);
        data = getData();
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, data);
        //Abos definieren
        String sqlRapper = "Select * from rapp";
        Cursor c = db.rawQuery(sqlRapper,null);
        while (c.moveToNext()){
            gridAdapter.listChecked.add(c.getString(1));
            for(ImageItem i: data){
                if(i.getTitle().equals(c.getString(1))){
                    i.setClicked();
                }
            }
        }
        //GridView Initialisieren
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageItem item = (ImageItem) parent.getItemAtPosition(position); //gedrücktes Item
                Log.e("CLicked?", ""+item.getClicked());
                String rapper =  item.getTitle();
                String idChannel = item.getIdChannel();
                if(item.getClicked()){
                    item.setUnclicked();
                    //löscht gesetzer rapper und lädt GridView neu
                    gridAdapter.listChecked.remove(rapper);
                    gridAdapter.notifyDataSetChanged();
                    db.delete(
                            "rapp",
                            "name=?",
                            new String[] {rapper}
                            );
                }else{
                    item.setClicked();
                    gridAdapter.listChecked.add(rapper);
                    gridAdapter.notifyDataSetChanged();
                    //setzt geklickter rapper in die DB und lädt Grid neu
                    ContentValues werte = new ContentValues();
                    werte.put("Name", rapper);
                    werte.put("idChannel", idChannel);
                    db.insert(
                            "rapp",
                            null,
                            werte
                    );
                }
            }
        });
        //setzt die Hintergrundfarbe der Box passend auf das Design
        TypedValue typedValue = new TypedValue();
        mActivity.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        int primaryColor = typedValue.data;
        gridView.setBackgroundColor(primaryColor);
        auswahl.setBackgroundColor(primaryColor);
    }

    //Gibt den Items ein Bild, ein Titel und eine ChannelID
    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            imageItems.add(new ImageItem(bitmap,namesofrappers[i],idsofrappers[i]));
        }
        return imageItems;
    }

    //TheMain starten
    private void fertig_click(){
        Intent startMain = new Intent(this, TheMain.class);
        startActivity(startMain);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        db = manager.getReadableDatabase(); //Datenbank wieder öffnen
    }


    @Override
    protected void onPause()
    {
        super.onPause();
        db.close(); //Datenbank schliessen
    }



}
