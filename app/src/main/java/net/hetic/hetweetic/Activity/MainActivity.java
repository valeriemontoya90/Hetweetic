package net.hetic.hetweetic.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import net.hetic.hetweetic.Fragment.HomeFragment;
import net.hetic.hetweetic.R;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mMenuDrawer;
    private ListView mLeftMenuLV;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mLeftMenuLV = (ListView) findViewById(R.id.menu_left);
        mMenuDrawer = (DrawerLayout) findViewById(R.id.menu_drawer);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.container, new HomeFragment());
        fragmentTransaction.commit();


        setupLeftMenu();
        setupToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void setupLeftMenu(){

        final ArrayList<String> menuEntry = new ArrayList<>();
        menuEntry.add("Accueil");
        menuEntry.add("A l'affiche");

        ArrayAdapter menuAdapter = new ArrayAdapter<String>(this, R.layout.menu_item, menuEntry);

        mLeftMenuLV.setAdapter(menuAdapter);

        mLeftMenuLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                android.app.FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();

                if (menuEntry.get(position).equals("Accueil")) {
                    fragmentTransaction.replace(R.id.container, new HomeFragment());
                } else if (menuEntry.get(position).equals("A l'affiche")) {


                    //   fragmentTransaction.replace(R.id.container, new MovieListFragment());
                }

                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
                mMenuDrawer.closeDrawers();
            }
        });
    }


    public void setupToolbar(){

        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, mMenuDrawer, mToolbar, 0,0) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
        };
        mMenuDrawer.setDrawerListener(mDrawerToggle);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /*
    / ! \
    AppCompatActivity break getFragmentManager.addToBackStack()
    Work Around => using getSupportFragmentManager() instead of  getFragmentManager()
    Side Effect => getSupportFragmentManager() break Material Design backport on device version <5.0
    Solution = override onBackPressed() method  :-(
    More about this issue => http://stackoverflow.com/questions/29787250/fragment-back-stack-does-not-work-when-extending-appcompatactivity/29788104#29788104
 */
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
