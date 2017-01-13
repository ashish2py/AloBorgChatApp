package com.developerbyweekend.aloborg.access;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.developerbyweekend.aloborg.MainActivity;
import com.developerbyweekend.aloborg.R;

public class RegisterActivity extends AppCompatActivity {

    public String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViewById(R.id.btnJoin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = ((EditText)findViewById(R.id.editTxtUsername)).getText().toString();
                Log.d("### Username",username);

                if(username.trim().equals("")){
                    ((EditText)findViewById(R.id.editTxtUsername)).setError("Please enter your name.");
                    return;
                }else{
                    Toast.makeText(getApplicationContext(), "Hi "+username+", ALOBORG welcomes you.", Toast.LENGTH_LONG).show();
                    Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
                    mainIntent .putExtra("USERNAME", username);
                    startActivity(mainIntent);
                }
            }
        });

    }
}
