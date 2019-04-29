package cn.nurasoft.zero.php;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;


/*
 * Created by Administrator on 26/02/2018.
 */

public class Functions_List_Activity extends AppCompatActivity {

    final String[] list = {"Asc", "AscW", "Format", "Hex", "Oct", "Str", "Val", "Abs", "Acos", "Asin", "Atan", "Atan2",
            "BigMul", "Ceiling", "Cos", "Cosh", "DivRem", "Exp", "Floor", "IEEERemainder", "Log", "Log10",
            "Max", "Min", "Pow", "Round", "Sign", "Sin", "Sinh", "Sqrt", "Tan", "Tanh", "Truncate", "Asc, AscW",
            "Chr, ChrW", "Filter", "Format", "FormatCurrency", "FormatDateTime", "FormatNumber", "FormatPercent",
            "InStr", "InStrRev", "Join", "LCase", "Left", "Len", "LSet", "LTrim", "Mid", "Replace", "Right",
            "RSet", "RTrim", "Space", "Split", "StrComp", "StrConv", "StrDup", "StrReverse", "Trim", "UCase",
            "CBool", "CByte", "CChar", "CDate", "CDbl", "CDec", "CInt", "CLng", "CObj", "CSByte", "CShort", "CSng",
            "CStr", "CUInt", "CULng", "CUShort", "CType", "CBool", "CByte", "CChar", "CDate", "CDbl", "CDec", "CInt",
            "CLng", "CObj", "CSByte", "CShort", "CSng", "CStr", "CUInt", "CULng", "CUShort"
    };
    ArrayAdapter<String> adapter;
    ImageView imageView;
    ListView lst;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(cn.nurasoft.zero.php.R.layout.functions_list);
        lst = findViewById(cn.nurasoft.zero.php.R.id.lst);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        imageView = findViewById(cn.nurasoft.zero.php.R.id.rtn);

        lst.setAdapter(adapter);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(Functions_List_Activity.this, Functions_Activity.class);

                Bundle bundle = new Bundle();
                bundle.putString("name", adapterView.getItemAtPosition(i).toString());
                Log.e("NAME:", adapterView.getItemAtPosition(i).toString());
                intent.putExtras(bundle);
                overridePendingTransition(cn.nurasoft.zero.php.R.anim.enter_from_right, cn.nurasoft.zero.php.R.anim.enter_from_left);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(Functions_List_Activity.this).toBundle());
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
