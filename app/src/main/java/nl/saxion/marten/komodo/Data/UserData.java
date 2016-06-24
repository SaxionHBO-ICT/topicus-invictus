package nl.saxion.marten.komodo.Data;

import java.util.ArrayList;

import nl.saxion.marten.komodo.model.Thread;
import nl.saxion.marten.komodo.model.User;

/**
 * Created by fatahfattah on 31-05-16.
 */

/**
 * Singleton User data class
 */
public class UserData {
    public static ArrayList<User> users = new ArrayList<>();

    public static ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Checks if input matches an user in the database
     * @param username = user input
     * @param password = user input
     * @return
     */
    public static boolean checkLogin(String username, String password) {
        for (User user : users) {
            if (username.equals(user.getDisplay_name())) {
                if (password.equals(user.getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Adds a thread to the correct user
     * @param thread = thread object
     */
    public static void addThreadToUsers(Thread thread) {
        for (User user : users) {
            if (thread.getUser_id() == user.getUser_id()) {
                user.putNewCreatedThread(thread);
            }
        }
    }

    /**
     * Method to find a user by a given username
     * @param username
     * @return
     */
    public static User getUserFromString(String username) {
        if (username != null) {
            for (User user : users) {
                if (user.getDisplay_name().equals(username)) {
                    return user;
                }
            }
        }
        return null;
    }

    /**
     * Method to find a user from a given user_id
     * @param user_id
     * @return
     */
    public static User getUserFromID(int user_id) {
        for (User user : users) {
            if (user.getUser_id() == user_id) {
                return user;
            }
        }
        return null;
    }

}
