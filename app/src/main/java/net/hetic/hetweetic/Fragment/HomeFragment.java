package net.hetic.hetweetic.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import net.hetic.hetweetic.R;


public class HomeFragment extends Fragment {

    private EditText mUsernameEt;
    private Button mDisplayTimelineBt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_home, container, false);

        mUsernameEt = (EditText) layout.findViewById(R.id.username_et);
        mDisplayTimelineBt = (Button) layout.findViewById(R.id.display_timeline_bt);

        mDisplayTimelineBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new ListTimelineFragment();
                Bundle args = new Bundle();
                args.putString("Username", mUsernameEt.getText().toString());
                fragment.setArguments(args);

                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();

                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
            }
        });

        return layout;
    }


    @Override
    public void onResume() {
        super.onResume();

    }
}
