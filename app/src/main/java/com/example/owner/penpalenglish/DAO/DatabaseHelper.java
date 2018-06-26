package com.example.owner.penpalenglish.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.owner.penpalenglish.Model.UserPhoto;
import com.example.owner.penpalenglish.Model.UserProfile;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "PenpalInnovations.db";
    private static final int DATABASE_VERSION = 3;

    private Dao<UserProfile, Integer> userDAO;
    private Dao<UserPhoto, Integer> userPhotoDAO;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {

            // Create tables. This onCreate() method will be invoked only once of the application life time i.e. the first time when the application starts.
            TableUtils.createTable(connectionSource, UserProfile.class);
            TableUtils.createTable(connectionSource, UserPhoto.class);


        } catch (java.sql.SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to create datbases", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

        try {

            // In case of change in database of next version of application, please increase the value of DATABASE_VERSION variable, then this method will be invoked
            //automatically. Developer needs to handle the upgrade logic here, i.e. create a new table or a new column to an existing table, take the backups of the
            // existing database etc.

            TableUtils.dropTable(connectionSource, UserProfile.class, true);
            TableUtils.dropTable(connectionSource, UserPhoto.class, true);
            onCreate(sqLiteDatabase, connectionSource);

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }

    // Create the getDao methods of all database tables to access those from android code.
    // Insert, delete, read, update everything will be happened through DAOs

    public Dao<UserProfile, Integer> getUserDAO() throws java.sql.SQLException {
        if (userDAO == null) {
            userDAO = getDao(UserProfile.class);
        }
        return userDAO;
    }

    public Dao<UserPhoto, Integer> getUserPhotoDAO() throws java.sql.SQLException {
        if (userPhotoDAO == null) {
            userPhotoDAO = getDao(UserPhoto.class);
        }
        return userPhotoDAO;
    }
}
