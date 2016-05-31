package nl.saxion.marten.komodo.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gebruiker on 5/18/2016. 
 */

public class Comment {
    private int comment_id;
    private String text;
    private String created_at;
    private int user_id; // Door wie
    private int thread_id; // In welke thread
    private int total_kudos;

    public Comment(JSONObject commentObject) throws JSONException{
        this.comment_id = commentObject.getInt("comment_id");

        this.text = commentObject.getString("text");
        this.created_at = commentObject.getString("created_at");
        this.user_id = commentObject.getInt("user_id");
        this.thread_id = commentObject.getInt("thread_id");
        this.total_kudos = commentObject.getInt("total_kudos");
    }

    public int getComment_id() {
        return comment_id;
    }

    public String getText() {
        return text;
    }

    public String getCreated_at() {
        return created_at;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getThread_id() {
        return thread_id;
    }

    public int getTotal_kudos() {
        return total_kudos;
    }
}