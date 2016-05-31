package nl.saxion.marten.komodo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import nl.saxion.marten.komodo.R;
import nl.saxion.marten.komodo.Data.ThreadData;
import nl.saxion.marten.komodo.Adapter.AllThreadListAdapter;

/**
 * Created by fatahfattah on 29-05-16.
 */
public class AllThreadFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_thread_list, container, false);

        final ListView listView = (ListView) rootview.findViewById(R.id.thread_list_container);
        final AllThreadListAdapter adapter = new AllThreadListAdapter(getContext(), R.layout.layout_thread_list_item, ThreadData.getThreads());
        listView.setAdapter(adapter);

        return rootview;
    }
}