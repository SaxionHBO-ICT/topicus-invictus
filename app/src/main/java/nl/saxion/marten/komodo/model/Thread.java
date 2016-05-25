package nl.saxion.marten.komodo.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by fatahfattah on 18-05-16. 
 */

public class Thread {
    private int thread_id;

    private String title;
    private String text;

    private String created_at;
    private int user_id;
    private int totalKudos;
    private int totalComments;
    private int totalViews;
    private int totalSubscribed;

    private List<Comment> comments;

    public Thread(JSONObject threadobject) throws JSONException {
        this.thread_id = threadobject.getInt("thread_id");

        this.title = threadobject.getString("title");
        this.text = threadobject.getString("text");
        this.created_at = threadobject.getString("created_at");
        this.user_id = threadobject.getInt("user_id");
        this.totalKudos = threadobject.getInt("total_kudos");
        this.totalComments = threadobject.getInt("total_comments");
        this.totalViews = threadobject.getInt("total_views");
        this.totalSubscribed = threadobject.getInt("total_subscribed");

        JSONArray threadarray = threadobject.getJSONArray("comments");
        for (int i = 0; i < threadarray.length() ; i++) {
            comments.add(new Comment(threadarray.getJSONObject(i)));
        }
    }

    public Comment getLastComment() { //waarom wil je dit?
        return comments.get(comments.size() - 1);
    }

    public int getTotalComments() {
        return comments.size();
    }
} 