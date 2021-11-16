package com.example.applicationobjectssqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "spinnerAluno";
    private static final String TABLE_NAME = "Aluno";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_RA = "Ra";
    private static final String COLUMN_RG = "Rg";
    private static final String COLUMN_CURSO = "curso";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase database) {
        // Category table create query
        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_RG + " TEXT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_RA + " TEXT,"
                + COLUMN_CURSO + " TEXT" + ")";
        database.execSQL(CREATE_ITEM_TABLE);
    }

    public void insertLabel(Aluno aluno) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RG, aluno.getRG());//column name, column value
        values.put(COLUMN_NAME, aluno.getNome());//column name, column value
        values.put(COLUMN_EMAIL, aluno.getEmail());//column name, column value
        values.put(COLUMN_RA, aluno.getRA());//column name, column value
        values.put(COLUMN_CURSO, aluno.getCurso());//column name, column value
        // Inserting Row
        database.insert(TABLE_NAME, null, values);//tableName,nullColumnHack, CotentValues

        database.close(); // Closing database connection
    }

    public List<Aluno> getAllAluno() {
        List<Aluno> list = new ArrayList<Aluno>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery,
                null);//selectQuery,selectedArguments
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Aluno aluno = new Aluno(
                        cursor.getString(1),//RG
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5));
                list.add(aluno);
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        database.close();
        // returning lables
        return list;
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int newVersion) {
        // Drop older table if existed
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
        onCreate(database);
    }


}