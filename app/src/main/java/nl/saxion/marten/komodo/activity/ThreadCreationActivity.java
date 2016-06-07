package nl.saxion.marten.komodo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import nl.saxion.marten.komodo.R;

/**
 * Created by fatahfattah on 07-06-16.
 */
public class ThreadCreationActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_TEXT = "EXTRA_TEXT";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_creation);

        final TextView etThreadCreationTitle = (TextView)findViewById(R.id.etThreadCreationTitle);


        final EditText etThreadCreationText = (EditText)findViewById(R.id.etThreadCreationText);

        Button btnThreadCreationbtn = (Button)findViewById(R.id.btnThreadCreationbtn);
        btnThreadCreationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle result = new Bundle();
                result.putString(EXTRA_TITLE, etThreadCreationTitle.getText().toString());
                result.putString(EXTRA_TEXT, etThreadCreationText.getText().toString());
                Intent intent = new Intent();
                intent.putExtras(result);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }


}
