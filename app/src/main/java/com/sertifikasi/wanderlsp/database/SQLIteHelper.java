package com.sertifikasi.wanderlsp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.sertifikasi.wanderlsp.model.ModelDaftar;

public class SQLIteHelper extends SQLiteOpenHelper {
    Context context;

    public SQLIteHelper(@Nullable Context context) {
        super(context, "dblsp", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE PENGGUNA(ID Integer PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PASSWORD TEXT, EMAIL TEXT, NAME TEXT)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean InsertContact(ModelDaftar md) {
        SQLiteDatabase dbread = this.getReadableDatabase();
        Cursor ceknama = dbread.rawQuery("select * from PENGGUNA where USERNAME='" + md.getUsername()+"'", null);

        if (ceknama.getCount()>0){
            Toast.makeText(context, "Username sudah terdaftar", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("NAME", md.getNama());
            contentValues.put("EMAIL", md.getEmail());
            contentValues.put("USERNAME", md.getUsername());
            contentValues.put("PASSWORD", md.getPassword());
            db.insert("PENGGUNA", null, contentValues);
            Toast.makeText(context, "Anda berhasil daftar", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    public Cursor getSingleUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from PENGGUNA where USERNAME='" + username + "'and PASSWORD='" + password + "'", null);
        return res;
    }
}

