package com.example.owner.penpalenglish.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.owner.penpalenglish.Activity.UserInfo;
import com.example.owner.penpalenglish.DAO.DatabaseHelper;
import com.example.owner.penpalenglish.Model.UserPhoto;
import com.example.owner.penpalenglish.Model.UserProfile;
import com.example.owner.penpalenglish.R;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.stmt.QueryBuilder;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserPhotoLayoutAdapter extends RecyclerView.Adapter<UserPhotoLayoutAdapter.CustomViewHolder> {

    private List<UserProfile> userNameList;
    private ArrayList<ArrayList<String>> userPhotoList;
    private DatabaseHelper databaseHelper = null;

    private Context context;

    public UserPhotoLayoutAdapter(Context context){
        this.context = context;
        userNameList = getUserNameData();
        userPhotoList = getUserPhotoData();

    }

    // This is how, DatabaseHelper can be initialized for future use
    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.userphotoview, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {


        holder.btnName.setText(userNameList.get(position).getFirstName() + " " + userNameList.get(position).getLastName()+ "        >");

                ArrayList<String> photoList = userPhotoList.get(position);



                if(photoList != null) {


                    ImageGridAdapter adapter = new ImageGridAdapter(context,photoList);
                    holder.grd.setAdapter(adapter);

                }

        holder.btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send this user id to chat messages activity
                goToUserInfo(String.valueOf(userNameList.get(position).getUserID()));

            }
        });

    }

    private void goToUserInfo(String personId) {
        Intent goToUserInfo = new Intent(context, UserInfo.class);
        goToUserInfo.putExtra("USER_ID", personId);
        context.startActivity(goToUserInfo);
    }

    @Override
    public int getItemCount() {
        return userNameList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        Button btnName;
        GridView grd;
        private ImageView userImage;

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            btnName = mView.findViewById(R.id.name);
            grd = mView.findViewById(R.id.grid_view);

        }
    }

    public List<UserProfile> getUserNameData()
    {
        List<UserProfile> userList = null;
        try {
            final Dao<UserProfile, Integer> userDAO = getHelper().getUserDAO();
            QueryBuilder<UserProfile, Integer> queryBuilder = userDAO.queryBuilder();

            userList =  userDAO.queryForAll();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public ArrayList<ArrayList<String>> getUserPhotoData()
    {
       List<String> userIdList = null;
        ArrayList<ArrayList<String>> userPhoto = new ArrayList<ArrayList<String>>();
       List<ArrayList<List<String>>> userPhotoList = null;

        try {
            final Dao<UserProfile, Integer> userDAO = getHelper().getUserDAO();
            final Dao<UserPhoto, Integer> userPhotoDAO = getHelper().getUserPhotoDAO();

            userIdList = userDAO.queryRaw("select userID from userprofile", new RawRowMapper<String>() {

                @Override
                public String mapRow(String[] columnNames, String[] resultColumns) throws SQLException {


                    return resultColumns[0];
                }
            }).getResults();


            for(int i = 0; i<userIdList.size(); i++)
            {

                List<String> path = null;

                path = userPhotoDAO.queryRaw("select photoPath from userPhoto where userId = "+userIdList.get(i) + " and fileName != 'null'", new RawRowMapper<String>() {

                        @Override
                        public String mapRow(String[] columnNames, String[] resultColumns) throws SQLException {


                            return resultColumns[0];
                        }
                    }).getResults();
                if(path.size()>0) {
                    userPhoto.add((ArrayList<String>) path);
                }
                else
                {
                    userPhoto.add(null);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userPhoto;

    }
}
