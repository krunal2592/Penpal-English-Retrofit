package com.example.owner.penpalenglish.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.owner.penpalenglish.Model.UserProfile;
import com.example.owner.penpalenglish.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserProfileAdapter extends RecyclerView.Adapter<UserProfileAdapter.ViewHolder> {
    private List<UserProfile> mUserList;
    private Context mContext;

    public UserProfileAdapter(List<UserProfile> myDataset, Context context) {
        mUserList = myDataset;
        mContext = context;

    }

    @NonNull
    @Override
    public UserProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View view = inflater.inflate(R.layout.buddylist, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final UserProfile user = mUserList.get(position);
        holder.userName.setText(user.getFirstName() + " " + user.getLastName());

//        if(user.getUserProfilePhoto() != null)
//        {
//
//            //Picasso.with(mContext).load(user.getUserProfilePhoto()).placeholder(R.drawable.person).into(holder.userImage);
//            //Picasso.with(mContext).load(R.drawable.person).into(holder.userImage);
//            Glide.with(mContext)
//                    .load(user.getUserProfilePhoto())
//                    .into(holder.userImage);
//
//
//        }
//        else {
//
//        }

    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView userName;
        public ImageView userImage;


        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            userName = (TextView) v.findViewById(R.id.name);

            userImage = (ImageView) v.findViewById(R.id.image);
        }
    }
}
