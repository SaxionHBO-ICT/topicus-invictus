package nl.saxion.marten.komodo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import nl.saxion.marten.komodo.Adapter.ThreadDetailCommentAdapter;
import nl.saxion.marten.komodo.Data.ThreadData;
import nl.saxion.marten.komodo.R;
import nl.saxion.marten.komodo.model.Thread;

/**
 * Created by fatahfattah on 31-05-16.
 */
public class ThreadDetailActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_detail);

        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("EXTRA_INT");

        Thread thread = ThreadData.getThreads().get(position);

        TextView tvDetailThreadTitle = (TextView)findViewById(R.id.tvDetailThreadTitle);
        tvDetailThreadTitle.setText(thread.getTitle());

        TextView tvDetailThreadText = (TextView)findViewById(R.id.tvDetailThreadText);
        tvDetailThreadText.setText(thread.getText());

        ListView listview = (ListView)findViewById(R.id.lvDetailThread);
        if (thread.getComments() != null) {
            ThreadDetailCommentAdapter adapter = new ThreadDetailCommentAdapter(this, R.layout.layout_thread_comment, thread.getComments());
            listview.setAdapter(adapter);
        }
    }
}
