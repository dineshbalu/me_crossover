package android.db.me_crossover;

import android.content.Intent;
import android.db.me_crossover.adapters.ManagerPagerAdapter;
import android.db.me_crossover.helper.DBHelper;
import android.db.me_crossover.interfaces.OnListFragmentInteractionListener;
import android.db.me_crossover.model.Conference;
import android.db.me_crossover.model.User;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements OnListFragmentInteractionListener {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper helper = new DBHelper(this);

        User user = helper.getUser("admin@conference.com","123456");

        if( user != null )
            Log.v("MainActivity","User = " + user.getName());

        initPager();
    }

    private void initPager() {
        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
        ManagerPagerAdapter adapter =
                new ManagerPagerAdapter(
                        getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter( adapter );
        mViewPager.setCurrentItem(1);

    }

    @Override
    public void onListFragmentInteraction( Conference item) {

    }
}
