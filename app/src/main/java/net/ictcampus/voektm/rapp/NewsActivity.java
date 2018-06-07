package net.ictcampus.voektm.rapp;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.ServiceConfigurationError;

public class NewsActivity extends LightSensitivActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Activity mActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ImageView v = findViewById(R.id.logoView);
        TypedValue typedValue = new TypedValue();
        mActivity.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        int primaryColor = typedValue.data;
        v.setBackgroundColor(primaryColor);
    }
    public void onClick(View v) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
