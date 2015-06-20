package net.hetic.hetweetic.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;

import net.hetic.hetweetic.Adapter.ListTimelineAdapter;
import net.hetic.hetweetic.Models.Tweet;
import net.hetic.hetweetic.R;
import net.hetic.hetweetic.Utils.Config;
import net.hetic.hetweetic.Webservices.Parser;
import net.hetic.hetweetic.Webservices.Webservices;

import org.apache.http.Header;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by valerie on 20/06/15.
 */
public class ListTimelineFragment extends Fragment {

    private ListView mRawResultList;
    private TextView mLabelUsername;
    private String username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_list_timeline, container, false);
        mRawResultList = (ListView) layout.findViewById(R.id.lv_timeline);
        mLabelUsername = (TextView) layout.findViewById(R.id.tv_label_username);

        username = getArguments().getString("Username");

        return layout;
    }


    @Override
    public void onResume() {
        super.onResume();
        launchTimelineRequest(username);
    }


    public void launchTimelineRequest(String username){
        Webservices.getTimeline(username, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                Log.i(Config.LOG_PREFIX, "getTimeline response " + new String(bytes));

                //Doc for parsing > https://dev.twitter.com/rest/reference/get/statuses/user_timeline
                try {
                    ArrayList<Tweet> response = Parser.parseTimelineResponse(new String(bytes));
                    showListTimeline(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                Log.e(Config.LOG_PREFIX, "getTimeline Failed => response " + new String(bytes));

            }
        });
    }

    public void showListTimeline(final ArrayList<Tweet> tweetList) {
        mLabelUsername.setText("Tweet de "+username.toUpperCase());

        ListTimelineAdapter adapter = new ListTimelineAdapter(getActivity(), R.layout.fragment_list_timeline, tweetList);
        mRawResultList.setAdapter(adapter);
        mRawResultList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Fragment fragment = new DetailsTimeLineFragment();
                Bundle args = new Bundle();
                args.putString("Avatar", tweetList.get(position).getProfileImageUrl());
                args.putString("Text", tweetList.get(position).getText());
                args.putString("Username", tweetList.get(position).getUserName());
                args.putString("CreatedAt", tweetList.get(position).getCreatedAd());
                args.putString("Location", tweetList.get(position).getLocation());
                fragment.setArguments(args);

                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();

                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
            }
        });
    }
}