package com.example.owner.penpalenglish.Activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
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
import java.util.ArrayList;
import java.util.HashSet;
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
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Create handle for the RetrofitInstance interface*/

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<List<UserProfile>> call = service.getAllUser();
        call.enqueue(new Callback<List<UserProfile>>() {

            @Override
            public void onResponse(Call<List<UserProfile>> call, Response<List<UserProfile>> response) {

                try {

                    final Dao<UserProfile, Integer> userDAO = getHelper().getUserDAO();
                    int count = userDAO.queryForAll().size();
                    if(userDAO.queryForAll().size()<1 ) {
                        InsertData(response.body());
                    }






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

        //populate the View
        populateView();


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
    private void populateView()  {

        final Dao<UserProfile, Integer> userDAO;
        try {
            userDAO = getHelper().getUserDAO();

            recyclerView = findViewById(R.id.recyclerView);
            adapter = new UserProfileAdapter(this, userDAO.queryForAll());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Method for Inserting Data into a Table

    private void InsertData(List<UserProfile> userList)
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


    @Override    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.search, menu);


        final MenuItem myActionMenuItem = menu.findItem(R.id.search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        changeSearchViewTextColor(searchView);
        ((EditText) searchView.findViewById(
                android.support.v7.appcompat.R.id.search_src_text)).
                setHintTextColor(getResources().getColor(R.color.white));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    final Dao<UserProfile, Integer> userDAO = getHelper().getUserDAO();
                    final List<UserProfile> filtermodelist=filter(userDAO.queryForAll(),newText);
                   // Log.d(TAG, "onQueryTextChange: "+ filtermodelist);
                    adapter.getFliter(filtermodelist);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });

        return true;
    }
    private List<UserProfile> filter(List<UserProfile> pl,String query)
    {
        query=query.toLowerCase();
        final List<UserProfile> filteredModeList=new ArrayList<>();
        for (UserProfile user:pl)
        {
            if(user.getFirstName()!= null)
            {
            final String name=user.getFirstName().toLowerCase();

                if (name.startsWith(query) == true) {
                    filteredModeList.add(user);
                }
            }
        }
        return filteredModeList;
    }


    private void changeSearchViewTextColor(View view) {
        if (view != null) {
            if (view instanceof TextView) {
                (( TextView ) view).setTextColor(Color.WHITE);
                return;
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = ( ViewGroup ) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    changeSearchViewTextColor(viewGroup.getChildAt(i));
                }
            }
        }


    }


}
