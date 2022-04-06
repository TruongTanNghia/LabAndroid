package TruongTanNghia.baikt2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioButton rd_13, rd_15, rd_18;
    Button tinhtoan;
    EditText nhapdulieu;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nhapdulieu=(EditText) findViewById(R.id.edtnhap);
        textView=(TextView) findViewById(R.id.tvkq1);
        rd_13=(RadioButton) findViewById(R.id.radio_13);
        rd_15=(RadioButton) findViewById(R.id.radio_15);
        rd_18=(RadioButton) findViewById(R.id.radio_18);
        tinhtoan=(Button) findViewById(R.id.btntinhtoan);
        tinhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float nhap=Float.parseFloat(nhapdulieu.getText().toString());
                if(rd_13.isChecked()){
                    float tt= (float) (nhap*0.13);
                    textView.setText("Your tip will be $"+tt);
                    Toast.makeText(MainActivity.this, "Your tip will be $"+tt, Toast.LENGTH_SHORT).show();
                }
                else if(rd_15.isChecked()){
                    float tt= (float) (nhap*0.15);
                    textView.setText("Your tip will be $"+tt);
                    Toast.makeText(MainActivity.this, "Your tip will be $"+tt, Toast.LENGTH_SHORT).show();
                }
                else if(rd_18.isChecked()){
                    float tt= (float) (nhap*0.18);
                    textView.setText("Your tip will be $"+tt);
                    Toast.makeText(MainActivity.this, "Your tip will be $"+tt, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}