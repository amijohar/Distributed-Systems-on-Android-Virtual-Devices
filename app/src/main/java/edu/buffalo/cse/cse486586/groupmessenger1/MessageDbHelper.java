package edu.buffalo.cse.cse486586.groupmessenger1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MessageDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "message.db";
    public static final String TABLE_NAME = "entries";
    public static final String key = "key";
    public static final String value = "value";

    public MessageDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(key varchar,value varchar)" );
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(ContentValues values){

        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(TABLE_NAME,null,values);
        if ( result == -1){
            return false;
        }
        else
            return true;

    }

    public Cursor getData(String string, String[] selectionArgs){

        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("select distinct * from entries where key='"+string+"'",selectionArgs);

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}




