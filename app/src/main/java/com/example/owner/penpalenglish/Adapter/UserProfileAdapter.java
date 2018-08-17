package com.example.owner.penpalenglish.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.owner.penpalenglish.Activity.UserInfo;
import com.example.owner.penpalenglish.DAO.DatabaseHelper;
import com.example.owner.penpalenglish.Model.UserProfile;
import com.example.owner.penpalenglish.R;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.stmt.QueryBuilder;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserProfileAdapter extends RecyclerView.Adapter<UserProfileAdapter.CustomViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private List<String> dataList;
    private List<UserProfile> searchList;
    private DatabaseHelper databaseHelper = null;



    private Context context;




    public UserProfileAdapter(){}

    public UserProfileAdapter(Context context, List<String> dataList) {

        this.dataList = setAdapterData();
        this.context = context;
    }



    // This is how, DatabaseHelper can be initialized for future use
    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
        }
        return databaseHelper;
    }


    public List<String> setAdapterData()
    {
        List<String> userIDList = null;
        try {
        final Dao<UserProfile, Integer> userDAO = getHelper().getUserDAO();


            userIDList = userDAO.queryRaw("select userID from userprofile", new RawRowMapper<String>() {
                @Override
                public String mapRow(String[] columnNames, String[] resultColumns) throws SQLException {
                    return resultColumns[0];
                }
            }).getResults();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userIDList;
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtName, txtIntroduction;
        private ImageView userImage,txtCountry;
        RecyclerView recyclerView;

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.name);
            txtCountry = mView.findViewById(R.id.country);
            txtIntroduction = mView.findViewById(R.id.introduction);
           userImage = mView.findViewById(R.id.profile_pic);

            //userImage.setClipToOutline(true);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.buddylist, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {

        try {
            final Dao<UserProfile, Integer> userDAO = getHelper().getUserDAO();
            List<UserProfile> list;

            int uid = Integer.parseInt(dataList.get(position));
           // UserProfile user  =  userDAO.queryForId(uid);
            QueryBuilder<UserProfile, Integer> queryBuilder = userDAO.queryBuilder();
            queryBuilder.where().eq("userID",uid);
            list =  queryBuilder.query();




            if(list.size() == 1)
                {

                    UserProfile user = list.get(0);
                     if ((user.getFirstName() != null ) && (user.getLastName() != null )) {
                             holder.txtName.setText(user.getFirstName() + " " + user.getLastName());

                             if(user.getCountry() != null) {
                                 if (user.getCountry().equals("Canada")) {
                                     holder.txtCountry.setImageResource(R.drawable.flag_canada);
                                 }
                                 if (user.getCountry().equals("USA")) {
                                     holder.txtCountry.setImageResource(R.drawable.flag_america);
                                 }

                                 if (user.getCountry().equals("UK")) {
                                     holder.txtCountry.setImageResource(R.drawable.flag_uk);
                                 }

                                 if (user.getCountry().equals("China")) {
                                     holder.txtCountry.setImageResource(R.drawable.flag_china);
                                 }

                                 if (user.getCountry().equals("Australia")) {
                                     holder.txtCountry.setImageResource(R.drawable.flag_australia);
                                 }
                             }
                             holder.txtIntroduction.setText(user.getIntroduction());

                             Picasso.Builder builder = new Picasso.Builder(context);
                             builder.downloader(new OkHttp3Downloader(context));

                             if (user.getUserProfilePhoto().equals("")) {
                                     Picasso.with(context).load(R.drawable.person).into(holder.userImage);
                             } else {

                                        Picasso.with(context).load(user.getUserProfilePhoto()).into(holder.userImage);
                                    }

                     }


                 }
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // send this user id to chat messages activity
                    goToUserInfo(dataList.get(position));

                }
            });




        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void goToUserInfo(String personId) {
        Intent goToUserInfo = new Intent(context, UserInfo.class);
        goToUserInfo.putExtra("USER_ID", personId);
        context.startActivity(goToUserInfo);
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }



    public void setSearchList(String searchText)
    {
        String result = "";
        try {

            final Dao<UserProfile, Integer> userDAO = getHelper().getUserDAO();

            List<String> userIDList = userDAO.queryRaw("select userID from userprofile where lastName like '" +searchText+"%'", new RawRowMapper<String>() {
                @Override
                public String mapRow(String[] columnNames, String[] resultColumns) throws SQLException {
                    return resultColumns[0];
                }
            }).getResults();


            refreshUserData(userIDList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void refreshUserData(List<String> listItme) {

        dataList.clear();
        dataList.addAll(listItme);
        notifyDataSetChanged();


    }



}
