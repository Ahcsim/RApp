package net.ictcampus.voektm.rapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * Created by muhammadchehab on 12/14/17.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> listFragment;
    private List<String> listTitles;

    public ViewPagerAdapter(FragmentManager fragmentManager, List<Fragment> listFragment,
                            List<String> listTitles) {
        super(fragmentManager);
        this.listFragment = listFragment;
        this.listTitles = listTitles;
    }

    @Override
    public Fragment getItem(int position) {
        Log.e("getItem",String.valueOf(position));
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTitles.get(position);
    }
}