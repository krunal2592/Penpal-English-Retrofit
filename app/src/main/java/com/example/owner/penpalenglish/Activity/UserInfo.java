package com.example.owner.penpalenglish.Activity;

import android.accounts.Account;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.owner.penpalenglish.DAO.DatabaseHelper;
import com.example.owner.penpalenglish.Model.UserPhoto;
import com.example.owner.penpalenglish.Model.UserProfile;
import com.example.owner.penpalenglish.R;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.squareup.picasso.Picasso;

import java.sql.SQLException;
import java.util.List;

public class UserInfo extends AppCompatActivity {

    private DatabaseHelper databaseHelper = null;
    UserProfile user;
    private String mUserID;
    private ImageView mUserPhoto;
    private TextView mUserName, mUserCountry, mUserSchool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo);

        mUserID = getIntent().getStringExtra("USER_ID");

        mUserName = (TextView) findViewById(R.id.name);
        mUserCountry = (TextView) findViewById(R.id.country);
        mUserSchool = (TextView) findViewById(R.id.school);
        mUserPhoto = (ImageView) findViewById(R.id.userProfilePic);


        try {

            final Dao<UserProfile, Integer> userDAO = getHelper().getUserDAO();
            final Dao<UserPhoto, Integer> userPhotoDAO = getHelper().getUserPhotoDAO();


           user = userDAO.queryForId(Integer.valueOf(mUserID));

//           user = (UserProfile) userDAO.queryBuilder()
//                    .where()
//                    .eq(String.valueOf(user.getUserID()), mUserID)
//                    .query();

            mUserName.setText(user.getFirstName() + " " + user.getLastName());
            mUserCountry.setText(user.getCountry());
            mUserSchool.setText(user.getSchool());
            Picasso.with(this).load(user.getUserProfilePhoto()).into(mUserPhoto);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        List<UserPhoto> userPhotos;

    }
        private DatabaseHelper getHelper() {
            if (databaseHelper == null) {
                databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
            }
            return databaseHelper;
        }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        /*
         * You'll need this in your class to release the helper when done.
         */
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
}
