package nl.saxion.marten.komodo.model;

import java.util.Date;
import java.util.List;

/**
 * Created by fatahfattah on 18-05-16. 
 */

public class Thread {
    private int thread_id;

    private String title;
    private String text;
    private String short_description;

    private String createdOn;
    private User createdBy;
    //private Comment lastComment;
    private int totalKudos;
    //private int totalComments;
    //private int totalViews;
    private int totalSubscribed;

    private List<Comment> comments;
    private List<User> usersThatCommented; // lijst van users die een comment geplaats hebben 

    public Thread() {
        thread_id = thread_id + 1; // thread id verhoogt met 1 ieder keer dat een nieuwe thread object aangemaakt wordt 
        createdOn = new Date().toString();
        //lastComment = comments.get(comments.size() - 1);
    }

    public Comment getLastComment() { //waarom wil je dit?
        return comments.get(comments.size() - 1);
    }

    public int getTotalComments() {
        return comments.size();
    }
} 