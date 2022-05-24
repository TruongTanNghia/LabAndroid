package ntu.bookman;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase databaseBook;
    EditText edMa, edTen, edSoTrang, edGia, edMoTa;
    Button btnThem, btnSua, btnXoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Tạo mới/Mở CSDL
        databaseBook = SQLiteDatabase.openOrCreateDatabase("data/data/ntu.bookman/MyBook.db",null);

        //TaoBangVaThemDuLieu();
        NapDSVaoListView();

        btnThem = (Button) findViewById(R.id.btnThem);
        btnSua = (Button) findViewById(R.id.btnSua);
        btnXoa = (Button) findViewById(R.id.btnXoa);

        EditText edChon = (EditText) findViewById(R.id.edMaChon);


        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edChon = (EditText) findViewById(R.id.edMaChon);
                String maChonString = edChon.getText().toString();
                int maChon = Integer.parseInt(maChonString);
                AlertDialog.Builder alBuilder = new AlertDialog.Builder(MainActivity.this);
                alBuilder.setTitle("Xác nhận đề xóa");
                alBuilder.setCancelable(true);
                alBuilder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Xoa(maChon);
                    }
                });
                alBuilder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Hủy yêu cầu xóa!", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = alBuilder.create();
                alertDialog.show();

            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentThem = new Intent(MainActivity.this,ThemActivity.class);
                startActivity(intentThem);
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy mã sách vừa chọn
                String maSachString = edChon.getText().toString();
                int maSach = Integer.parseInt(maSachString);
                // Tạo intent
                Intent intentSua = new Intent(MainActivity.this,SuaActivity.class);
                // gói dữ liệu
                intentSua.putExtra("masach",maSach);
                // Khởi động SuaACtivity
                startActivity(intentSua);
            }
        });
        /*edMa = (EditText) findViewById(R.id.edMa);
        edTen = (EditText) findViewById(R.id.edTen);
        edSoTrang = (EditText) findViewById(R.id.edSoTrang);
        edGia = (EditText) findViewById(R.id.edGia);
        edMoTa = (EditText) findViewById(R.id.edMoTa);*/

        /*int ma = Integer.parseInt(edMa.getText().toString());
        String ten = edTen.getText().toString();
        int soTrang = Integer.parseInt(edSoTrang.getText().toString());
        float gia = Float.parseFloat(edGia.getText().toString());
        String moTa = edMoTa.getText().toString();*/

        //CapNhat(2,"Tôi thấy hoa vàng trên cỏ xanh", 100, 80000, "Tiểu thuyết");

    }

    void NapDSVaoListView(){
        ListView listView = (ListView) findViewById(R.id.lvSach);
        //Nguồn dữ liệu
        //Mỗi ptu 1 string gồm id name price
        ArrayList<String> dsSach = new ArrayList<String>();
        //Mở db, select dl, đưa vào array
        //...
        Cursor cs = databaseBook.rawQuery("SELECT * FROM BOOKS", null);
        cs.moveToFirst();
        //duyệt từng dòng

        if(cs.moveToFirst()) {
            do{
                //lấy mã sách
                int ms = cs.getInt(0);//cojt 0
                //lấy tên sách
                String tenSach = cs.getString(1);
                int trang = cs.getInt(2);
                float gia = cs.getFloat(3);
                String dong = String.valueOf(ms) + " -----" +
                        tenSach + " ----" + String.valueOf(trang) + " ----" + String.valueOf(gia);
                dsSach.add(dong);
            }while (cs.moveToNext());

        }
        //dsSach.add("try");
        //dsSach.add("Try Again");
        //....
        //Adapter
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,dsSach);
        //Gắn vào ListView
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String dongChon = dsSach.get(i);
                //Xử lý tách lấy phần số
                int k = dongChon.indexOf(" "); //tim vị trí xuất hiện đầu tiên của khoảng trắng
                String ma = dongChon.substring(0,k); //trích lấy phần mã
                //test thử
                EditText edChon = (EditText) findViewById(R.id.edMaChon);
                edChon.setText(ma);
            }
        });
    }
    void TaoBangVaThemDuLieu(){

        //Xóa bảng nếu đã có
        String sqlXoaBang = "DROP TABLE IF EXISTS BOOKS;\n";
        databaseBook.execSQL(sqlXoaBang);
        //Lệnh tạo bảng
        String sqlTaoBang = "CREATE TABLE BOOKS(BookID integer PRIMARY KEY, BookName text,\n" +
                "Page integer, Price Float, Description text);\n";
        databaseBook.execSQL(sqlTaoBang);
        //Thêm bảng ghi
        String sqlThem1 = "INSERT INTO BOOKS VALUES(1, 'Java', 100, 9.99, 'sách về\n" +
                "java');\n";
        databaseBook.execSQL(sqlThem1);
        String sqlThem2 = "INSERT INTO BOOKS VALUES(2, 'Android', 320, 19.00, 'Android cơ\n" +
                "bản');\n";
        databaseBook.execSQL(sqlThem2);
        String sqlThem3 = "INSERT INTO BOOKS VALUES(3, 'Học làm giàu', 120, 0.99, 'sách đọc\n" +
                "cho vui');\n";
        databaseBook.execSQL(sqlThem3);
        String sqlThem4 = "INSERT INTO BOOKS VALUES(4, 'Tử điển Anh-Việt', 1000, 29.50, 'Từ\n" +
                "điển 100.000 từ');\n";
        databaseBook.execSQL(sqlThem4);
    }

    void ThemMoiSach(int ma, String ten, int soTrang, float gia, String moTa) {
        String sqlThem = "INSERT INTO BOOKS VALUES("+ma+","+"'"+ten+"'"+soTrang+","+gia+", '"+moTa+"')";
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

    void Xoa(int ma)
    {
        String[] thamSotruyen={String.valueOf(ma)};
        int kq = databaseBook.delete("BOOKS", "BookID=?",thamSotruyen);
        if(kq==0)
            Toast.makeText(this, "Không xóa được", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
    }
}