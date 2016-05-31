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

        fetchData();

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
                    UserData.getUsers().add(user);

                    //System.out.println("Number of users created: " + UserData.getUsers().size());
                }

                for (int i = 0; i < threads.length(); i++) {
                    Thread thread = new Thread(threads.getJSONObject(i));
                    ThreadData.getThreads().add(thread);

                    comments = threads.getJSONObject(i).getJSONArray("comments");

                    for (int j = 0; j < comments.length(); j++) {
                        Comment comment = new Comment(comments.getJSONObject(j));

                        thread.addCommentToThread(comment);
                    }
                    System.out.println("Number of threads created: " + ThreadData.getThreads().size());
                }
            }
            catch (JSONException exception) {

            }
        }
        catch (IOException exception) {
            System.out.println("File not found");
        }
    }

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
