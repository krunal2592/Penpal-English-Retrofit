package com.example.owner.penpalenglish.Model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserProfile implements Serializable {

    private Integer userID;
    private String firstName;
    private String lastName;
    private String sex;
    private String birthYear;
    private String country;
    private String school;
    private String hobby;
    private String introduction;
    private String userProfilePhoto;
    private Double rating;
    private Integer credit;
    private String isTeacher;
    private Double latitude;
    private Double longitude;
    private String presenceState;
    private Long timestamp;
    private Double unitPrice;
    private String userAlias;
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
