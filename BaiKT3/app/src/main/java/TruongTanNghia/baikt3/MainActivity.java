package TruongTanNghia.baikt3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtitem,edtVT;
    Button btnlt,btnls,btnx;
    ListView listView;
    ArrayList<String> arrayList=null;
    ArrayAdapter<String> adapter=null;
    int vt = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtitem=(EditText) findViewById(R.id.edtItem);
        edtVT=(EditText) findViewById(R.id.edtVitri);
        btnlt=(Button) findViewById(R.id.btnLuuthem);
        btnls=(Button) findViewById(R.id.btnLuusua);
        btnx=(Button) findViewById(R.id.btnXoa);
        listView=(ListView) findViewById(R.id.lv);
        arrayList = new ArrayList<>();

        arrayList.add("Content");
        arrayList.add("Graphics");
        arrayList.add("Hardware");
        arrayList.add("Media");
        arrayList.add("NFC");
        arrayList.add("OS");
        arrayList.add("Preference");
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);

        btnlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String chuoi = edtitem.getText().toString();
                arrayList.add(chuoi);
                adapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                edtitem.setText(arrayList.get(i));
                edtVT.setText(String.valueOf(i));
                vt = i;
            }
        });

        btnls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.set(vt,edtitem.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

        btnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.remove(vt);
                adapter.notifyDataSetChanged();
            }
        });
    }
}