package com.example.imtpmd1617.studievoortgang;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

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
        db.execSQL("CREATE TABLE " + DatabaseInfo.Tables.MODULES + " (" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseInfo.ModulesColumn.NAAM + " TEXT," +
                DatabaseInfo.ModulesColumn.ECT + " INT);"
        );
        db.execSQL("CREATE TABLE " + DatabaseInfo.Tables.STUDENTEN + " (" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseInfo.StudentColumn.STUDENTNUMMER + " INT," +
                DatabaseInfo.StudentColumn.VOORNAAM + " TEXT," +
                DatabaseInfo.StudentColumn.ACHTERNAAM + " TEXT);"
        );
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseInfo.Tables.MODULES + "," + DatabaseInfo.Tables.STUDENTEN);
        onCreate(db);
    }

    public void insert(String table, String nullColumnHack, ContentValues values){
        mSQLDB.insert(table, nullColumnHack, values);
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectArgs, String groupBy, String having, String orderBy){
        return mSQLDB.query(table, columns, selection, selectArgs, groupBy, having, orderBy);
    }

}