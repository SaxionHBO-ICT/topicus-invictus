package nl.saxion.marten.komodo.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by fatahfattah on 18-05-16. 
 */

/**
 * User object
 */
public class User {
    private int user_id;
    private String display_name;
    private String password;
    private String firstname;
    private String lastname;
    private String email;

    private String createdOn;
    private String lastOnline;

    private int totalKudos;
    private int totalComments;
    private int totalSubscriptions; // aantal threads die deze user volgt

    //Lijst van threads aangemaakt door deze user
    private ArrayList<Thread> createdThreads = new ArrayList<>();

    public User(JSONObject userObject) throws JSONException {
        this.user_id = userObject.getInt("user_id");
        this.password = userObject.getString("password");
        this.firstname = userObject.getString("firstname");
        this.lastname = userObject.getString("lastname");
        this.email = userObject.getString("email");

        this.createdOn = new Date().toString();

        this.display_name = firstname + "" + lastname;
    }

    /**
     * Adds a thread to this user object
     * @param thread
     */
    public void putNewCreatedThread(Thread thread) {
        createdThreads.add(thread);
    }

    public int getUser_id() {
        return user_id;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public String getPassword() {
        return password;
    }
}