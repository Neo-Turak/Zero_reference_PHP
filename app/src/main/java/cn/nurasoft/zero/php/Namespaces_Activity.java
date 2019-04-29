package cn.nurasoft.zero.php;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import java.util.ArrayList;

import net.sqlcipher.database.SQLiteDatabase;

/*
 * Created by Administrator on 25/02/2018.
 */

public class Namespaces_Activity extends AppCompatActivity {

    DatabaseHelperClass dbase;
    SQLiteDatabase db;
    ArrayAdapter<String> adapter;
    ListView listView;
    ImageButton rtn;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(cn.nurasoft.zero.php.R.layout.namespace_list);


        dbase = new DatabaseHelperClass(Namespaces_Activity.this);

        db = dbase.getReadableDatabase(getString(cn.nurasoft.zero.php.R.string.pwd));

        dbase.openDataBase();

     final  ArrayList<String> list= dbase.Get_Namespaces();

        dbase.close();

        listView = findViewById(cn.nurasoft.zero.php.R.id.lister);
        rtn = findViewById(cn.nurasoft.zero.php.R.id.rtn);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String name = adapterView.getItemAtPosition(i).toString();
                Intent intent=new Intent();
                intent.putExtra("namespaces",name);
                intent.setClass(Namespaces_Activity.this,Namespaces_View_Activity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(Namespaces_Activity.this).toBundle());


            }
        });

        rtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }


}
