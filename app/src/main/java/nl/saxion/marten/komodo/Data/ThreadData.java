package nl.saxion.marten.komodo.Data;

import java.util.ArrayList;
import java.util.Collections;

import nl.saxion.marten.komodo.model.Thread;

/**
 * Created by fatahfattah on 29-05-16.
 */

/**
 * Singleton Thread data class
 */
public class ThreadData {
    public static ArrayList<Thread> threads = new ArrayList<>();

    public static ArrayList<Thread> getThreads() {
        return threads;
    }

    /**
     * Method to return a list of thread created by a certain user_id
     * @param User_id
     * @return List of threads
     */
    public static ArrayList<Thread> getThreadsCreatedByUserID(int User_id) {
        ArrayList<Thread> createdThreads = new ArrayList<>();

        for (Thread thread : threads) {
            if (thread.getUser_id() == User_id) {
                createdThreads.add(thread);
            }
        }
        return createdThreads;
    }

    /**
     * Method to find a thread by a thread_id
     * @param thread_id
     * @return
     */
    public static Thread findThreadById(int thread_id) {
        for (Thread thread : threads) {
            if (thread.getThread_id() == thread_id) {
                return thread;
            }
        }
        return null;
    }

    /**
     * creates a copy of [threads]
     * sorts the copied arraylist
     * returns the first 10 objects in this list
     * @return list of threads
     */
    public static ArrayList<Thread> getPopularThreads() {
        ArrayList<Thread> sortedThreads = new ArrayList<>(threads);
        Collections.sort(sortedThreads);

        if (sortedThreads.size() >= 10) {
            return new ArrayList<>(sortedThreads.subList(0, 10));
        }
        else {
            return new ArrayList<>(sortedThreads.subList(0, sortedThreads.size()));
        }
    }
}
