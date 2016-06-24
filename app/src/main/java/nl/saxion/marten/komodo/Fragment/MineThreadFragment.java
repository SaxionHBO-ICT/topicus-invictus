package nl.saxion.marten.komodo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import nl.saxion.marten.komodo.Adapter.MineThreadListAdapter;
import nl.saxion.marten.komodo.Data.ThreadData;
import nl.saxion.marten.komodo.R;
import nl.saxion.marten.komodo.activity.ThreadDetailActivity;
import nl.saxion.marten.komodo.activity.ThreadListActivity;

/**
 * Created by fatahfattah on 31-05-16.
 */

/**
 * Fragment that holds a list of all threads created by a given user
 */
public class MineThreadFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_thread_list, container, false);

        Bundle bundle = getArguments();
        final int user_id = bundle.getInt(ThreadListActivity.BUNDLE_USER_ID);

        final ListView listView = (ListView) rootview.findViewById(R.id.thread_list_container);
        final MineThreadListAdapter adapter = new MineThreadListAdapter(getContext(), R.layout.layout_thread_list_item, ThreadData.getThreadsCreatedByUserID(user_id));
        listView.setAdapter(adapter);

        //Listener to see details of the clicked thread in the list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ThreadDetailActivity.class);
                int thread_id = ThreadData.getThreadsCreatedByUserID(user_id).get(position).getThread_id();
                intent.putExtra("EXTRA_INT", thread_id);
                startActivity(intent);
            }
        });

        return rootview;
    }
}
