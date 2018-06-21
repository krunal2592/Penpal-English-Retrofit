package com.example.owner.penpalenglish.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.owner.penpalenglish.DAO.UserDAO;
import com.example.owner.penpalenglish.DAO.UserPhotoDAO;
import com.example.owner.penpalenglish.Model.UserPhoto;
import com.example.owner.penpalenglish.Model.UserProfile;
import com.example.owner.penpalenglish.R;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;


public class UserProfileAdapter extends RecyclerView.Adapter<UserProfileAdapter.CustomViewHolder> {

    private UserDAO userDAO;
    private UserPhotoDAO userPhotoDAO;
    private List<UserProfile> dataList;

    private Context context;

    public UserProfileAdapter(Context context, List<UserProfile> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtName, txtCountry, txtIntroduction;
        private ImageView userImage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.name);
            txtCountry = mView.findViewById(R.id.country);
            txtIntroduction = mView.findViewById(R.id.introduction);
           userImage = mView.findViewById(R.id.profile_pic);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.buddylist, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        userDAO = new UserDAO(context);
        userPhotoDAO = new UserPhotoDAO(context);
        userDAO.dbinsert(dataList.get(position));
        userPhotoDAO.dbinsert((UserPhoto) dataList.get(position).getUserPhotos());

        if ((dataList.get(position).getFirstName() != null) && (dataList.get(position).getFirstName() != null) ) {
            holder.txtName.setText(dataList.get(position).getFirstName() + " " + dataList.get(position).getLastName());
            holder.txtCountry.setText(dataList.get(position).getCountry());
            holder.txtIntroduction.setText(dataList.get(position).getIntroduction());

            Picasso.Builder builder = new Picasso.Builder(context);
            builder.downloader(new OkHttp3Downloader(context));

            builder.build().load(String.valueOf(dataList.get(position).getUserPhotos()))
                    .placeholder((R.drawable.person))
                    .error(R.drawable.person)
                    .into(holder.userImage);
        }
    }



    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
