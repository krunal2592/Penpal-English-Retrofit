package com.example.owner.penpalenglish.Service;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.owner.penpalenglish.Activity.MainActivity;
import com.example.owner.penpalenglish.Adapter.UserProfileAdapter;
import com.example.owner.penpalenglish.DAO.DatabaseHelper;
import com.example.owner.penpalenglish.Model.UserPhoto;
import com.example.owner.penpalenglish.Model.UserProfile;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataService {

    private DatabaseHelper databaseHelper = null;

    private UserProfileAdapter adapter;
    private Context context;
    Boolean data = true;

    //Default Constructor
   public DataService(){

   }

    public Boolean GetDataFromServer()
    {

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<List<UserProfile>> call = service.getAllUser();
        call.enqueue(new Callback<List<UserProfile>>() {

            @Override
            public void onResponse(Call<List<UserProfile>> call, Response<List<UserProfile>> response) {
                final Dao<UserProfile, Integer> userDAO;
                try {
                    userDAO = getHelper().getUserDAO();
                    int count = userDAO.queryForAll().size();
                    if(count <1 ) {

                        InsertData(response.body());

                        }
                    data = true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<List<UserProfile>> call, Throwable t) {
                // progressDoalog.dismiss();

                //Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                data = false;
            }
        });
        return data;
    }

    public void InsertData(List<UserProfile> userList)
    {
        try {
            final Dao<UserProfile, Integer> userDAO = getHelper().getUserDAO();
            final Dao<UserPhoto, Integer> userPhotoDAO = getHelper().getUserPhotoDAO();

            List<UserPhoto> userPhotos;

            for(int i=0;i<=userList.size();i++) {

                UserProfile userProfile = userList.get(i);

                userPhotos = (List<UserPhoto>) userList.get(i).getUserPhotos();

                if(userList.get(i).getUserPhotos().size()>0)
                {
                    for(int j =0; j<userPhotos.size(); j++)
                    {
                        UserPhoto userPhoto = userPhotos.get(j);

                        if(userPhoto.getAvatar() == 1) {
                            userProfile.setUserProfilePhoto("http://54.148.5.0:8080/giaserver/" + userPhoto.getUserId() + "/profile/media/" + userPhoto.getPhotoId());
                        }

                        userPhoto.setPhotopath("http://54.148.5.0:8080/giaserver/" + userPhoto.getUserId() + "/profile/media/" + userPhoto.getPhotoId());
                        userPhotoDAO.create(userPhoto);
                    }
                }
                else
                {
                    userProfile.setUserProfilePhoto("");
                }

                userDAO.create(userProfile);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
        }
        return databaseHelper;
    }


//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//        /*
//         * You'll need this in your class to release the helper when done.
//         */
//        if (databaseHelper != null) {
//            OpenHelperManager.releaseHelper();
//            databaseHelper = null;
//        }
//    }
}
