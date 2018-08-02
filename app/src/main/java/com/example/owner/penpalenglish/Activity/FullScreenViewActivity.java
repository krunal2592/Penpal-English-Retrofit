package com.example.owner.penpalenglish.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.owner.penpalenglish.Adapter.FullScreenImageAdapter;
import com.example.owner.penpalenglish.R;


public class FullScreenViewActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fullscreen_view);

		ViewPager viewPager = findViewById(R.id.pager);


		Intent i = getIntent();
		int position = i.getIntExtra("position", 0);
		int userId = i.getIntExtra("userID",0);

		FullScreenImageAdapter adapter = new FullScreenImageAdapter(FullScreenViewActivity.this,userId,position);


		viewPager.setAdapter(adapter);

		// displaying selected image first
		viewPager.setCurrentItem(position);
	}
}
