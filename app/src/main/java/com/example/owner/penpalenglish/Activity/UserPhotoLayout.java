package com.example.owner.penpalenglish.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.owner.penpalenglish.Adapter.CustomTabAdapter;
import com.example.owner.penpalenglish.Adapter.UserPhotoLayoutAdapter;
import com.example.owner.penpalenglish.Adapter.UserProfileAdapter;
import com.example.owner.penpalenglish.DAO.DatabaseHelper;
import com.example.owner.penpalenglish.R;

import java.util.List;

public class UserPhotoLayout extends AppCompatActivity {



    private UserPhotoLayoutAdapter adapter, adapter1;
    List<String> userNameList = null;

    private RecyclerView recyclerView;
    private CustomTabAdapter customTabAdapter;

    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userphotolayout);

        recyclerView = (RecyclerView)findViewById(R.id.userPhotoRecyclerView);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(UserPhotoLayout.this);
        adapter = new UserPhotoLayoutAdapter(UserPhotoLayout.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        setTitle("1:1 TUTOR LIST");



        View view1 = getLayoutInflater().inflate(R.layout.customtab, null);
        customTabAdapter = new CustomTabAdapter(tabLayout,this);
        customTabAdapter.setTabData(view1);








    }

    public void setTitle(String title){
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = new TextView(this);
        textView.setText(title);
        textView.setTextSize(20);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(textView);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.photView1:

                Intent switchView = new Intent(this, MainActivity.class);

                this.startActivity(switchView);


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
