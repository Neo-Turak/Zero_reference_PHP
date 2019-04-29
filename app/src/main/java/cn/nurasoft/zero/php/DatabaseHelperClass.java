package cn.nurasoft.zero.php;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import net.sqlcipher.Cursor;
import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;




/*
 * Created by Administrator on 20/03/2018.
 */

public class DatabaseHelperClass extends SQLiteOpenHelper {

    private static final String fileName = "cSharp.db";
    private String TAG ="TAG";
    private final static String pwd="ILOVEYOU";
    private static final int DB_VERSION = 1;   // 数据库版本
    private static String DB_PATH = "";
    private static SQLiteDatabase sqLiteDatabase;

    private String arg[]=new String[6];

    DatabaseHelperClass(Context context) {



        super(context, fileName, null, DB_VERSION);
        DB_PATH = "/data/data/"+context.getPackageName() + "/dbase/";
        SQLiteDatabase.loadLibs(context);

        final String path = "/data/data/" + context.getPackageName() + "/dbase/";

    }


    void openDataBase() throws SQLException {
        String myPath = DB_PATH + fileName;
        Log.e(TAG,myPath);
        sqLiteDatabase = SQLiteDatabase.openDatabase(myPath, pwd,null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if (sqLiteDatabase != null)
            sqLiteDatabase.close();
        super.close();
    }



     ArrayList<String> getTitle(){
        Cursor cursor;
        ArrayList<String> result=new ArrayList<>();
        String query="select title_EN from Tutorial";
        cursor=sqLiteDatabase.rawQuery(query,null);

        if (cursor.getCount()>0){
            if (cursor.moveToFirst()){
                do {
                    result.add(cursor.getString(0));
                }
                while (cursor.moveToNext());
            }
            cursor.close();
        }
    return result;
    }

    ArrayList<String> Get_Namespaces(){
        Cursor cursor;
        ArrayList<String> result=new ArrayList<>();
        String query="select name from namespaces";
        cursor=sqLiteDatabase.rawQuery(query,null);

        if (cursor.getCount()>0){
            if (cursor.moveToFirst()){
                do {
                    result.add(cursor.getString(0));
                }
                while (cursor.moveToNext());
            }
            cursor.close();
        }
        return result;
    }

    String Get_Description_CN(String namespace){
       Cursor cursor;
       String result=null;
       String query="select desc_cn from namespaces where name='"+namespace+"'";
       cursor=sqLiteDatabase.rawQuery(query,null);

       if (cursor.getCount()>0){
           if (cursor.moveToFirst()){
               do {
                   result=cursor.getString(0);
               }
               while (cursor.moveToNext());
           }
           cursor.close();
       }
       return result;
    }

    String[] Get_Function(String CName) {
        arg[0] = null;
        arg[1] = null;
        arg[2] = null;
        arg[3] = null;
        arg[4] = null;
        arg[5] = null;
        String query = "select * from functions where name='" + CName + "'";

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    arg[0] = cursor.getString(0);
                    arg[1] = cursor.getString(1);
                    arg[2] = cursor.getString(2);
                    arg[3] = cursor.getString(3);
                    arg[4] = cursor.getString(4);
                    arg[5] = cursor.getString(5);
                }
                while (cursor.moveToNext());
            }
            cursor.close();
        }
        return arg;
    }

    String Get_Description_EN(String namespace){
        Cursor cursor;
        String result=null;
        String query="select desc_en from namespaces where name='"+namespace+"'";
        cursor=sqLiteDatabase.rawQuery(query,null);

        if (cursor.getCount()>0){
            if (cursor.moveToFirst()){
                do {
                    result=cursor.getString(0);
                }
                while (cursor.moveToNext());
            }
            cursor.close();
        }
        return result;
    }

    byte[] getPDF(String title_EN){

        Cursor cursor;
        byte[] result=null;

        String query="select main from Tutorial where title_EN='"+ title_EN+"'";

        cursor=sqLiteDatabase.rawQuery(query,null);

        if (cursor.getCount()>0){
            if (cursor.moveToFirst()){
                do {
                    result=cursor.getBlob(0);
                }
                while (cursor.moveToNext());
            }
            cursor.close();
        }
        return result;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int ii) {

    }

    public void CheckDir(Context context,File path) {

        if (!path.exists()) {

            Log.e("Check:", String.valueOf(path.mkdirs()));

            path.mkdirs();

            CopyFile(context, path);

            Log.e(TAG, "Create folder and Copy file！");

        } else {

            Log.e(TAG, "存在！" + path);
        }
    }

    private void CopyFile(Context context, File Path) {

        AssetManager assetManager = context.getAssets();
        try {
            InputStream in = assetManager.open(fileName);

            OutputStream out = new FileOutputStream(Path + "/" + fileName);

            Log.e(TAG,"复制文件："+out.toString());

            byte[] buffer = new byte[10240];

            int read = in.read(buffer);

            while (read != -1) {
                out.write(buffer, 0, read);
                read = in.read(buffer);

            }

        } catch (Exception e) {
            e.getMessage();
        }
    }

}
