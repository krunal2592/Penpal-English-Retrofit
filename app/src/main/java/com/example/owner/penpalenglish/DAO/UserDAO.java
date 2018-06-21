package com.example.owner.penpalenglish.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.owner.penpalenglish.Model.UserProfile;

import java.util.ArrayList;
import java.util.List;

public class UserDAO extends SQLiteOpenHelper {
    public String test;
    public UserDAO(Context context) {
        super(context, "Penpal English1", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        test = "onCreate USERDAO";

        String sql = "CREATE TABLE tbl_UserProfile (userID INTEGER NOT NULL ," +
                " birthYear TEXT , " +
                " sex TEXT ," +
                " firstName TEXT, " +
                " lastName TEXT ," +
                " userALias TEXT," +
                " isTeacher TEXT," +
                " presenceState TEXT ," +
                " country TEXT," +
                " longitude Double," +
                " latitude Double," +
                " school TEXT," +
                " hobby TEXT," +
                " introduction TEXT," +
                " timestamp Long ," +
                " unitPrice Float," +
                " photoPath TEXT);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS tbl_UserProfile";
        db.execSQL(sql);
    }

    public void dbinsert(UserProfile userProfile)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues userData = getUserContentValues(userProfile);

        db.insert("tbl_UserProfile", null, userData);

    }
    @NonNull
    private ContentValues getUserContentValues(UserProfile userProfile)
    {
        ContentValues userData = new ContentValues();
        userData.put("userID",userProfile.getUserID());
        userData.put("birthYear",userProfile.getBirthYear());
        userData.put("sex",userProfile.getSex());
        userData.put("firstName",userProfile.getFirstName());
        userData.put("lastName",userProfile.getLastName());
        userData.put("userALias",userProfile.getUserAlias());
        userData.put("isTeacher",userProfile.getIsTeacher());
        userData.put("presenceState",userProfile.getPresenceState());
        userData.put("country",userProfile.getCountry());
        userData.put("longitude",userProfile.getLongitude());
        userData.put("latitude",userProfile.getLatitude());
        userData.put("school",userProfile.getSchool());
        userData.put("hobby",userProfile.getHobby());
        userData.put("introduction",userProfile.getIntroduction());

        userData.put("timestamp", userProfile.getTimestamp());
        userData.put("unitPrice",userProfile.getUnitPrice());
        userData.put("photoPAth" , userProfile.getUserProfilePhoto());
        //userData.put("userProfilePhoto",userProfile.getUserProfilePhoto());

        return userData;
    }

    public List<UserProfile> searchUser()
    {
        String sql = "SELECT * FROM tbl_UserProfile;";
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(sql,null);
        Integer i = c.getCount();
        List<UserProfile> userList = new ArrayList<UserProfile>();
        c.moveToFirst();
        //c.getPosition();
        while(c.moveToNext())
        {
            UserProfile user = new UserProfile();

            user.setUserID(c.getInt(c.getColumnIndex("userID")));
            user.setBirthYear(c.getString(c.getColumnIndex("birthYear")));
            user.setSex(c.getString(c.getColumnIndex("sex")));
            user.setFirstName(c.getString(c.getColumnIndex("firstName")));
            user.setLastName(c.getString(c.getColumnIndex("lastName")));
            user.setUserAlias(c.getString(c.getColumnIndex("userALias")));
            user.setIsTeacher(c.getString(c.getColumnIndex("isTeacher")));
            user.setPresenceState(c.getString(c.getColumnIndex("presenceState")));
            user.setCountry(c.getString(c.getColumnIndex("country")));
            user.setLongitude(c.getDouble(c.getColumnIndex("longitude")));
            user.setLatitude(c.getDouble(c.getColumnIndex("latitude")));
            user.setSchool(c.getString(c.getColumnIndex("school")));
            user.setHobby(c.getString(c.getColumnIndex("hobby")));
            user.setIntroduction(c.getString(c.getColumnIndex("introduction")));
            user.setTimestamp(c.getLong(c.getColumnIndex("timestamp")));
            user.setTimestamp(c.getLong(c.getColumnIndex("unitPrice")));
            user.setUserProfilePhoto(c.getString(c.getColumnIndex("photoPath")));

            userList.add(user);

        }
        c.close();

        return userList;
    }

    public void dbAlter(UserProfile user)
    {
        SQLiteDatabase db = getReadableDatabase();
        String [] param = {user.getUserID().toString()};
        ContentValues userData = getUserContentValues(user);
        db.update("tbl_UserProfile",userData,"userID = ?",param);

    }

    public Cursor  getBuddyListByKeyword(String search) {
        //Open connection to read only
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM tbl_UserProfile WHERE firstName   LIKE  '%" +search + "%' "
                ;


        Cursor cursor = db.rawQuery(sql, null);
        // looping through all rows and adding to list

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;


    }

}
