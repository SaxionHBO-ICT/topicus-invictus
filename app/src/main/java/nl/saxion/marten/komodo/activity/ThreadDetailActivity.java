package nl.saxion.marten.komodo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import nl.saxion.marten.komodo.Adapter.ThreadDetailCommentAdapter;
import nl.saxion.marten.komodo.Data.ThreadData;
import nl.saxion.marten.komodo.R;
import nl.saxion.marten.komodo.model.Comment;
import nl.saxion.marten.komodo.model.Thread;

/**
 * Created by fatahfattah on 31-05-16.
 */
public class ThreadDetailActivity extends AppCompatActivity {

    ThreadDetailCommentAdapter adapter;
    Thread thread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_detail);

        Bundle bundle = getIntent().getExtras();
        final int thread_id = bundle.getInt("EXTRA_INT");

        thread = ThreadData.findThreadById(thread_id);

        TextView tvDetailThreadTitle = (TextView)findViewById(R.id.tvDetailThreadTitle);
        tvDetailThreadTitle.setText(thread.getTitle());

        TextView tvDetailThreadText = (TextView)findViewById(R.id.tvDetailThreadText);
        tvDetailThreadText.setText(thread.getText());

        ListView listview = (ListView)findViewById(R.id.lvDetailThread);
        if (thread.getComments() != null) {
            adapter = new ThreadDetailCommentAdapter(this, R.layout.layout_thread_comment, thread.getComments());
            listview.setAdapter(adapter);
        }

        final EditText etDetailComment = (EditText)findViewById(R.id.etDetailComment);

        Button btnSend = (Button)findViewById(R.id.btnDetailSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etDetailComment.getText().equals("")) {
                    String[] formats = new String[] {"dd-MM-yyyy",};

                    SimpleDateFormat sdf = new SimpleDateFormat(formats[0], Locale.ENGLISH);
                    String date = sdf.format(new Date());

                    Comment comment = new Comment(thread.getComments().size(), etDetailComment.getText().toString(), date, ThreadListActivity.user.getUser_id(), thread_id);

                    ThreadData.findThreadById(thread_id).addCommentToThread(comment);

                    etDetailComment.setText("");

                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
