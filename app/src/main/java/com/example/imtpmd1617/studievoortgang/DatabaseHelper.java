package com.example.imtpmd1617.studievoortgang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.imtpmd1617.studievoortgang.Models.Module;
import com.example.imtpmd1617.studievoortgang.Models.Student;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static SQLiteDatabase mSQLDB;
    private static DatabaseHelper mInstance;
    private static final int DATABASEVERSION = 1;
    private static final String DATABASENAME = "imtpmd.db";
    private SQLiteDatabase db;

    public DatabaseHelper(Context ctx) {
        super(ctx, DATABASENAME, null, DATABASEVERSION);
    }


    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version ){
        super(context,name,factory, version);
    }

    // Singleton voor het verkijgen van de DatabaseHelper instance

    public static synchronized DatabaseHelper getHelper (Context ctx){
        if (mInstance == null){
            mInstance = new DatabaseHelper(ctx);
            mSQLDB = mInstance.getWritableDatabase();
        }
        return mInstance;
    }

    // Maakt de database één keer aan bij de eerste opstart van de applicatie

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        createTables();
    }

    // Het aanmaken van de table in SQLite met de daarbij behorende kolommen verkregen van de models

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

    // Drop table SQLlite voor het verwijderen van de doorgegeven tabel

    public void dropTable(String table){
        mSQLDB.execSQL("DROP TABLE IF EXISTS "+ table);
    }


    // Upgrade de SQLite database bij een update van de applicatie

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseInfo.Tables.MODULES + "," + DatabaseInfo.Tables.STUDENTEN);
        onCreate(db);
    }

    // Insert een row in de SQLite database

    public void insert(String table, String nullColumnHack, ContentValues values){
        mSQLDB.insert(table, nullColumnHack, values);
    }


    /* Query de lokale database met een gekregen query String.
       Maakt bij elke gevonden row een nieuwe Module aan met zijn waarde.
       Plaatst deze Module in een List en returned deze */

    public List<Module> querySqliteModules(String query) {
        List<Module> moduleList = new ArrayList<>();

        String selectQuery = query;

        Cursor cursor = mSQLDB.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Module module = new Module();
                module.setId(Integer.parseInt(cursor.getString(0)));
                if(cursor.getString(3) != "-1"){
                    module.setCijfer(Double.parseDouble(cursor.getString(3)));
                }
                module.setModule_naam(cursor.getString(1));
                module.setModule_afkorting(cursor.getString(2));
                module.setPeriode(Integer.parseInt(cursor.getString(4)));
                module.setEct(Integer.parseInt(cursor.getString(6)));
                moduleList.add(module);
                Log.d("Module: ", "" + cursor.getColumnName(1));
            } while (cursor.moveToNext());
        }

        return moduleList;
    }

    /* Query de lokale database met een gekregen query String.
       Maakt bij elke gevonden row een nieuwe Student aan met zijn waarde.
       Plaatst deze Student in een List en returned deze */

    public List<Student> querySqliteStudent(String query) {
        List<Student> studentList = new ArrayList<>();

        String selectQuery = query;

        Cursor cursor = mSQLDB.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setVoornaam(cursor.getString(1));
                student.setAchternaam(cursor.getString(2));
                student.setStudentId(Integer.parseInt(cursor.getString(0)));
                student.setStudierichting(cursor.getString(3));
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        return studentList;
    }

}