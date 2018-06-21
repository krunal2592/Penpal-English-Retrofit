package com.example.owner.penpalenglish.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.owner.penpalenglish.Model.UserPhoto;

public class UserPhotoDAO extends SQLiteOpenHelper {
    public String test;
    public UserPhotoDAO(Context context) {
        super(context, "PenpalEnglish1", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        test = "onCreate USERPHOTODAO";
        String sql = "CREATE TABLE tbl_UserPhotos (photoId INTEGER NOT NULL ," +
                " userId INTEGER NOT NULL, " +
                " fileName TEXT NOT NULL," +
                " avatar TEXT NOT NULL );";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS tbl_UserPhotos";
        db.execSQL(sql);
    }
    public void dbinsert(UserPhoto userPhoto)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues userData = getUserContentValues(userPhoto);

        db.insert("tbl_UserPhotos", null, userData);

    }
    @NonNull
    private ContentValues getUserContentValues(UserPhoto userPhoto)
    {
        ContentValues userData = new ContentValues();

        userData.put("photoId",userPhoto.getPhotoId());
        userData.put("userId", String.valueOf(userPhoto.getUserId()));
        userData.put("fileName",userPhoto.getFileName());
        userData.put("avatar",userPhoto.getAvatar());



        return userData;
    }
}
