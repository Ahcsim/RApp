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

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView textView;
    SensorManager sensorManager;
    Sensor sensor;
    private int[] themes = {R.style.RApp_Light, R.style.RApp};
    private static int listPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Activity mActivity = this;
        super.onCreate(savedInstanceState);
        setTheme(themes[listPos]);
        setContentView(R.layout.activity_main);
         ImageView v = findViewById(R.id.logoView);
        textView = findViewById(R.id.textView);

        TypedValue typedValue = new TypedValue();
        mActivity.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        int primaryColor = typedValue.data;
        v.setBackgroundColor(primaryColor);

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
            textView.setText("" + event.values[0]);
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
}
