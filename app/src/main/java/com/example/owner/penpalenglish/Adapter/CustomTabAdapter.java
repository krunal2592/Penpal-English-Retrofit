package com.example.owner.penpalenglish.Adapter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.owner.penpalenglish.Activity.UserPhotoLayout;
import com.example.owner.penpalenglish.R;

public class CustomTabAdapter
{
    TabLayout tabLayout;
    Context context;

    public CustomTabAdapter(TabLayout tab, Context context) {
        this.tabLayout = tab;
        this.context = context;
    }


    public void setTabData(View view1) {

        RelativeLayout relHome1 = (RelativeLayout)view1.findViewById(R.id.relhome);
        RelativeLayout relChitChat = (RelativeLayout)view1.findViewById(R.id.relChitchat);
        RelativeLayout relBuddies = (RelativeLayout)view1.findViewById(R.id.relBuddies);
        RelativeLayout relChat = (RelativeLayout)view1.findViewById(R.id.relChat);
        RelativeLayout relQA = (RelativeLayout)view1.findViewById(R.id.relQA);

        tabLayout.addTab(tabLayout.newTab().setCustomView(relHome1));
        tabLayout.addTab(tabLayout.newTab().setCustomView(relChitChat));
        tabLayout.addTab(tabLayout.newTab().setCustomView(relBuddies));
        tabLayout.addTab(tabLayout.newTab().setCustomView(relChat));
        tabLayout.addTab(tabLayout.newTab().setCustomView(relQA));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition())
                {
                    case 0:
                        Toast.makeText(context,"Home Tab Clicked",Toast.LENGTH_LONG).show();
                        break;

                    case 1:
                        Toast.makeText(context,"Chit chat Tab Clicked",Toast.LENGTH_LONG).show();
                        break;

                    case 2:
                        Toast.makeText(context,"Buddies Tab Clicked",Toast.LENGTH_LONG).show();
                        break;

                    case 3:
                        Toast.makeText(context,"Chat Tab Clicked",Toast.LENGTH_LONG).show();
                        break;

                    case 4:
                        Toast.makeText(context,"Q&A Tab Clicked",Toast.LENGTH_LONG).show();
                        break;
                }
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
