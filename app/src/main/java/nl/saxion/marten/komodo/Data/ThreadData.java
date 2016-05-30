package nl.saxion.marten.komodo.Data;

import java.util.ArrayList;
import java.util.Collections;

import nl.saxion.marten.komodo.model.Thread;

/**
 * Created by fatahfattah on 29-05-16.
 */
public class ThreadData {
    public static ArrayList<Thread> threads = new ArrayList<>();

    public static ArrayList<Thread> getThreads() {
        return threads;
    }

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
