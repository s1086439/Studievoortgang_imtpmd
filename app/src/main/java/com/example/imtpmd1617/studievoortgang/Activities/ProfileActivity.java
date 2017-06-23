package com.example.imtpmd1617.studievoortgang.Activities;

import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.example.imtpmd1617.studievoortgang.DatabaseHelper;
import com.example.imtpmd1617.studievoortgang.DatabaseInfo;
import com.example.imtpmd1617.studievoortgang.Adapters.ProfilePagerAdapter;
import com.example.imtpmd1617.studievoortgang.R;

import static com.example.imtpmd1617.studievoortgang.DatabaseHelper.getHelper;

public class ProfileActivity extends AppCompatActivity {

    /* Adapter die fragments faciliteert
       Fragments worden in het geheugen geplaatst, en houdt ze daar.
       Omdat we weinig fragments gebruiken met niet veel content hebben hiervoor gekozen
       in plaats van de StatePagerAdapter, die de fragments elke keer bij het verlaten van
       een fragment uit het geheugen haalt.
     */

    private ProfilePagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new ProfilePagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

}
