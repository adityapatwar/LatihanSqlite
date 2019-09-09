package com.example.latihansqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION=1;
    private static final String DB_NAME="UserInfo";
    private static final String TABLE_NAME="tbl_user";
    private static final String KEY_NAME="name";
    private static final String KEY_AGE="age";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createUserTable="Create Table " +TABLE_NAME+"("+KEY_NAME+" TEXT PRIMARY KEY,"+KEY_AGE+" INTEGER"+")";
        db.execSQL(createUserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql=("drop table if exists " +TABLE_NAME);
        db.execSQL (sql);
        onCreate (db);
    }
    public void insert (Orang orang){
        SQLiteDatabase db =getWritableDatabase();
        ContentValues values =new ContentValues ();
        values.put(KEY_NAME,orang.getName ());
        values.put(KEY_AGE,orang.getAge ());
        db.insert (TABLE_NAME,null,values);
    }
    public List<Orang> selectUserData(){
        ArrayList<Orang> userList= new ArrayList<> ();

        SQLiteDatabase db = getWritableDatabase ();
        String[] columns ={KEY_NAME,KEY_AGE};

        Cursor c =db.query (TABLE_NAME,columns,null,null,null,null,null);
        while (c.moveToNext ()){

            String name=c.getString(0);
            int age=c.getInt(1);

            Orang orang = new Orang();
            orang.setName(name);
            orang.setAge(age);
            userList.add(orang);

        }
        return userList;
    }

    public void delete(String name){
        SQLiteDatabase db = getWritableDatabase ();
        String WhereClause = KEY_NAME+"='"+name+"'";
        db.delete (TABLE_NAME,WhereClause,null);
    }

    public void update(Orang orang){
        SQLiteDatabase db = getReadableDatabase ();
        ContentValues values = new ContentValues ();
        values.put (KEY_AGE,orang.getAge ());
        String whereClause = KEY_NAME+"='"+orang.getName ()+"'";
        db.update (TABLE_NAME,values,whereClause,null);
    }
}
