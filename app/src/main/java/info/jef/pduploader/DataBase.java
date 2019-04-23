package info.jef.pduploader;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 11/21/2017.
 */

public class DataBase extends SQLiteOpenHelper {

    private static final String DB_NAME = "graphical.db";
    private static final int DB_VER = 2;
    public static final String TAB = "userreg";
    //public static final String TAB = "item";


    public DataBase(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "create table userreg(name TEXT NOT NULL, mob_no TEXT NOT NULL,username TEXT NOT NULL PRIMARY KEY,password TEXT NOT NULL)";
        db.execSQL(query);
        String query1="create table locks (name text not null,password text not null)";
        db.execSQL(query1);
    }

    public void registration(String name, String mob_num, String username, String pass) {

        SQLiteDatabase db = this.getReadableDatabase();
        String q = "insert into userreg values('" + name + "','" + mob_num + "','" + username + "','" + pass + "')";
        db.execSQL(q);
    }
    public void registrationlock(String a,String b) {

        SQLiteDatabase db = this.getReadableDatabase();
        String q = "insert into locks values('"+a+"','" +b+ "')";
        db.execSQL(q);
    }
    public void updationlock(String a,String b) {

        SQLiteDatabase db = this.getReadableDatabase();
        String q1 = "delete from locks";
        db.execSQL(q1);
        String q = "insert into locks values('"+a+"','" +b+ "')";
        db.execSQL(q);
    }
    public String userlogin(String contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        // TODO Auto-generated method stub
        String u = contact;
        String p1 = null;
        String fetchdocreg = "Select * from  userreg where username='" + u + "'";
        System.out.println("THE QUERY IS " + fetchdocreg);
        Cursor mCursorById = db.rawQuery(fetchdocreg, null);
        // if (mCursorById != null) {
        if (mCursorById.moveToFirst()) // moveToFirst()....>Move the cursor to the first row.

        {
            p1 = mCursorById.getString(2);
            //Log.d("inside if", password);
        }

        mCursorById.close();
        System.out.println("password from db after if" + p1);
        return p1;

    }

    public String locklogin(String contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        // TODO Auto-generated method stub
        String u = contact;
        String p1 = null;
        String fetchdocreg = "Select * from  locks where password='" + u + "'";
        System.out.println("THE QUERY IS " + fetchdocreg);
        Cursor mCursorById = db.rawQuery(fetchdocreg, null);
        // if (mCursorById != null) {
        if (mCursorById.moveToFirst()) // moveToFirst()....>Move the cursor to the first row.

        {
            p1 = mCursorById.getString(1);
            //Log.d("inside if", password);
        }

        mCursorById.close();
        System.out.println("password from db after if" + p1);
        return p1;

    }

    public String forgetlogin(String contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        // TODO Auto-generated method stub
        String u = contact;
        String p1 = null;
        String fetchdocreg = "Select * from  locks ";
        System.out.println("THE QUERY IS " + fetchdocreg);
        Cursor mCursorById = db.rawQuery(fetchdocreg, null);
        // if (mCursorById != null) {
        if (mCursorById.moveToFirst()) // moveToFirst()....>Move the cursor to the first row.

        {
            p1 = mCursorById.getString(1);
            //Log.d("inside if", password);
        }

        mCursorById.close();
        System.out.println("password from db after if" + p1);
        return p1;

    }



    public String forgetlogin1(String contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        // TODO Auto-generated method stub
        String u = contact;
        String p1 = null;
        String fetchdocreg = "Select * from  userreg where username='"+contact+"' ";
        System.out.println("THE QUERY IS " + fetchdocreg);
        Cursor mCursorById = db.rawQuery(fetchdocreg, null);
        // if (mCursorById != null) {
        if (mCursorById.moveToFirst()) // moveToFirst()....>Move the cursor to the first row.

        {
            p1 = mCursorById.getString(1);
            //Log.d("inside if", password);
        }

        mCursorById.close();
        System.out.println("password from db after if" + p1);
        return p1;
    }

        @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);

    }



}

