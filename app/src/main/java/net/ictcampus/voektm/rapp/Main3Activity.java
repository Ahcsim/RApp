package net.ictcampus.voektm.rapp;


import android.os.Bundle;
import android.widget.TextView;

public class Main3Activity extends Navigation {
    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mActivity = this;

        mTextMessage = (TextView) findViewById(R.id.message);

    }

}
