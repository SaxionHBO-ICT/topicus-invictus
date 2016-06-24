package nl.saxion.marten.komodo.activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import nl.saxion.marten.komodo.Data.ThreadData;
import nl.saxion.marten.komodo.Data.UserData;
import nl.saxion.marten.komodo.R;
import nl.saxion.marten.komodo.model.Comment;
import nl.saxion.marten.komodo.model.Thread;
import nl.saxion.marten.komodo.model.User;

/**
 * Activity where user can login
 * If login is correct the app will go to the ThreadListActivity
 */
public class LoginActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private CheckBox cbRemember;
    private Button btnLogin;

    public static final String INTENT_EXTRA_USERNAME = "INTENT_EXTRA_USERNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        cbRemember = (CheckBox) findViewById(R.id.checkBox);
        btnLogin = (Button) findViewById(R.id.button);

        /**
         * Fetches thread data when app starts up
         */
        if (ThreadData.getThreads().size() == 0) {
            fetchData();
        }

        /**
         * Listener for login button
         * Checks if entered username and password matches an user in the database
         */
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (etUsername.getText() != null && etPassword != null) {
                    String username = etUsername.getText().toString();
                    String password = etPassword.getText().toString();

                    if (UserData.checkLogin(username, password)) {
                        Intent intent = new Intent(getApplicationContext(), ThreadListActivity.class);
                        intent.putExtra(INTENT_EXTRA_USERNAME, username);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }

    /**
     * Method to fetch data from a json file
     * Creates json objects from a the provided assets
     * Turns the json objects into java objects
     */
    private void fetchData() {
        JSONObject jsonObject;
        JSONArray threads;
        JSONArray users;
        JSONArray comments;
        try {
            String assets = readAssetIntoString("data.json");
            try {
                jsonObject = new JSONObject(assets);
                threads = jsonObject.getJSONArray("thread");
                users = jsonObject.getJSONArray("user");

                for (int i = 0; i < users.length(); i++) {
                    User user = new User(users.getJSONObject(i));
                    UserData.getUsers().add(0, user);
                }
                for (int i = 0; i < threads.length(); i++) {
                    Thread thread = new Thread(threads.getJSONObject(i));
                    ThreadData.getThreads().add(0, thread);
                    comments = threads.getJSONObject(i).getJSONArray("comments");

                    for (int j = 0; j < comments.length(); j++) {
                        Comment comment = new Comment(comments.getJSONObject(j));
                        thread.addCommentToThread(comment);
                    }
                }
            }
            catch (JSONException exception) {
                System.out.println("Invalid JSON object");
            }
        }
        catch (IOException exception) {
            System.out.println("File not found");
        }
    }

    /**
     * Method to read the data from a text file
     * return a String which will be used to create objects from
     * @param filename = the json file which holds the data
     * @return String
     * @throws IOException
     */
    private String readAssetIntoString(String filename) throws IOException {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            InputStream is = getAssets().open(filename, AssetManager.ACCESS_BUFFER);
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
