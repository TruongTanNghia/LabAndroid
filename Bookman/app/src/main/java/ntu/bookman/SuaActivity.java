package ntu.bookman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SuaActivity extends AppCompatActivity {
    SQLiteDatabase databaseBook;
    Button btnThucHien;
    EditText edMa, edTen, edSoTrang, edGia, edMoTa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua);

        databaseBook = SQLiteDatabase.openOrCreateDatabase("data/data/ntu.bookman/MyBook.db",null);

        edMa = (EditText) findViewById(R.id.edMa);
        edTen = (EditText) findViewById(R.id.edTen);
        edSoTrang = (EditText) findViewById(R.id.edSoTrang);
        edGia = (EditText) findViewById(R.id.edGia);
        edMoTa = (EditText) findViewById(R.id.edMoTa);
        btnThucHien = (Button) findViewById(R.id.btnThucHien);

        // Lấy về Intent sửa đã gửi
        Intent intentGetMa = getIntent();
        // Lấy ra dữ liệu đã gói thông qua key là masach
        int maSachSua = intentGetMa.getIntExtra("masach",0);
        String sqlChon = "SELECT * FROM BOOKS";
        Toast.makeText(this, "Mã: "+maSachSua, Toast.LENGTH_SHORT).show();
        String[] thamsoTruyen ={String.valueOf(maSachSua)};
        Cursor cs= databaseBook.rawQuery("SELECT * FROM BOOKS where BookID=?", thamsoTruyen);
        if(cs.moveToFirst()) {
            do{
                int ma = cs.getInt(0);
                String ten = cs.getString(1);
                int soTrang = cs.getInt(2);
                Float gia = cs.getFloat(3);
                String moTa = cs.getString(4);

                edMa.setText(String.valueOf(ma));
                edTen.setText(ten);
                edSoTrang.setText(String.valueOf(soTrang));
                edGia.setText(String.valueOf(gia));
                edMoTa.setText(moTa);
            }while (cs.moveToNext());

        }


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

                CapNhat(ma,ten,soTrang,gia,moTa);
                Intent intentMain = new Intent(SuaActivity.this,MainActivity.class);
                startActivity(intentMain);
            }
        });
    }
    void CapNhat(int maGoc, String tenMoi, int soTrang, float gia, String moTa)
    {
        String[] thamSotruyen={String.valueOf(maGoc)};
        ContentValues row = new ContentValues();
        row.put("BookName", tenMoi);
        row.put("Page", soTrang);
        row.put("Price", gia);
        row.put("Description", moTa);
        int kq = databaseBook.update("BOOKS", row, "BookID=?",thamSotruyen);
        if(kq==0)
            Toast.makeText(this, "Không cập nhật được", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
    }
}