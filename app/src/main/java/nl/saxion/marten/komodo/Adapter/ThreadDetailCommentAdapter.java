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
import nl.saxion.marten.komodo.model.Comment;

/**
 * Created by fatahfattah on 31-05-16.
 */
public class ThreadDetailCommentAdapter extends ArrayAdapter<Comment> {
    List<Comment> comments = new ArrayList<>();

    public ThreadDetailCommentAdapter(Context context, int resource, List<Comment> objects) {
        super(context, resource, objects);
        this.comments = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_thread_comment, parent, false);
        }
        Comment comment = comments.get(position);

        TextView tvCommentTitle = (TextView)convertView.findViewById(R.id.tvComment);
        tvCommentTitle.setText(comment.getText());
        TextView tvCommentText = (TextView)convertView.findViewById(R.id.tvCommentDisplayanem);
        tvCommentText.setText(UserData.getUserFromID(comment.getUser_id()).getDisplay_name());

        return convertView;
    }
}
