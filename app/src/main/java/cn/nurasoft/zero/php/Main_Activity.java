package cn.nurasoft.zero.php;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;

import android.widget.TextView;




public class Main_Activity extends AppCompatActivity {

    ImageView tutorial,functions,key_words,namespaces,about;

    TextView Tutorial,Functions,Key_words,Namespaces,About;

    TextView version;

    static String sender;

    Intent intent;

    PackageInfo pInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);

        version=findViewById(R.id.version);



        try {
            pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            String code=pInfo.versionName;

            version.setText(getString(R.string.version)+code);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        //prepare for tutorial


        //for image button
        tutorial=findViewById(R.id.tutorial);
        functions=findViewById(R.id.functions);
        key_words=findViewById(R.id.key_words);
        namespaces=findViewById(R.id.namespaces);
        about=findViewById(R.id.about);

        //the below for Text
        Tutorial=findViewById(R.id.Tutorial);
        Functions=findViewById(R.id.Functions);
        Key_words=findViewById(R.id.Key_words);
        Namespaces=findViewById(R.id.Namespaces);
        About=findViewById(R.id.About);

        //show version
        try {
            Bundle bundle = getIntent().getExtras();
            sender=bundle.getString("language");
        }catch (NullPointerException x)
        {
            x.printStackTrace();
        }


        /*
        *check system local language;
         */
        if (sender.equals("中文")){
            Tutorial.setText("教程");
            Functions.setText("函数");
            Key_words.setText("关键字");
            Namespaces.setText("命名空间");
            About.setText("关于");
        }else {
            Tutorial.setText("Tutorial");
            Functions.setText("functions");
            Key_words.setText("key words");
            Namespaces.setText("namespaces");
            About.setText("about");
        }

        /*
        set layout;
         */

        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent();

                intent.setClass(Main_Activity.this,Tutorial_List_Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter_from_left, R.anim.enter_from_right);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent();
                intent.setClass(Main_Activity.this,About_Me_Activity.class);
                startActivity(intent);
            }
        });

        namespaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent();
                intent.setClass(Main_Activity.this,Namespaces_Activity.class);
                startActivity(intent);
            }
        });

        functions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent();
                intent.setClass(Main_Activity.this,Functions_List_Activity.class);
                startActivity(intent);
            }
        });

        key_words.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent();
                intent.setClass(Main_Activity.this,Key_words_Activity.class);
                startActivity(intent);
            }
        });

    }



}
