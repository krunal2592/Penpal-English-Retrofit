package com.example.owner.penpalenglish.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserProfile implements Serializable {

    @SerializedName("userID")
    private Integer userID;

    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("sex")
    private String sex;
    @SerializedName("birthYear")
    private String birthYear;
    @SerializedName("country")
    private String country;
    @SerializedName("school")
    private String school;
    @SerializedName("hobby")
    private String hobby;
    @SerializedName("introduction")
    private String introduction;
    private String userProfilePhoto;
    private Double rating;
    @SerializedName("credit")
    private Integer credit;
    @SerializedName("isTeacher")
    private String isTeacher;
    @SerializedName("latitude")
    private Double latitude;
    @SerializedName("longitude")
    private Double longitude;
    @SerializedName("presenceState")
    private String presenceState;
    @SerializedName("timestamp")
    private Long timestamp;
    @SerializedName("unitProce")
    private Double unitPrice;
    @SerializedName("userAlias")
    private String userAlias;
    @SerializedName("userPhotos")
    private Set<UserPhoto> userPhotos = new HashSet<UserPhoto>();;


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
