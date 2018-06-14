package net.ictcampus.voektm.rapp;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;

public class LightSensitivActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor sensor;
    private int[] themes = {R.style.RApp_Light, R.style.RApp};
    private static int listPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Activity mActivity = this;
        super.onCreate(savedInstanceState);
        setTheme(themes[listPos]);
        TypedValue typedValue = new TypedValue();
        mActivity.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

    }
        @Override
        protected void onResume() {
            super.onResume();
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        }


        @Override
        protected void onPause() {
            super.onPause();
            sensorManager.unregisterListener(this);
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
                if (event.values[0] <= 100) {
                    if (listPos != 1) {
                        listPos = 1;
                        Intent intent = getIntent();
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent);
                    }
                }else if(event.values[0] > 120){
                    if (listPos != 0) {
                        listPos = 0;
                        Intent intent = getIntent();
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent);
                    }
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
        public void setListPos(int pos){
            listPos = pos;
            sensorManager.unregisterListener(this);
        }

}
