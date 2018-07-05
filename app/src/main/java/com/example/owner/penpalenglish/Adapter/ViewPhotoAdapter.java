package com.example.owner.penpalenglish.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.owner.penpalenglish.Model.UserPhoto;
import com.example.owner.penpalenglish.Model.UserProfile;
import com.example.owner.penpalenglish.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewPhotoAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<UserPhoto> photoList;
    private Integer [] images = {R.drawable.person,R.drawable.ic_launcher_background,R.drawable.ic_launcher_foreground};

    public ViewPhotoAdapter(Context context, List<UserPhoto> photoList) {
        this.context = context;
        this.photoList = photoList;
    }

    @Override
    public int getCount() {
        return photoList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.imageslider, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
       // imageView.setImageResource(images[position]);
        Picasso.with(context).load(photoList.get(position).getPhotopath()).into(imageView);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}



