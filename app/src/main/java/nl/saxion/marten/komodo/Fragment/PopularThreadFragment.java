package nl.saxion.marten.komodo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import nl.saxion.marten.komodo.Adapter.PopularThreadListAdapter;
import nl.saxion.marten.komodo.Data.ThreadData;
import nl.saxion.marten.komodo.R;
import nl.saxion.marten.komodo.activity.ThreadDetailActivity;

/**
 * Created by fatahfattah on 29-05-16.
 */

/**
 * Fragment that holds a list of top 10 popular threads
 */
public class PopularThreadFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_popular_thread_list, container, false);

        final ListView listView = (ListView) rootview.findViewById(R.id.popular_thread_container);
        PopularThreadListAdapter adapter = new PopularThreadListAdapter(getContext(), R.layout.layout_thread_list_item, ThreadData.getPopularThreads());
        listView.setAdapter(adapter);

        //Listener to see details of the clicked thread in the list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ThreadDetailActivity.class);
                int thread_id = ThreadData.getPopularThreads().get(position).getThread_id();
                intent.putExtra("EXTRA_INT", thread_id);
                startActivity(intent);
            }
        });

        return rootview;
    }

}
