package cn.nurasoft.zero.php;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by miro on 12/02/17~.~
 **/

public class Tutorial_List_Activity extends Activity {

    ImageButton _return;
    ListView listView;

    DatabaseHelperClass databaseHelperClass;
    SQLiteDatabase db;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(cn.nurasoft.zero.php.R.layout.tutorial_list);

        /*
        for database
         */
        databaseHelperClass = new DatabaseHelperClass(Tutorial_List_Activity.this);
        db = databaseHelperClass.getReadableDatabase("ILOVEYOU");
        databaseHelperClass.openDataBase();
        final List<String> tutorial = databaseHelperClass.getTitle();
        databaseHelperClass.close();
        /*
         *********end**********
         */

        _return = findViewById(cn.nurasoft.zero.php.R.id._return);


        _return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Tutorial_List_Activity.this, Main_Activity.class);
                Tutorial_List_Activity.this.startActivity(intent);
            }
        });


        // final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,tutorial);

        listView = findViewById(cn.nurasoft.zero.php.R.id.listview);

        final ArrayList<HashMap<String, Object>> listItem = new ArrayList<>();
        for (int i = 0; i < 39; i++) {
            HashMap<String, Object> map = new HashMap<>();

            map.put("title", tutorial.get(i));
            if (i<28) {
                map.put("type", "Basic");
            }else{
                map.put("type", "Advanced");
            }
            map.put("arrow", cn.nurasoft.zero.php.R.drawable.arrows);
            listItem.add(map);

            SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,// 数据源
                    cn.nurasoft.zero.php.R.layout.list,// ListItem的XML实现
                    // 动态数组与ImageItem对应的子项
                    new String[]{"title", "type", "arrow"},
                    // ImageItem的XML文件里面的一个ImageView,两个TextView ID
                    new int[]{cn.nurasoft.zero.php.R.id.title, cn.nurasoft.zero.php.R.id.type, cn.nurasoft.zero.php.R.id.arrow});

            // 添加并且显示
            listView.setAdapter(listItemAdapter);


            // listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Map<String, Object> item = (HashMap<String, Object>)adapterView.getItemAtPosition(i);

                   Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), Tutorial_Activity.class);

                    intent.putExtra("title", (String)item.get("title"));
                    startActivity(intent);
                }
            });

        }

    }
}
