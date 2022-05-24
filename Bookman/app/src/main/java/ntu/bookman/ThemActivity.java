package ntu.bookman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThemActivity extends AppCompatActivity {
    SQLiteDatabase databaseBook;
    Button btnThucHien;
    EditText edMa, edTen, edSoTrang, edGia, edMoTa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);

        databaseBook = SQLiteDatabase.openOrCreateDatabase("data/data/ntu.bookman/MyBook.db",null);
        btnThucHien = (Button) findViewById(R.id.btnThucHien);
        edMa = (EditText) findViewById(R.id.edMa);
        edTen = (EditText) findViewById(R.id.edTen);
        edSoTrang = (EditText) findViewById(R.id.edSoTrang);
        edGia = (EditText) findViewById(R.id.edGia);
        edMoTa = (EditText) findViewById(R.id.edMoTa);


        btnThucHien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maString = edMa.getText().toString();
                int ma = Integer.parseInt(maString);
                String ten = edTen.getText().toString();
                String soTrangString = edSoTrang.getText().toString();
                int soTrang = Integer.parseInt(soTrangString);
                String giaString = edGia.getText().toString();
                float gia = Float.parseFloat(giaString);
                String moTa = edMoTa.getText().toString();
                ThemMoiSach(ma,ten,soTrang,gia,moTa);
                Intent intentMain = new Intent(ThemActivity.this,MainActivity.class);
                startActivity(intentMain);
            }
        });

    }
    void ThemMoiSach(int ma, String ten, int soTrang, float gia, String moTa) {
        String sqlThem = "INSERT INTO BOOKS VALUES("+ma+","+"'"+ten+"',"+soTrang+","+gia+",'"+moTa+"')";
        databaseBook.execSQL(sqlThem);

        ContentValues row = new ContentValues();
        row.put("BookID", ma);
        row.put("BookName", ten);
        row.put("Page", soTrang);
        row.put("Price", gia);
        row.put("Description", moTa);
        long kq = databaseBook.insert("BOOKS", null, row);
        if(kq==1)
            Toast.makeText(this, "Không thêm được", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
    }
}