package net.ictcampus.voektm.rapp;

        import android.content.Intent;
        import android.database.Cursor;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Button btnAbos = findViewById(R.id.abosBearbeiten);
        TextView txtRapper = findViewById(R.id.txtRapper);
        btnAbos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAbos_Click();
            }
        });
        /*String sql = "Select * from rapp";
        Cursor c = db.rawQuery(sql,null);
        while (c.moveToNext()){
            Log.i("Test", c.getString(1));
        }*/
    }
    public void btnAbos_Click(){
        Intent startAbos = new Intent(this, MainActivity.class);
        startActivity(startAbos);
    }
}
