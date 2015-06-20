package net.hetic.hetweetic.Models;

/**
 * Created by chipowok on 10/06/15.
 */
public class Tweet {

    private String profileImageUrl;
    private String text;
    private String userName;
    private String createdAd;
    private String location;


    public Tweet() {
    }

    public Tweet(String profileImageUrl, String text, String userName, String createdAd, String location) {
        this.profileImageUrl = profileImageUrl;
        this.text = text;
        this.userName = userName;
        this.createdAd = createdAd;
        this.location = location;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreatedAd() {
        return createdAd;
    }

    public void setCreatedAd(String createdAd) {
        this.createdAd = createdAd;
    }
}
