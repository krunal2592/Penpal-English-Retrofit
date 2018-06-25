package com.example.owner.penpalenglish.Model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserProfile implements Serializable {

    @DatabaseField(columnName = "userID")
    @SerializedName("userID")
    private Integer userID;

    @DatabaseField(columnName = "firstName")
    @SerializedName("firstName")
    private String firstName;

    @DatabaseField(columnName = "lastName")
    @SerializedName("lastName")
    private String lastName;

    @DatabaseField(columnName = "sex")
    @SerializedName("sex")
    private String sex;

    @DatabaseField(columnName = "birthYear")
    @SerializedName("birthYear")
    private String birthYear;

    @DatabaseField(columnName = "country")
    @SerializedName("country")
    private String country;

    @DatabaseField(columnName = "school")
    @SerializedName("school")
    private String school;

    @DatabaseField(columnName = "hobby")
    @SerializedName("hobby")
    private String hobby;

    @DatabaseField(columnName = "introduction")
    @SerializedName("introduction")
    private String introduction;

    @DatabaseField(columnName = "userProfilePhoto")
    private String userProfilePhoto;
    private Double rating;

    @DatabaseField(columnName = "credit")
    @SerializedName("credit")
    private Integer credit;

    @DatabaseField(columnName = "isTeacher")
    @SerializedName("isTeacher")
    private String isTeacher;

    @DatabaseField(columnName = "latitude")
    @SerializedName("latitude")
    private Double latitude;

    @DatabaseField(columnName = "longitude")
    @SerializedName("longitude")
    private Double longitude;

    @DatabaseField(columnName = "presenceState")
    @SerializedName("presenceState")
    private String presenceState;

    @DatabaseField(columnName = "timestamp")
    @SerializedName("timestamp")
    private Long timestamp;

    @DatabaseField(columnName = "unitPrice")
    @SerializedName("unitPrice")
    private Double unitPrice;

    @DatabaseField(columnName = "userAlias")
    @SerializedName("userAlias")
    private String userAlias;

    @SerializedName("userPhotos")
    private Set<UserPhoto> userPhotos = new HashSet<UserPhoto>();;




    public UserProfile(){}


   public Integer getUserID()
   {
       return userID;
   }

   public void setUserID(Integer userID)
   {
       this.userID = userID;
   }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getSex()
    {
        return sex;
    }
    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getBirthYear()
    {
        return birthYear;
    }
    public void setBirthYear(String birthYear)
    {
        this.birthYear = birthYear;
    }

    public String getCountry()
    {
        return country;
    }
    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getSchool()
    {
        return school;
    }
    public void setSchool(String school)
    {
        this.school = school;
    }

    public String getHobby()
    {
        return hobby;
    }

    public void setHobby(String hobby)
    {
        this.hobby = hobby;
    }

    public String getIntroduction()
    {
        return introduction;
    }

    public void setIntroduction(String introduction)
    {
        this.introduction = introduction;
    }

    public Double getRating()
    {
        return rating;
    }

    public void setRating(Double rating)
    {
        this.rating = rating;
    }
    public String getUserProfilePhoto()
    {
        return userProfilePhoto;
    }

    public void setUserProfilePhoto(String userProfilePhoto)
    {
        this.userProfilePhoto = userProfilePhoto;
    }

    public Integer getCredit()
    {
        return credit;
    }

    public void setCredit(Integer credit)
    {
        this.credit = credit;
    }

    public Double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(Double latitude)
    {
        this.latitude = latitude;
    }

    public Double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(Double longitude)
    {
        this.longitude = longitude;
    }

    public String getIsTeacher(){ return isTeacher; }

    public void setIsTeacher(String isTeacher)
    {
        this.isTeacher = isTeacher;
    }

    public String getPresenceState(){ return presenceState; }

    public void setPresenceState( String presenceState)
    { this.presenceState = presenceState;}

    public String getUserAlias(){return userAlias; }

    public void setUserAlias(String userAlias)
    {this.userAlias = userAlias; }

    public Double getUnitPrice(){return unitPrice;}

    public void setUnitPrice(Double unitPrice)
    { this.unitPrice = unitPrice;}

    public Long getTimestamp(){ return timestamp; }

    public void setTimestamp( Long timestamp)
    { this.timestamp = timestamp;}


    public Set<UserPhoto> getUserPhotos() {
        return userPhotos;
    }

    public void setUserPhotos(Set<UserPhoto> userPhotos) {
        this.userPhotos = userPhotos;
    }





}
