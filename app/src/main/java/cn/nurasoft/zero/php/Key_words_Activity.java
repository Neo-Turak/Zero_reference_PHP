package cn.nurasoft.zero.php;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/*
 * Created by Administrator on 26/02/2018.
 */

public class Key_words_Activity extends AppCompatActivity {

    TextView key1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(cn.nurasoft.zero.php.R.layout.key_words_view);


        key1 = findViewById(cn.nurasoft.zero.php.R.id.Key1);
        String[] key_words=new String[78];
        key_words=getResources().getStringArray(cn.nurasoft.zero.php.R.array.key_words);

        int i=0;
        StringBuilder builder=new StringBuilder();
        for (String x:key_words){
            i++;
            if (i==4){
                builder.append(x).append("\n");
                i=0;
            }else{
                builder.append(x).append("      ");
            }
        }
        key1.setText(builder.toString());
        builder.delete(0,builder.length());



    }
}


