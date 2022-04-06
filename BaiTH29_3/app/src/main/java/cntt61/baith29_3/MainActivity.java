package cntt61.baith29_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void iLogin(View v){
        Intent dangnhap = new Intent(this, ActivityLogin.class);
        startActivity(dangnhap);
    }
}