package com.nizar.abdelhedi.androidieeetraining.storage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nizar.abdelhedi.androidieeetraining.MainActivity;
import com.nizar.abdelhedi.androidieeetraining.TODO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abdelhedi on 23/09/2016.
 */


public class SQLite {

    SQLiteDatabase db;

    public SQLite(String dataBaseName, Context context) {
        db = context.openOrCreateDatabase(dataBaseName, context.MODE_PRIVATE, null);
    }

    public void createTable(String tableName) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + " ( NAME Text,TODO Text,DATE Text );");
    }


    public void insertData(TODO todo, String tableName) {
        db.execSQL("INSERT INTO " + tableName + " VALUES ('" + todo.getName() + "', '" + todo.getMemo() + "', '" + todo.getDate() + "' )");
    }

    public List<TODO> retrivenDate(String tableName) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName, null);
        List<TODO> tODOList = new ArrayList<>();
        cursor.moveToFirst();
        while (cursor.moveToNext())
        {
            int cName = cursor.getColumnIndex("NAME");
            int cTODO = cursor.getColumnIndex("TODO");
            int cDate = cursor.getColumnIndex("DATE");
            tODOList.add(new TODO(cursor.getString(cName)+"", cursor.getString(cTODO)+"", cursor.getString(cDate)+""));

        }
        return tODOList;
    }

    public void closeDateBase() {
        db.close();
    }


}
