package nl.saxion.marten.komodo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import nl.saxion.marten.komodo.R;
import nl.saxion.marten.komodo.model.Thread;
import nl.saxion.marten.komodo.Data.ThreadData;

/**
 * Created by fatahfattah on 29-05-16.
 */
public class AllThreadListAdapter extends ArrayAdapter<Thread> {

    public AllThreadListAdapter(Context context, int resource, List<Thread> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_thread_list_item, parent, false);
        }

        final Thread thread = ThreadData.getThreads().get(position);

        TextView tvThreadTitle = (TextView)convertView.findViewById(R.id.tvThreadTitle);
        tvThreadTitle.setText(thread.getTitle());

        TextView tvThreadDate = (TextView)convertView.findViewById(R.id.tvThreadDate);
        tvThreadDate.setText("Thread created at: " + thread.getCreated_at());

        TextView tvThreadKudos = (TextView)convertView.findViewById(R.id.tvThreadKudos);
        tvThreadKudos.setText(Integer.toString(thread.getTotalKudos()));

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
