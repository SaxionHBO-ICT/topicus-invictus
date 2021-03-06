package nl.saxion.marten.komodo.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import nl.saxion.marten.komodo.Data.UserData;

/**
 * Created by fatahfattah on 18-05-16. 
 */

/**
 * Thread object
 */
public class Thread implements Comparable<Thread>{
    private int thread_id;

    private String title;
    private String text;

    private String created_at;
    private int user_id;
    private int totalKudos;

    private List<Comment> comments = new ArrayList<>();

    public Thread(String title, String text, int user_id, String created_at) {
        this.title = title;
        this.text = text;
        this.user_id = user_id;
        this.created_at = created_at;

    }

    public Thread(JSONObject threadobject) throws JSONException {
        this.thread_id = threadobject.getInt("thread_id");

        this.title = threadobject.getString("title");
        this.text = threadobject.getString("text");
        this.created_at = threadobject.getString("created_at");
        this.user_id = threadobject.getInt("user_id");
        this.totalKudos = threadobject.getInt("total_kudos");

        UserData.addThreadToUsers(this);
    }

    public void giveKudos() {
        totalKudos += 1;
    }

    /**
     * Comparator method
     * @param another thread
     * @return thread with most kudos
     */
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

    public void addCommentToThread(Comment comment) {
        comments.add(comment);
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

    public List<Comment> getComments() {
        return comments;
    }

    public String getTitle() {
        return title;
    }

    public int getThread_id() {
        return thread_id;
    }
}