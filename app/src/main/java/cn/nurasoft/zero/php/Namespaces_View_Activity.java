package cn.nurasoft.zero.php;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.Window;
import android.widget.TextView;

import net.sqlcipher.database.SQLiteDatabase;

public class Namespaces_View_Activity extends AppCompatActivity {


    TextView desc_EN,desc_CN,name;
    DatabaseHelperClass dbase;
    SQLiteDatabase db;
    Context context;
    String namespaces;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());

        setContentView(cn.nurasoft.zero.php.R.layout.namespaces_view);
        context=Namespaces_View_Activity.this;

        dbase=new DatabaseHelperClass(context);
        db=dbase.getReadableDatabase(getString(cn.nurasoft.zero.php.R.string.pwd));

        Bundle bundle=getIntent().getExtras();

        namespaces=bundle.getString("namespaces");

        dbase.openDataBase();

        desc_EN=findViewById(cn.nurasoft.zero.php.R.id.desc_EN);
        desc_CN=findViewById(cn.nurasoft.zero.php.R.id.desc_CN);

        name=findViewById(cn.nurasoft.zero.php.R.id.space_name);

        name.setText(namespaces);
        desc_EN.setText(dbase.Get_Description_EN(namespaces));
        desc_CN.setText(dbase.Get_Description_CN(namespaces));

        dbase.close();

    }
}
