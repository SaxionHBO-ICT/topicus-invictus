package nl.saxion.marten.komodo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import nl.saxion.marten.komodo.Adapter.PopularThreadListAdapter;
import nl.saxion.marten.komodo.Data.ThreadData;
import nl.saxion.marten.komodo.R;

/**
 * Created by fatahfattah on 29-05-16.
 */
public class PopularThreadFragment extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_popular_thread_list, container, false);

        final ListView listView = (ListView) rootview.findViewById(R.id.popular_thread_container);
        final PopularThreadListAdapter adapter = new PopularThreadListAdapter(getContext(), R.layout.layout_thread_list_item, ThreadData.getPopularThreads());
        listView.setAdapter(adapter);

        return rootview;
    }
}
