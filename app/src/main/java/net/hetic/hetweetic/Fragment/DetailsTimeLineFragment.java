package net.hetic.hetweetic.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import net.hetic.hetweetic.R;

/**
 * Created by valerie on 20/06/15.
 */
public class DetailsTimeLineFragment extends Fragment {

    private ImageView mRawResultAvatar;
    private TextView mRawResultText;
    private TextView mRawResultUsername;
    private TextView mRawResultCreatedAt;
    private TextView mRawResultLocation;

    private ImageLoader mImageLoader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_details_timeline, container, false);

        mRawResultAvatar = (ImageView) layout.findViewById(R.id.raw_result_avatar);
        mRawResultText = (TextView) layout.findViewById(R.id.raw_result_text);
        mRawResultUsername = (TextView) layout.findViewById(R.id.raw_result_username);
        mRawResultCreatedAt = (TextView) layout.findViewById(R.id.raw_result_created_at);
        mRawResultLocation = (TextView) layout.findViewById(R.id.raw_result_location);

        mImageLoader = ImageLoader.getInstance();

        return layout;
    }


    @Override
    public void onResume() {
        super.onResume();

        String avatar = getArguments().getString("Avatar");
        String text = getArguments().getString("Text");
        String username = getArguments().getString("Username");
        String createdAt = getArguments().getString("CreatedAt");
        String location = getArguments().getString("Location");

        showDetailTimeline(avatar, text, username, createdAt, location);

    }

    public void showDetailTimeline(String avatar, String text, String username, String createdAt, String location) {
        mImageLoader.displayImage(avatar, mRawResultAvatar);
        mRawResultText.setText(text);
        mRawResultUsername.setText(username.toUpperCase());
        mRawResultCreatedAt.setText(createdAt);
        mRawResultLocation.setText(location);
    }
}
