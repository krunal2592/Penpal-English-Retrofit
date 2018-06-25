package com.example.owner.penpalenglish.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.owner.penpalenglish.Adapter.UserProfileAdapter;
import com.example.owner.penpalenglish.DAO.DatabaseHelper;
import com.example.owner.penpalenglish.DAO.UserDAO;
import com.example.owner.penpalenglish.DAO.UserPhotoDAO;
import com.example.owner.penpalenglish.Model.UserPhoto;
import com.example.owner.penpalenglish.Model.UserProfile;
import com.example.owner.penpalenglish.R;
import com.example.owner.penpalenglish.Service.GetDataService;
import com.example.owner.penpalenglish.Service.RetrofitClientInstance;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper = null;

    private UserProfileAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;
    private UserDAO userDAO;
    private UserPhotoDAO userPhotoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        userDAO = new UserDAO(this);
//        userPhotoDAO = new UserPhotoDAO(this);
//        progressDoalog = new ProgressDialog(MainActivity.this);
//        progressDoalog.setMessage("Loading....");
//        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<List<UserProfile>> call = service.getAllUser();
        call.enqueue(new Callback<List<UserProfile>>() {

            @Override
            public void onResponse(Call<List<UserProfile>> call, Response<List<UserProfile>> response) {
                // progressDoalog.dismiss();


                try {

                    generateDataList(response.body());

                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<List<UserProfile>> call, Throwable t) {
                // progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // This is how, DatabaseHelper can be initialized for future use
    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this,DatabaseHelper.class);
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

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<UserProfile> userList) throws SQLException {

        final Dao<UserProfile, Integer> userDAO = getHelper().getUserDAO();
        if(userDAO.queryForAll().size()<0) {
            InsertData(userList);
        }

        else {

            recyclerView = findViewById(R.id.recyclerView);
            adapter = new UserProfileAdapter(this, userDAO.queryForAll());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }

    // Method for Inserting Data into a Table

    private void InsertData(List<UserProfile> userList)
    {
        try {
            final Dao<UserProfile, Integer> userDAO = getHelper().getUserDAO();
            final Dao<UserPhoto, Integer> userPhotoDAO = getHelper().getUserPhotoDAO();

            UserProfile user = new UserProfile();
            UserPhoto userPhoto = new UserPhoto();
            for(int i=0;i<=userList.size();i++) {
                user.setUserID(userList.get(i).getUserID());
                user.setFirstName(userList.get(i).getFirstName());
                user.setLastName(userList.get(i).getLastName());
                user.setBirthYear(userList.get(i).getBirthYear());
                user.setCountry(userList.get(i).getCountry());
                user.setCredit(userList.get(i).getCredit());
                user.setIsTeacher(userList.get(i).getIsTeacher());
                user.setUserAlias(userList.get(i).getUserAlias());
                user.setUnitPrice(userList.get(i).getUnitPrice());
                user.setHobby(userList.get(i).getHobby());
                user.setIntroduction(userList.get(i).getIntroduction());
                user.setLatitude(userList.get(i).getLatitude());
                user.setLongitude(userList.get(i).getLongitude());
                user.setPresenceState(userList.get(i).getPresenceState());
                user.setTimestamp(userList.get(i).getTimestamp());
                user.setUserPhotos(userList.get(i).getUserPhotos());

                int count = userList.get(i).getUserPhotos().size();

                if(userList.get(i).getUserPhotos().size()>0)
                {
                    for(int j =0; j<userList.get(i).getUserPhotos().size(); j++)
                    {
                    }
                }
               // userDAO.create(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
