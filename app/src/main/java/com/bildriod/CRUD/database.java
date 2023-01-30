package com.bildriod.CRUD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class database extends SQLiteOpenHelper {
    private static final String dbname="signup.db";
    public database(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String q="create table users(id integer primary key autoincrement,name text,password text)";
        sqLiteDatabase.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
        onCreate(sqLiteDatabase);

    }
        public boolean insert_data(String name,String password)
{
        SQLiteDatabase db=this.getWritableDatabase();
    ContentValues c=new ContentValues();
    c.put("name",name);
    c.put("password", password);
    long r=db.insert("users",null,c);
    if(r==-1)
        return false;
    else
        return true;
}


public boolean update_data(String name, String password) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues c = new ContentValues();
    c.put("name", name);
    c.put("password", password);
    Cursor cursor = db.rawQuery("select * from users where name=?", new String[]{name});
    if (cursor.getCount() > 0) {
        long r = db.update("users", c, "name=?", new String[]{name});
        if (r == -1)
            return true;
        else
            return false;
    } else {
        return false;
    }

}




    public boolean delete_data(String name,String password)             //DELETE CODE
    {
     SQLiteDatabase db=this.getWritableDatabase();
     Cursor cursor=db.rawQuery("select *from users where name=?",new String[]{name});
     if(cursor.getCount()>0)
     {
      long r =db.delete("users","name=?",new String[]{name});
      if (r==-1)
              return false;
          else
              return true;
      }else
          return false;
     }





public Cursor getinfo(){

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from users",null);
        return cursor;

  }
}