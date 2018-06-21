package com.example.owner.penpalenglish.Model;



public class UserPhoto {


    protected Integer photoId;

    protected Integer userId;

    protected String fileName;

    protected Integer avatar;

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
}
