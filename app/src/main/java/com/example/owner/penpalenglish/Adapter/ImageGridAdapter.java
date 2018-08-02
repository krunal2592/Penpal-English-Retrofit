package com.example.owner.penpalenglish.Adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.owner.penpalenglish.R;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageGridAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> userPhotoList;

    private static LayoutInflater inflater=null;

    public ImageGridAdapter(Context c, ArrayList<String> userPhotoList) {
        mContext = c;
        this.userPhotoList = userPhotoList;

    }
    @Override
    public int getCount() {

    return userPhotoList.size();
    }

    @Override
    public Object getItem(int position) {
        return userPhotoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {


        ImageView imageview;


        if(convertView == null)
        {

            imageview = new ImageView(mContext);
            imageview.requestLayout();

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(450, 450);
            imageview.setLayoutParams(layoutParams);
            imageview.setScaleType(ImageView.ScaleType.FIT_XY);

        }
        else
        {
            imageview = (ImageView) convertView;
        }



       //imageview.setImageResource(mThumbIds[pos]);
        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.downloader(new OkHttp3Downloader(mContext));

        Picasso.with(mContext).load(userPhotoList.get(pos)).into(imageview);




       return imageview;

    }




}
