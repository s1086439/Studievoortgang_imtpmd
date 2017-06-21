package com.example.imtpmd1617.studievoortgang.Adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.imtpmd1617.studievoortgang.Fragments.ProfileFragment;
import com.example.imtpmd1617.studievoortgang.Fragments.ProfileHoofdfaseModulesFragment;
import com.example.imtpmd1617.studievoortgang.Fragments.ProfileKeuzeModulesFragment;
import com.example.imtpmd1617.studievoortgang.Fragments.ProfilePropedeuseModulesFragment;

public class ProfilePagerAdapter extends FragmentPagerAdapter {
    public ProfilePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 4;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ProfileFragment();
            case 1:
                return new ProfilePropedeuseModulesFragment();
            case 2:
                return new ProfileHoofdfaseModulesFragment();
            case 3:
                return new ProfileKeuzeModulesFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Studentgegevens";
            case 1:
                return "Propedeuse";
            case 2:
                return "Hoofdfase";
            case 3:
                return "Keuzevakken";
        }
        return null;
    }
}
