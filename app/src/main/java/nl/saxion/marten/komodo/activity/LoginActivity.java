package nl.saxion.marten.komodo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import nl.saxion.marten.komodo.R;
import nl.saxion.marten.komodo.controller.Login;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final CheckBox cbRemember = (CheckBox) findViewById(R.id.checkBox);
        Button btnLogin = (Button) findViewById(R.id.button);

        if(getPreferences(MODE_PRIVATE).contains("username")) {
            cbRemember.setChecked(true);
            etUsername.setText(getPreferences(MODE_PRIVATE).getString("username", ""));
            etPassword.setText(getPreferences(MODE_PRIVATE).getString("password", ""));
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if(Login.login(username, password)){
                    if(cbRemember.isChecked()) {
                        getPreferences(MODE_PRIVATE).edit().putString("username", username).apply();
                        getPreferences(MODE_PRIVATE).edit().putString("password", password).apply();
                    } else {
                        getPreferences(MODE_PRIVATE).edit().remove("username").apply();
                        getPreferences(MODE_PRIVATE).edit().remove("password").apply();
                    }
                    Intent intent = new Intent(getApplicationContext(), ThreadListActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
