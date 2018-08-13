package com.example.owner.penpalenglish.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.renderscript.ScriptGroup;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.owner.penpalenglish.Adapter.CustomTabAdapter;
import com.example.owner.penpalenglish.Adapter.UserProfileAdapter;
import com.example.owner.penpalenglish.DAO.DatabaseHelper;
import com.example.owner.penpalenglish.Model.UserProfile;
import com.example.owner.penpalenglish.R;
import com.example.owner.penpalenglish.Service.DataService;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import static android.graphics.drawable.ClipDrawable.HORIZONTAL;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper = null;

    private UserProfileAdapter adapter, adapter1;

    private RecyclerView recyclerView;
    private CustomTabAdapter customTabAdapter;

    Button filter;
    EditText search;




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filter = (Button) findViewById(R.id.filter);
        filter.setClipToOutline(true);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);

        search = (EditText)findViewById(R.id.editTextSearch);


        setTitle("1:1 TUTOR LIST");
        final DataService service = new DataService();
        Boolean data = service.GetDataFromServer();
        populateView();
        View view1 = getLayoutInflater().inflate(R.layout.customtab, null);
        customTabAdapter = new CustomTabAdapter(tabLayout,MainActivity.this);
        customTabAdapter.setTabData(view1);

       // hideSoftKeyboard(MainActivity.this);
        search.setInputType(InputType.TYPE_NULL);

//        search.setOnTouchListener(new View.OnTouchListener(){
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int inType = search.getInputType(); // backup the input type
//                search.setInputType(InputType.TYPE_NULL); // disable soft input
////                search.onTouchEvent(event); // call native handler
////                search.setInputType(inType); // restore input type
//                return true; // consume touch even
//            }
//        });


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence searchString, int start, int before, int count) {
                adapter.setSearchList(String.valueOf(searchString));
            }

            @Override
            public void afterTextChanged(Editable s) {
                hideSoftKeyboard(MainActivity.this);

            }
        });
    }


    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }




    // This is how, DatabaseHelper can be initialized for future use
    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this,DatabaseHelper.class);
        }
        return databaseHelper;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        /*
         * You'll need this in your class to release the helper when done.
         */
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
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

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void populateView()  {

        final Dao<UserProfile, Integer> userDAO;
        try {
            userDAO = getHelper().getUserDAO();

            recyclerView = findViewById(R.id.recyclerView);


            adapter1 = new UserProfileAdapter();
            List<String> userIDList = adapter1.setAdapterData();
            adapter = new UserProfileAdapter(this,userIDList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

            DividerItemDecoration itemDecor = new DividerItemDecoration(this, HORIZONTAL);
            recyclerView.addItemDecoration(itemDecor);

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.photView:

                Intent switchView = new Intent(this, UserPhotoLayout.class);

                this.startActivity(switchView);


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
