package com.example.owner.penpalenglish.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaRouter;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.owner.penpalenglish.Activity.UserInfo;
import com.example.owner.penpalenglish.DAO.UserDAO;
import com.example.owner.penpalenglish.DAO.UserPhotoDAO;
import com.example.owner.penpalenglish.Model.UserPhoto;
import com.example.owner.penpalenglish.Model.UserProfile;
import com.example.owner.penpalenglish.R;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class UserProfileAdapter extends RecyclerView.Adapter<UserProfileAdapter.CustomViewHolder> {

    
    private List<UserProfile> dataList;

    private Context context;

    public UserProfileAdapter(Context context, List<UserProfile> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public void getFliter(List<UserProfile> listItme) {
        dataList = new ArrayList<>();
        dataList.addAll(listItme);
        notifyDataSetChanged();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtName, txtCountry, txtIntroduction;
        private ImageView userImage;

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
    public void onBindViewHolder(CustomViewHolder holder, final int position) {


        if ((dataList.get(position).getFirstName() != null) && (dataList.get(position).getFirstName() != null) ) {
            holder.txtName.setText(dataList.get(position).getFirstName() + " " + dataList.get(position).getLastName());
            holder.txtCountry.setText(dataList.get(position).getCountry());
            holder.txtIntroduction.setText(dataList.get(position).getIntroduction());

            Picasso.Builder builder = new Picasso.Builder(context);
            builder.downloader(new OkHttp3Downloader(context));

            if(dataList.get(position).getUserProfilePhoto().equals(""))
            {
                Picasso.with(context).load(R.drawable.person).into(holder.userImage);
            }
            else {

                Picasso.with(context).load(dataList.get(position).getUserProfilePhoto()).into(holder.userImage);
            }

        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send this user id to chat messages activity
                goToUserInfo(dataList.get(position).getUserID().toString());
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
        return dataList.size();
    }
}
