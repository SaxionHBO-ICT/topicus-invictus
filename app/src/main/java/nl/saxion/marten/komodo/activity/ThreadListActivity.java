package nl.saxion.marten.komodo.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import nl.saxion.marten.komodo.Data.UserData;
import nl.saxion.marten.komodo.R;
import nl.saxion.marten.komodo.Adapter.ViewPagerAdapter;
import nl.saxion.marten.komodo.model.User;

public class ThreadListActivity extends AppCompatActivity {

    private TabLayout tablayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    private User user;

    public static final String BUNDLE_USERNAME = "BUNDLE_USERNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_list);

        this.user = UserData.getUserFromString(getIntent().getStringExtra(LoginActivity.INTENT_EXTRA_USERNAME));

        tablayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.ViewPager);

        tablayout.addTab(tablayout.newTab().setText("All"));
        tablayout.addTab(tablayout.newTab().setText("Popular"));
        tablayout.addTab(tablayout.newTab().setText("Mine"));
        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);

        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_USERNAME, user.getDisplay_name());

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), tablayout.getTabCount(), bundle);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}
