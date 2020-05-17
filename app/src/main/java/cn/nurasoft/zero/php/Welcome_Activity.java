package cn.nurasoft.zero.php;


import android.annotation.SuppressLint;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;

import android.view.Window;

import net.sqlcipher.database.SQLiteDatabase;

import java.io.File;
import java.util.Locale;



/**
 * Created by miro on 11/02/17~.~
 **/

public class Welcome_Activity extends Activity {

    DatabaseHelperClass dbhelper;
    SQLiteDatabase db;
    private Handler handler;

    String language;

    Context context;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setTheme(cn.nurasoft.zero.php.R.style.StartTheme);

        context=this;



        language= Locale.getDefault().getDisplayLanguage();

        dbhelper = new DatabaseHelperClass(Welcome_Activity.this);

        dbhelper.CheckDir(context,new File("/data/data/"+context.getPackageName() + "/dbase/"));

        db = dbhelper.getReadableDatabase(getString(cn.nurasoft.zero.php.R.string.pwd));

            dbhelper.onUpgrade(db, 1, 2);

        //show the main activity
        final  Intent intent=new Intent();
        handler =  new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what==1)
                intent.setClass(Welcome_Activity.this, Main_Activity.class);
                intent.putExtra("language",language);
                Welcome_Activity.this.startActivity(intent);
            }
        };

        new Thread(new Runnable(){
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    Thread.sleep(3000) ;
                } catch (Exception e) {
                    // TODO: handle exception
                }
                handler.sendEmptyMessage(1) ;
            }

        }).start() ;
    }


}
