package com.example.owner.penpalenglish.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.owner.penpalenglish.Adapter.UserProfileAdapter;
import com.example.owner.penpalenglish.DAO.UserDAO;
import com.example.owner.penpalenglish.DAO.UserPhotoDAO;
import com.example.owner.penpalenglish.Model.UserPhoto;
import com.example.owner.penpalenglish.Model.UserProfile;
import com.example.owner.penpalenglish.R;
import com.example.owner.penpalenglish.Service.GetDataService;
import com.example.owner.penpalenglish.Service.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private UserProfileAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;
    private UserDAO userDAO;
    private UserPhotoDAO userPhotoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDAO = new UserDAO(this);
        userPhotoDAO = new UserPhotoDAO(this);
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

                UserProfile user = new UserProfile();
                user = (UserProfile) response.body();
                if(userDAO.searchUser().size() <= 0) {
                    if(user.getUserPhotos().size()>0)
                    {
                        userPhotoDAO.dbinsert((UserPhoto) user.getUserPhotos());
                    }
                    userDAO.dbinsert(user);

                }
                else
                {
                generateDataList(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<UserProfile>> call, Throwable t) {
                // progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<UserProfile> userList) {

       // userDAO.dbinsert((UserProfile) userList);

        recyclerView = findViewById(R.id.recyclerView);

        adapter = new UserProfileAdapter(this,userList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
