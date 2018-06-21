package com.example.owner.penpalenglish;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.owner.penpalenglish.Adapter.UserProfileAdapter;
import com.example.owner.penpalenglish.DAO.UserDAO;
import com.example.owner.penpalenglish.DAO.UserPhotoDAO;
import com.example.owner.penpalenglish.DAO.UserPhotosDAO;
import com.example.owner.penpalenglish.Model.UserPhoto;
import com.example.owner.penpalenglish.Model.UserProfile;
import com.example.owner.penpalenglish.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static String url = "http://54.148.5.0:8080/giaserver/users/profiles";

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private UserProfileAdapter adapter;
    private UserProfile userProfile;
    private UserPhoto userPhoto;
    private UserDAO userDAO;
    private UserPhotosDAO userPhotoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MyTask().execute(url);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

       populaterecyclerView();


    }

    private void populaterecyclerView(){

        UserDAO dao = new UserDAO(this);
        adapter = new UserProfileAdapter(dao.searchUser(),this);

        mRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        // loadStudentList();
    }

    class MyTask extends AsyncTask<String, Void, String> {

        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            return Utils.getJSONString(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (null != pDialog && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if (null == result || result.length() == 0) {
                Toast.makeText(MainActivity.this, "No data found from web!!!", Toast.LENGTH_LONG).show();
                MainActivity.this.finish();
            }
            else {


                jsonToUserProfile(result);
               // populaterecyclerView();

            }
        }
    }

    public void jsonToUserProfile(String data)
    {
        UserProfile user = new UserProfile();
        UserPhoto userPhoto = new UserPhoto();


        data = "{" + "\"UserProfile\"" + ":"  + data + "}";
        try
        {

            userDAO = new UserDAO(MainActivity.this);
            Toast.makeText(MainActivity.this, userDAO.test, Toast.LENGTH_LONG).show();
            userPhotoDAO = new UserPhotosDAO(MainActivity.this);
            //Toast.makeText(MainActivity.this, userPhotoDAO.test, Toast.LENGTH_LONG).show();

            userDAO.getWritableDatabase();
            userPhotoDAO.getWritableDatabase();
            JSONObject mainJson = new JSONObject(data);

            //JSONObject.

            JSONArray jsonArray = mainJson.getJSONArray("UserProfile");


            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject objJson = jsonArray.getJSONObject(i);

                String abc = String.valueOf(objJson.names().get(3));
                String pqr = String.valueOf(objJson.names().get(4));
                user.setUserID(objJson.getInt("userID"));
                user.setBirthYear(objJson.getString("birthYear"));
                if(abc.equals("firstName"))
                {
                    user.setFirstName(objJson.getString("firstName"));
                }
                else {
                    user.setFirstName("");
                }

                if(pqr.equals("lastName")) {
                    user.setLastName(objJson.getString("lastName"));
                }
                else
                {
                    user.setLastName(" ");
                }
                user.setUserAlias(objJson.getString("userAlias"));
                user.setIsTeacher(objJson.getString("isTeacher"));
                user.setPresenceState(objJson.getString("presenceState"));
                user.setCountry(objJson.getString("country"));
                user.setLongitude(objJson.getDouble("longitude"));
                user.setLatitude(objJson.getDouble("latitude"));
                user.setSchool(objJson.getString("school"));
                user.setHobby(objJson.getString("hobby"));
                user.setIntroduction(objJson.getString("introduction"));
                user.setTimestamp(objJson.getLong("timestamp"));
                user.setUnitPrice(objJson.getDouble("unitPrice"));
                Toast.makeText(MainActivity.this,"Json Data " + mainJson ,Toast.LENGTH_LONG).show();
               // userDAO.dbinsert(user);


               // userDAO.dbinsert(user);
                Toast.makeText(MainActivity.this," User Inserted " + user.getFirstName() + " " + user.getLastName() ,Toast.LENGTH_LONG).show();

                JSONArray jsonPhotoArray = objJson.getJSONArray("userPhotos");


                    for (int j = 0; j < jsonPhotoArray.length(); j++) {
                        JSONObject objectPhoto = (JSONObject) jsonPhotoArray.get(j);

                        String pname = String.valueOf(objectPhoto.names().get(2));
                        int pid = objectPhoto.getInt("photoId");
                        int uid = objectPhoto.getInt("userId");
                        // String name = objectPhoto.getString("fileName");

                        int av = objectPhoto.getInt("avatar");

                        userPhoto.setPhotoId(objectPhoto.getInt("photoId"));
                        userPhoto.setUserId(objectPhoto.getInt("userId"));
                        if (pname.equals("fileName")) {
                            userPhoto.setFileName(objectPhoto.getString("fileName"));
                        } else {
                            userPhoto.setFileName(" ");
                        }

                        if (objectPhoto.getInt("avatar") == 1) {
                            userPhoto.setAvatar(objectPhoto.getInt("avatar"));
                            user.setUserProfilePhoto("http://54.148.5.0:8080/giaserver/" + objectPhoto.getInt("userId") + "/profile/media/" + objectPhoto.getString("photoId") + ".jpg");
                        } else {
                            userPhoto.setAvatar(objectPhoto.getInt("avatar"));
                        }

                        Toast.makeText(MainActivity.this, "User Photo Data " + objectPhoto, Toast.LENGTH_LONG).show();

                        // userPhotoDAO.dbinsert(userPhoto);

                    }


                userDAO.dbinsert(user);
//

            }
            userDAO.close();
            userPhotoDAO.close();
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }







}
