package net.hetic.hetweetic.Webservices;


import net.hetic.hetweetic.Models.Tweet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Parser {


    public Parser(){}

    public static ArrayList<Tweet> parseTimelineResponse(String response) throws JSONException {

        ArrayList<Tweet> parsedTweetList = new ArrayList<Tweet>();

        JSONArray resultsJSON = new JSONArray(response);

        for(int i = 0; i<resultsJSON.length();i++){

            //Creation d'un nouvel objet vide Tweet
            Tweet parsedTweet = new Tweet();

            JSONObject tweetJSON = resultsJSON.getJSONObject(i);

            //Récupérer les valeurs et remplir l'objet
            JSONObject tweetAuthorJSON = tweetJSON.getJSONObject("user");
            String username = tweetAuthorJSON.optString("name", "erreur name introuvable");
            String createdAt = tweetAuthorJSON.optString("created_at", "erreur name introuvable");
            String location = tweetAuthorJSON.optString("location", "erreur name introuvable");
            String avatar = tweetAuthorJSON.optString("profile_image_url", "erreur avatar introuvable");

            String text = tweetJSON.optString("text", "erreur text introuvable");

            parsedTweet.setProfileImageUrl(avatar);
            parsedTweet.setText(text);
            parsedTweet.setUserName(username);
            parsedTweet.setCreatedAd(createdAt);
            parsedTweet.setLocation(location);

            parsedTweetList.add(parsedTweet);

       }

        return  parsedTweetList;
    }

}
