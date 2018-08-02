package com.example.owner.penpalenglish.Adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.owner.penpalenglish.DAO.DatabaseHelper;
import com.example.owner.penpalenglish.Model.UserPhoto;
import com.example.owner.penpalenglish.R;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FullScreenImageAdapter extends PagerAdapter {

	Activity activity;
	private Context context;
	private List<UserPhoto> userPhotoList;
	private DatabaseHelper databaseHelper = null;
	private int userID;
	private int pos;
	// constructor
	public FullScreenImageAdapter(Activity activity, int userId, int position) {
		this.activity = activity;
		this.userID = userId;
		this.pos = position;
		userPhotoList = getPhotoList();
	}

	@Override
	public int getCount() {

		return userPhotoList.size();

	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageView imgDisplay;
		Button btnClose;

		LayoutInflater inflater = (LayoutInflater) activity
				.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
		View viewLayout = inflater.inflate(R.layout.layout_fullscreen_image, container,
				false);


		imgDisplay = viewLayout.findViewById(R.id.imgDisplay);





		Picasso.with(activity).load(userPhotoList.get(position).getPhotopath()).into(imgDisplay);

		btnClose = viewLayout.findViewById(R.id.btnClose);


		// close button click event
		btnClose.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				activity.finish();
			}
		});

		container.addView(viewLayout);

		return viewLayout;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((RelativeLayout) object);

	}

	private DatabaseHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
		}
		return databaseHelper;
	}


	public List<UserPhoto> getPhotoList()
	{

		try {
			final Dao<UserPhoto, Integer> userPhotoDAO = getHelper().getUserPhotoDAO();


			QueryBuilder<UserPhoto, Integer> queryBuilder = userPhotoDAO.queryBuilder();
			queryBuilder.where().eq("userId", userID);
			userPhotoList = queryBuilder.query();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userPhotoList;
	}
}