package com.example.imtpmd1617.studievoortgang.Adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.imtpmd1617.studievoortgang.Fragment.GraphFragment;
import com.example.imtpmd1617.studievoortgang.Fragment.ProfileFragment;
import com.example.imtpmd1617.studievoortgang.Fragment.ProfileHoofdfaseModulesFragment;
import com.example.imtpmd1617.studievoortgang.Fragment.ProfilePropedeuseModulesFragment;

public class ProfilePagerAdapter extends FragmentPagerAdapter {
    public ProfilePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new GraphFragment();
                //return new ProfileFragment();
            case 1:
                return new ProfilePropedeuseModulesFragment();
            case 2:
                return new ProfileHoofdfaseModulesFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Voortgang";
                //return "Studentgegevens";
            case 1:
                return "Propedeuse";
            case 2:
                return "Hoofdfase";
        }
        return null;
    }
}
