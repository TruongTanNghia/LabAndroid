package cntt61.baith29_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ActivityLogin extends AppCompatActivity {
    EditText edusername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edusername = (EditText) findViewById(R.id.edtUserName);
    }
    public void iQuiz(View v){
        Bundle b = new Bundle();
        b.putString("UN",edusername.getText().toString());
        Intent i = new Intent(this, ActivityHome.class);
        i.putExtra("data",b);
        startActivity(i);
    }
}