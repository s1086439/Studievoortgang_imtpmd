package com.example.imtpmd1617.studievoortgang;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static SQLiteDatabase mSQLDB;
    private static DatabaseHelper mInstance;
    private static final int DATABASEVERSION = 1;
    private static final String DATABASENAME = "imtpmd.db";
    private SQLiteDatabase db;

    public DatabaseHelper(Context ctx) {super(ctx, DATABASENAME, null, DATABASEVERSION);
    }


    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version ){
        super(context,name,factory, version);
    }

    public static synchronized DatabaseHelper getHelper (Context ctx){
        if (mInstance == null){
            mInstance = new DatabaseHelper(ctx);
            mSQLDB = mInstance.getWritableDatabase();
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        createTables();
    }

    public void createTables(){
        db.execSQL("CREATE TABLE " + DatabaseInfo.Tables.STUDENTEN + " (" +
                DatabaseInfo.StudentColumn.STUDENTID + " INTEGER PRIMARY KEY, " +
                DatabaseInfo.StudentColumn.VOORNAAM + " TEXT," +
                DatabaseInfo.StudentColumn.ACHTERNAAM + " TEXT," +
                DatabaseInfo.StudentColumn.STUDIERICHTING + " TEXT);"
        );
        db.execSQL("CREATE TABLE " + DatabaseInfo.Tables.MODULES + " (" +
                DatabaseInfo.ModulesColumn.ID + " INTEGER PRIMARY KEY, " +
                DatabaseInfo.ModulesColumn.NAAM + " TEXT," +
                DatabaseInfo.ModulesColumn.AFKORTING + " TEXT," +
                DatabaseInfo.ModulesColumn.CIJFER + " INT," +
                DatabaseInfo.ModulesColumn.PERIODE + " INT," +
                DatabaseInfo.ModulesColumn.FASE + " INT," +
                DatabaseInfo.ModulesColumn.ECT + " INT);"
        );
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseInfo.Tables.MODULES + "," + DatabaseInfo.Tables.STUDENTEN);
        onCreate(db);
    }

    public void insert(String table, String nullColumnHack, ContentValues values){
        mSQLDB.insert(table, nullColumnHack, values);
    }


    //public Cursor query(String table, String[] columns, String selection, String[] selectArgs, String groupBy, String having, String orderBy){
    //return mSQLDB.query(table, columns, selection, selectArgs, groupBy, having, orderBy);
    //}

    // Query table met een 2D ArrayList
    // Geeft de query als ArrayList met rows terug

    public ArrayList<ArrayList<String>> queryDb(String table, String[] columns, String selection, String[] selectArgs, String groupBy, String having, String orderBy){
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

        Cursor cursor = mSQLDB.query(table, columns, selection, selectArgs, groupBy, having, orderBy);
        //Cursor cursor = query(table, new String[]{"*"}, null, null, null, null, null);
        cursor.moveToFirst();

        if (cursor.moveToFirst()) {
            for(int rij = 0; rij < cursor.getCount(); rij++) {
                data.add(new ArrayList<String>());
                for(int col = 0; col < cursor.getColumnCount(); col++){
                    data.get(rij).add(cursor.getString(col));
                }
                cursor.moveToNext();
            }
            cursor.close();
        }
        return data;
    }

}