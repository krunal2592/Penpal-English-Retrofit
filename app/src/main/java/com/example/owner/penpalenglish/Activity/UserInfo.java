package com.example.owner.penpalenglish.Activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.owner.penpalenglish.Adapter.ViewPhotoAdapter;
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
    private String mUserID;
    private ImageView mUserPhoto,chat,book;
    private TextView mUserName, mUserHobby, mUserAbout;
    private RatingBar rating;
    ViewPager viewPager;
    ViewPhotoAdapter adapter;

    private ActionBar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo);




adapter = new ViewPhotoAdapter();


        mUserID = getIntent().getStringExtra("USER_ID");

        mUserName = (TextView) findViewById(R.id.name);
        mUserHobby = (TextView) findViewById(R.id.hobby1);
        mUserAbout = (TextView) findViewById(R.id.about1);
        mUserPhoto = (ImageView) findViewById(R.id.userProfilePic);
        chat = (ImageView) findViewById(R.id.chat);
        book = (ImageView) findViewById(R.id.book);
        rating = (RatingBar) findViewById(R.id.rating);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserInfo.this,"Chat Click",Toast.LENGTH_LONG).show();
            }
        });


        try {
            setUserData(mUserID);
            setmUserPhoto(mUserID);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void setTitle(String fname,String lname){
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = new TextView(this);
        textView.setText(fname +" " + lname);
        textView.setTextSize(20);
        textView.setTypeface(null, Typeface.NORMAL);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(textView);
    }



    public void setUserData(String mUserID) throws SQLException {


        List<UserProfile> list;
        list = adapter.getUserIDList(mUserID);

        for (int i = 0; i < list.size(); i++) {

            UserProfile user = list.get(i);
            mUserName.setText(user.getFirstName() + " " + user.getLastName());
            mUserHobby.setText(user.getHobby());
            mUserAbout.setText(user.getIntroduction());
            rating.setRating((float) 4.5);
//            mUserCountry.setText(list.get(i).getCountry());
//            mUserSchool.setText(list.get(i).getSchool());

            if (list.get(i).getUserProfilePhoto().equals("")) {
                Picasso.with(this).load(R.drawable.person).into(mUserPhoto);
            } else {
                Picasso.with(this).load(list.get(i).getUserProfilePhoto()).into(mUserPhoto);
            }

            setTitle(list.get(i).getFirstName(), list.get(i).getLastName());
        }
    }



    public void setmUserPhoto(String mUserID) throws SQLException {

        List<UserPhoto> photoList;
        photoList = adapter.getPhotoList(mUserID);

        ViewPhotoAdapter viewPagerAdapter = new ViewPhotoAdapter(this, photoList);

        viewPager.setAdapter(viewPagerAdapter);
    }
}
