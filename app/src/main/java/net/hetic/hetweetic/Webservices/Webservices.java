package net.hetic.hetweetic.Webservices;


import android.util.Log;
import com.loopj.android.http.AsyncHttpResponseHandler;

import net.hetic.hetweetic.Utils.Config;

public class Webservices {

    private static TwitterClient client = new TwitterClient();

    public Webservices() {
    }

    public static void getTimeline(String username, AsyncHttpResponseHandler responseHandler){

        if(Config.DISPLAY_LOG) Log.i(Config.LOG_PREFIX, "request getTimeline movies send => "+Config.URL_TIMELINE+"?user_id="+username+"&screen_name="+username);

        client.get(Config.URL_TIMELINE+"?user_id="+username+"&screen_name="+username, responseHandler);
    }


}
