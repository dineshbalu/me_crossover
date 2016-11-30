package android.db.me_crossover.adapters;

import android.db.me_crossover.fragments.ConferenceListFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Caritor on 11/29/2016.
 */
public class ManagerPagerAdapter extends FragmentStatePagerAdapter {
    public ManagerPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return new ConferenceListFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Conference";
    }
}
