package com.example.owner.penpalenglish.Model;


import com.j256.ormlite.field.DatabaseField;

public class UserPhoto {

    @DatabaseField(columnName = "photoId")
    protected Integer photoId;

    @DatabaseField(columnName = "userId")
    protected Integer userId;

    @DatabaseField(columnName = "fileName")
    protected String fileName;

    @DatabaseField(columnName = "avatar")
    protected Integer avatar;

    @DatabaseField(columnName = "photoPath")
    protected String photopath;


    //Default Constructor
    public UserPhoto(){}

    public Integer getAvatar() {
        return avatar;
    }
    public void setAvatar(Integer avatar) {
        this.avatar = avatar;
    }

    public boolean isAvatar(){
        return avatar.intValue()==1;
    }

    public Integer getPhotoId() {
        return photoId;
    }
    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPhotopath() {
        return photopath;
    }
    public void setPhotopath(String photopath) {
        this.photopath = photopath;
    }
}
