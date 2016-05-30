package nl.saxion.marten.komodo.activity;

import android.content.res.AssetManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import nl.saxion.marten.komodo.R;
import nl.saxion.marten.komodo.model.Thread;
import nl.saxion.marten.komodo.Data.ThreadData;
import nl.saxion.marten.komodo.Adapter.ViewPagerAdapter;

public class ThreadListActivity extends AppCompatActivity {

    private TabLayout tablayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_list);

        if (ThreadData.getThreads().size() == 0) {
            fetchThreads();
        }

        tablayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.ViewPager);

        tablayout.addTab(tablayout.newTab().setText("All Threads"));
        tablayout.addTab(tablayout.newTab().setText("Popular Threads"));
        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), tablayout.getTabCount());
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

    private void fetchThreads() {
        JSONObject jsonObject = null;
        JSONArray threads = new JSONArray();

        try {
            String assets = readAssetIntoString("threads2.json");
            try {
                jsonObject = new JSONObject(assets);
                threads = jsonObject.getJSONArray("thread");

                for (int i = 0; i < threads.length(); i++) {
                    Thread thread = new Thread(threads.getJSONObject(i));
                    ThreadData.getThreads().add(thread);
                    System.out.println("Number of threads created: " + ThreadData.getThreads().size());
                }
            }
            catch (JSONException exception) {

            }
        }
        catch (IOException exception) {
            System.out.println("File not found");
        }
    }

    private String readAssetIntoString(String filename) throws IOException {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            InputStream is = getAssets().open(filename, AssetManager.ACCESS_BUFFER);
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
