package nl.saxion.marten.komodo.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import nl.saxion.marten.komodo.Fragment.AllThreadFragment;
import nl.saxion.marten.komodo.Fragment.PopularThreadFragment;

/**
 * Created by fatahfattah on 29-05-16.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    int numberOfTabs;

    public ViewPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: AllThreadFragment allThreadFragment = new AllThreadFragment(); return allThreadFragment;
            case 1: PopularThreadFragment popularThreadFragment = new PopularThreadFragment(); return popularThreadFragment;
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
