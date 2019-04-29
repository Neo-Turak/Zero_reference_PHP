package cn.nurasoft.zero.php;

import android.app.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import net.sqlcipher.database.SQLiteDatabase;

/**
 * Created by NURA on 2017/2/13.
 **/

public class Functions_Activity extends Activity {

    DatabaseHelperClass dbhelper;
    SQLiteDatabase db;
    ImageButton _return;

    TextView ex, Description, code, parameters, return_type, extra;

    View cview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(cn.nurasoft.zero.php.R.layout.functions_view);
        _return = findViewById(cn.nurasoft.zero.php.R.id._return);
        Description = findViewById(cn.nurasoft.zero.php.R.id.Description);
        code = findViewById(cn.nurasoft.zero.php.R.id.code);
        parameters = findViewById(cn.nurasoft.zero.php.R.id.Parameters);
        return_type = findViewById(cn.nurasoft.zero.php.R.id.return_type);
        extra = findViewById(cn.nurasoft.zero.php.R.id.extra);
        ex = findViewById(cn.nurasoft.zero.php.R.id.ex);

        cview = findViewById(cn.nurasoft.zero.php.R.id.CodeView);

        dbhelper = new DatabaseHelperClass(Functions_Activity.this);
        db = dbhelper.getReadableDatabase(getString(cn.nurasoft.zero.php.R.string.pwd));

        Bundle bundle = this.getIntent().getExtras();

        String s = null;
        try {
            s = bundle.getString("name");
        } catch (NullPointerException e) {
            Log.e("It is emty!", e.getMessage());
        }

        _return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dbhelper.openDataBase();

        Description.setText(dbhelper.Get_Function(s)[1]);
        Description.setMovementMethod(new ScrollingMovementMethod());
        parameters.setText(dbhelper.Get_Function(s)[3]);
        code.setText(dbhelper.Get_Function(s)[2]);

        if (code.getText() == "") {
            Log.e("Code:", code.getText().toString());
            cview.setVisibility(View.INVISIBLE);
            code.setBackgroundColor(Color.WHITE);
        }
        code.setMovementMethod(new ScrollingMovementMethod());
       extra.setText(dbhelper.Get_Function(s)[5]);
        return_type.setText(dbhelper.Get_Function(s)[4]);
        if (extra.getText() == "" || extra.getText() == null) {
            ex.setVisibility(View.INVISIBLE);
        }
        dbhelper.close();
    }
}
