package itokcenter.com.sqliteandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Gorro on 04/08/16.
 */
public class DBController {

    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public DBController(Context context) {
        this.context = context;
    }

    public DBController open() {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insertData(String name, String address, int age) {
        ContentValues values = new ContentValues();
        values.put("nombre", name);
        values.put("edad", age);
        values.put("domicilio", address);
        db.insert(DBHelper.TABLE_NAME, null, values);
    }

    public Cursor getData() {
        String[] columnas = new String[]{
                DBHelper._ID, "nombre", "edad", "domicilio"
        };
        Cursor cursor = db.query(
                DBHelper.TABLE_NAME,
                columnas, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void delete(long id) {
        db.delete(DBHelper.TABLE_NAME, DBHelper._ID + "=" + id, null);
    }

}
