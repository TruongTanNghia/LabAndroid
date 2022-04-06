package cntt61.baith29_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityHome extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textView = (TextView) findViewById(R.id.tvUserName);
        Intent i = getIntent();
        Bundle b = i.getBundleExtra("data");
        String strUN = b.getString("UN");
        textView.setText(textView.getText().toString()+strUN);
    }
}