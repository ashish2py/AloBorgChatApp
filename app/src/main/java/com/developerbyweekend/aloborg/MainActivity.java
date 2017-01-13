package com.developerbyweekend.aloborg;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.developerbyweekend.aloborg.api.APIReceiver;
import com.developerbyweekend.aloborg.conversation.Message;
import com.developerbyweekend.aloborg.conversation.ZenDeskMessageAdapter;
import com.developerbyweekend.aloborg.model.User;
import com.developerbyweekend.aloborg.model.ZenDeskMessage;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> userlists = new ArrayList<String>();
    private ArrayList<Message> messages = new ArrayList<>();
    private ListView listView;
    private ZenDeskMessageAdapter zenDeskMessageAdapter;
    private String loggedUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loggedUsername = getIntent().getStringExtra("USERNAME");
        setTitle("ALOBORG welcomes you");

        messages.add(new Message("ALOBORG", "Hi " + loggedUsername +", \nHow can I help you?"));
        zenDeskMessageAdapter = new ZenDeskMessageAdapter(this, messages);


        listView = (ListView) findViewById(R.id.list_view_messages);
        listView.setAdapter(zenDeskMessageAdapter);


        findViewById(R.id.btnSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputMsg;
                inputMsg = ((EditText)findViewById(R.id.inputMsg)).getText().toString();

                if(inputMsg.trim().equals("")) {
                    ((EditText) findViewById(R.id.inputMsg)).setError("Please enter your query.");
                    return;
                }

                //add question to list view
                addQuestion(inputMsg);

                Log.d("###### msg #####", inputMsg);

                //Progress dialog
                final ProgressDialog dialog = ProgressDialog.show(MainActivity.this,"Ask Question",
                        "Sending your question...",true);
                dialog.show();

                // prepare hashmap
                HashMap<String, String> data = new HashMap();
                data.put(ZenDeskMessage.PARAM_QUESTION , inputMsg);

                // request api
                ZenDeskMessage.askQuestion(getApplicationContext(), data, new APIReceiver() {
                    @Override
                    public void onResponse(Object data) {
                        dialog.cancel();
                        ZenDeskMessage zenDeskMessage = (ZenDeskMessage) data;

                        // append to listview
                        addResponse(zenDeskMessage.getBody().replaceAll("<[^>]+>", ""));
                        //Toast.makeText(getApplicationContext(), zenDeskMessage.getBody().replaceAll("<[^>]+>", ""), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Exception error) {
                        // Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_LONG).show();
                        dialog.cancel();
                        addResponse("I didn't get you.");
                    }
                });

            }
        });

    }

    public void addQuestion(String question){
        messages.add(new Message(loggedUsername.toUpperCase(), question));
        zenDeskMessageAdapter.notifyDataSetChanged();
        ((EditText)findViewById(R.id.inputMsg)).setText("");
    }

    public void addResponse(String response){
        messages.add(new Message("ALOBORG", response));
        zenDeskMessageAdapter.notifyDataSetChanged();
        ((EditText)findViewById(R.id.inputMsg)).setText("");
    }



}
