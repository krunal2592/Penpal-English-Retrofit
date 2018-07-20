package com.example.owner.penpalenglish.Adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.owner.penpalenglish.DAO.DatabaseHelper;
import com.example.owner.penpalenglish.Model.UserPhoto;
import com.example.owner.penpalenglish.Model.UserProfile;
import com.example.owner.penpalenglish.R;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RawRowMapper;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserPhotoLayoutAdapter extends RecyclerView.Adapter<UserPhotoLayoutAdapter.CustomViewHolder> {

    private List<String> userNameList;
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
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        holder.btnName.setText(userNameList.get(position));
//        for(int i=0;i<userPhotoList.size();i++)
//        {
//          for(int j = 0;j<userPhotoList.get(position).size();i++)
//            if(getUserPhotoData().get(j).size()>0)
//            {
//                Picasso.with(context).load(String.valueOf(userPhotoList.get(j))).into(holder.userImage);
//            }
//            else
//            {
//                Picasso.with(context).load(R.drawable.person).into(holder.userImage);
//            }
//        }

    }

    @Override
    public int getItemCount() {
        return userNameList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        Button btnName;
        private ImageView userImage;

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            btnName = mView.findViewById(R.id.name);

            userImage = mView.findViewById(R.id.pic);
            //userImage.setClipToOutline(true);
        }
    }

    public List<String> getUserNameData()
    {
        List<String> userNameList = null;
        try {
            final Dao<UserProfile, Integer> userDAO = getHelper().getUserDAO();

            userNameList = userDAO.queryRaw("select firstName,lastName from userprofile", new RawRowMapper<String>() {

                @Override
                public String mapRow(String[] columnNames, String[] resultColumns) throws SQLException {

                    if(resultColumns[0] != null && resultColumns[1]!= null) {
                        return resultColumns[0]+" "+ resultColumns[1];
                    }
                    else
                    {
                        resultColumns[0] = "";
                        resultColumns[1] = "";
                    }
                   return resultColumns[0]+ " "+ resultColumns[1];
                }
            }).getResults();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userNameList;
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

                path = userPhotoDAO.queryRaw("select photoPath from userPhoto where userId = "+userIdList.get(i), new RawRowMapper<String>() {

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
