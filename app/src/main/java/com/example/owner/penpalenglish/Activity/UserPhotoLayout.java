package com.example.owner.penpalenglish.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;

import com.example.owner.penpalenglish.Adapter.UserPhotoLayoutAdapter;
import com.example.owner.penpalenglish.Adapter.UserProfileAdapter;
import com.example.owner.penpalenglish.DAO.DatabaseHelper;
import com.example.owner.penpalenglish.R;

import java.util.List;

public class UserPhotoLayout extends AppCompatActivity {



    private UserPhotoLayoutAdapter adapter, adapter1;
    List<String> userNameList = null;

    private RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userphotolayout);

        recyclerView = (RecyclerView)findViewById(R.id.userPhotoRecyclerView);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(UserPhotoLayout.this);
        adapter = new UserPhotoLayoutAdapter(UserPhotoLayout.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }
}
