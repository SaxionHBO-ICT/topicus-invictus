package nl.saxion.marten.komodo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import nl.saxion.marten.komodo.Adapter.MineThreadListAdapter;
import nl.saxion.marten.komodo.Data.UserData;
import nl.saxion.marten.komodo.R;
import nl.saxion.marten.komodo.activity.ThreadListActivity;
import nl.saxion.marten.komodo.model.User;

/**
 * Created by fatahfattah on 31-05-16.
 */
public class MineThreadFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_thread_list, container, false);

        Bundle bundle = getArguments();
        String username = bundle.getString(ThreadListActivity.BUNDLE_USERNAME);
        User user = UserData.getUserFromString(username);

        final ListView listView = (ListView) rootview.findViewById(R.id.thread_list_container);
        final MineThreadListAdapter adapter = new MineThreadListAdapter(getContext(), R.layout.layout_thread_list_item, user.getCreatedThreads());
        listView.setAdapter(adapter);

        return rootview;
    }
}
