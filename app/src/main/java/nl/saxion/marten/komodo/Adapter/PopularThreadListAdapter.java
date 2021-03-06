package nl.saxion.marten.komodo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nl.saxion.marten.komodo.Data.UserData;
import nl.saxion.marten.komodo.R;
import nl.saxion.marten.komodo.model.Thread;

/**
 * Created by fatahfattah on 30-05-16.
 */

/**
 * Adapter to show a list of top 10 popular threads
 * Called in the PopularThreadFragment
 */
public class PopularThreadListAdapter extends ArrayAdapter<Thread> {

    private List<Thread> popularThreads = new ArrayList<>();

    public PopularThreadListAdapter(Context context, int resource, List<Thread> objects) {
        super(context, resource, objects);
        this.popularThreads = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_thread_list_item, parent, false);
        }

        final Thread thread = popularThreads.get(position);

        TextView tvThreadTitle = (TextView)convertView.findViewById(R.id.tvThreadTitle);
        tvThreadTitle.setText(thread.getTitle());

        TextView tvThreadDate = (TextView)convertView.findViewById(R.id.tvThreadDate);
        tvThreadDate.setText("Thread created at: " + thread.getCreated_at());

        TextView tvThreadKudos = (TextView)convertView.findViewById(R.id.tvThreadKudos);
        tvThreadKudos.setText(Integer.toString(thread.getTotalKudos()));

        TextView tvThreadDisplayname = (TextView)convertView.findViewById(R.id.tvThreadDisplayname);
        tvThreadDisplayname.setText("Created by: " + UserData.getUserFromID(thread.getUser_id()).getDisplay_name());

        convertView.findViewById(R.id.ivKudos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.giveKudos();
                notifyDataSetChanged();
            }
        });

        return convertView;
    }


}
