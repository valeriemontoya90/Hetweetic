package net.hetic.hetweetic.Webservices;

import android.util.Log;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.Base64;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import net.hetic.hetweetic.Utils.Config;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;


public class TwitterClient {


    private AsyncHttpClient mClient;

    public TwitterClient() {
        this.mClient = new AsyncHttpClient();
    }

    private final static String CONSUMER_KEY = "t6TbnFS5FUgJAhWHXO4v7GgMg";
    private final static String CONSUMER_SECRET = "ceaqEh1QGO0rqA8V317dGO4l8UZaZEPSzSV8ZquvhueOIPnDVE";

    public void get(final String url, final ResponseHandlerInterface responseHandler) {

        AsyncHttpClient httpClient = new AsyncHttpClient();

        RequestParams requestParams = new RequestParams();
        requestParams.put("grant_type", "client_credentials");

        httpClient.addHeader("Authorization", "Basic " + Base64.encodeToString((CONSUMER_KEY + ":" + CONSUMER_SECRET).getBytes(), Base64.NO_WRAP));
        httpClient.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");


        httpClient.post("https://api.twitter.com/oauth2/token", requestParams, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {

                try {
                    JSONObject jsonObject = new JSONObject(new String(bytes));

                   //Log.e("", "token_type " + jsonObject.getString("token_type") + " access_token " + jsonObject.getString("access_token"));

                    doClientRequestWithOAuth(url, jsonObject.getString("token_type"), jsonObject.getString("access_token"), responseHandler);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                Log.e(Config.LOG_PREFIX, "TwitterClient failed to authenticate app => response " + new String(bytes));
            }

        });
    }


    public void doClientRequestWithOAuth(String url, String tokenType, String accessToken, ResponseHandlerInterface responseHandler){

        mClient.addHeader("Authorization", tokenType + " " + accessToken);

        mClient.get(url, responseHandler);
    }
}
