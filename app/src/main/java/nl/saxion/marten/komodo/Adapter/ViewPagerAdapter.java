package nl.saxion.marten.komodo.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import nl.saxion.marten.komodo.Fragment.AllThreadFragment;
import nl.saxion.marten.komodo.Fragment.MineThreadFragment;
import nl.saxion.marten.komodo.Fragment.PopularThreadFragment;

/**
 * Created by fatahfattah on 29-05-16.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    int numberOfTabs;
    Bundle fragmentBundle;

    public ViewPagerAdapter(FragmentManager fm, int numberOfTabs, Bundle bundle) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
        this.fragmentBundle = bundle;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: AllThreadFragment allThreadFragment = new AllThreadFragment(); return allThreadFragment;
            case 1: PopularThreadFragment popularThreadFragment = new PopularThreadFragment(); return popularThreadFragment;
            case 2: MineThreadFragment mineThreadFragment = new MineThreadFragment(); mineThreadFragment.setArguments(fragmentBundle); return mineThreadFragment;
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
