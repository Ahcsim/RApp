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

public class MainActivity extends LightSensitivActivity{
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Activity mActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView v = findViewById(R.id.logoView);
        textView = findViewById(R.id.textView);
        TypedValue typedValue = new TypedValue();
        mActivity.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        int primaryColor = typedValue.data;
        v.setBackgroundColor(primaryColor);

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
