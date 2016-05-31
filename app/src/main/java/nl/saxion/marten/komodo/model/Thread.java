package nl.saxion.marten.komodo.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import nl.saxion.marten.komodo.Data.UserData;

/**
 * Created by fatahfattah on 18-05-16. 
 */

public class Thread implements Comparable<Thread>{
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

        UserData.addThreadToUsers(this);
    }

    public void giveKudos() {
        totalKudos += 1;
    }

    @Override
    public int compareTo(Thread another) {
        if (this.getTotalKudos() > another.getTotalKudos()) {
            return -1;
        }
        else if (this.getTotalKudos() < another.getTotalKudos()){
            return 1;
        }
        else {
            return 0;
        }
    }

    public int getTotalComments() {
        return comments.size();
    }

    public int getThread_id() {
        return thread_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getText() {
        return text;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getTotalKudos() {
        return totalKudos;
    }

    public int getTotalViews() {
        return totalViews;
    }

    public int getTotalSubscribed() {
        return totalSubscribed;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getTitle() {
        return title;
    }
}