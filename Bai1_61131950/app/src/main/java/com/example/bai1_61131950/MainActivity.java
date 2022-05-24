package com.example.bai1_61131950;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView ketqua ;
    Button dientich , chuvi ;
    EditText edtCanh1, edtCanh2;
    EditText edtChieuCao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ketqua = findViewById(R.id.ketqua);
        dientich = findViewById(R.id.dientich);
        chuvi = findViewById(R.id.chuvi);
        edtCanh1 = findViewById(R.id.canh1);
        edtCanh2 = findViewById(R.id.canh2);
        edtChieuCao = findViewById(R.id.canh3);

        dientich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int chieucao = Integer.valueOf(edtChieuCao.getText().toString().trim());
                int canhday = Integer.valueOf(edtCanh2.getText().toString().trim());
                int dientichhbh = chieucao*canhday;
                ketqua.setText("Diện tich: "+String.valueOf(dientichhbh));
            }
        });
        chuvi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int canhday = Integer.valueOf(edtCanh2.getText().toString().trim());
                int canhke = Integer.valueOf(edtCanh1.getText().toString().trim());
                int chuvi = 2*(canhday + canhke) ;
                ketqua.setText("Chu vi: "+String.valueOf(chuvi));
            }
        });
    }
}