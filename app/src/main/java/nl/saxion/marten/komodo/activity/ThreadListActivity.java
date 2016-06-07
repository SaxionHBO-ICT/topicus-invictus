package nl.saxion.marten.komodo.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import nl.saxion.marten.komodo.Data.ThreadData;
import nl.saxion.marten.komodo.Data.UserData;
import nl.saxion.marten.komodo.R;
import nl.saxion.marten.komodo.Adapter.ViewPagerAdapter;
import nl.saxion.marten.komodo.model.Thread;
import nl.saxion.marten.komodo.model.User;

public class ThreadListActivity extends AppCompatActivity {

    private TabLayout tablayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    public static User user;

    public static final String BUNDLE_USER_ID = "BUNDLE_USER_ID";
    public static final int REQUEST_CODE = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_list);

        user = UserData.getUserFromString(getIntent().getStringExtra(LoginActivity.INTENT_EXTRA_USERNAME));

        tablayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.ViewPager);

        tablayout.addTab(tablayout.newTab().setText("All"));
        tablayout.addTab(tablayout.newTab().setText("Popular"));
        tablayout.addTab(tablayout.newTab().setText("Mine"));
        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);

        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_USER_ID, user.getUser_id());

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), tablayout.getTabCount(), bundle);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.new_thread) {
            Intent intent = new Intent(this, ThreadCreationActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String[] formats = new String[] {"dd-MM-yyyy",};

        switch (requestCode) {
            case 1111:
                if (resultCode == RESULT_OK) {
                    Bundle result = data.getExtras();
                    String title = result.getString(ThreadCreationActivity.EXTRA_TITLE);
                    String text = result.getString(ThreadCreationActivity.EXTRA_TEXT);

                    //Creates datestamp in correct format
                    SimpleDateFormat sdf = new SimpleDateFormat(formats[0], Locale.ENGLISH);
                    String date = sdf.format(new Date());

                    Thread thread = new Thread(title, text, user.getUser_id(), date);
                    ThreadData.getThreads().add(thread);
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
