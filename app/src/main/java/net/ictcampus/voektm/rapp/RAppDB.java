package net.ictcampus.voektm.rapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RAppDB extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "RApp.db";

    private static RAppDB instance = null;

    private RAppDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized RAppDB getInstance(Context context){
        if (instance ==null){
            instance = new RAppDB(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE rapp (_id INTEGER PRIMARY KEY , Name TEXT)");
    }

    public void insertdb(SQLiteDatabase db, String insertvalue){
        ContentValues werte = new ContentValues();
        werte.put("name", insertvalue);
        db.insert(
                "rapp",
                null,
                werte
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS rapp");
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onDowngrade(db, oldVersion, newVersion);
    }
}