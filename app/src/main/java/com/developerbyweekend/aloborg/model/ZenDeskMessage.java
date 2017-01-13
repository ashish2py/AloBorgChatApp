package com.developerbyweekend.aloborg.model;

import android.content.Context;

import com.developerbyweekend.aloborg.R;
import com.developerbyweekend.aloborg.api.APIReceiver;
import com.developerbyweekend.aloborg.api.APIService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import static android.R.attr.password;

/**
 * Created by Ashish on 13/01/17.
 */

public class ZenDeskMessage {
    private String id;
    private String url;
    private String html_url;
    private String vote_sum;
    private String created_at;
    private String name;
    private String title;
    private String body;

    public static final String PARAM_QUESTION = "question";
    public static final String PARAM_ID="id";
    public static final String PARAM_URL="url";
    public static final String PARAM_HTML_URL="html_url";
    public static final String PARAM_VOTE_SUM="vote_sum";
    public static final String PARAM_CREATED_AT="created_at";
    public static final String PARAM_NAME="name";
    public static final String PARAM_HTML_TITLE="title";
    public static final String PARAM_HTML_BODY="body";


    public ZenDeskMessage(String id, String url, String html_url, String vote_sum, String created_at, String name, String title, String body){
        this.id=id;
        this.html_url=html_url;
        this.url = url;
        this.vote_sum = vote_sum;
        this.created_at = created_at;
        this.name=name;
        this.title=title;
        this.body=body;
    }
    public ZenDeskMessage(){
        this.id=this.url=this.html_url=this.vote_sum=this.created_at=this.name=this.title=this.body=null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url= url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getVote_sum() {
        return vote_sum;
    }

    public void setVote_sum(String vote_sum) {
        this.vote_sum = vote_sum;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) { this.title= title;}

    public String getBody() { return body;}

    public void setBody(String body) { this.body= body;}

    private static ZenDeskMessage parseJson(JSONObject jsonResponse)throws JSONException{
        String id, url, html_url, vote_sum, created_at, name, title, body;

        id= jsonResponse.getString(ZenDeskMessage.PARAM_ID);
        url= jsonResponse.getString(ZenDeskMessage.PARAM_URL);
        html_url= jsonResponse.getString(ZenDeskMessage.PARAM_HTML_URL);
        vote_sum = jsonResponse.getString(ZenDeskMessage.PARAM_VOTE_SUM);
        created_at = jsonResponse.getString(ZenDeskMessage.PARAM_CREATED_AT);
        name = jsonResponse.getString(ZenDeskMessage.PARAM_NAME);
        title = jsonResponse.getString(ZenDeskMessage.PARAM_HTML_TITLE);
        body = jsonResponse.getString(ZenDeskMessage.PARAM_HTML_BODY);

        ZenDeskMessage zenDeskMessage= new ZenDeskMessage(id, url,html_url,vote_sum,created_at, name, title, body);
        return  zenDeskMessage;
    }


    // ASKING QUESTIONS
    public static void askQuestion(Context context, Map<String,String> data, final APIReceiver receiver) {

        String question;

        // Get username details
        question = data.get(ZenDeskMessage.PARAM_QUESTION);

        if (question == null) {
            receiver.onError(new IllegalArgumentException(ZenDeskMessage.PARAM_QUESTION + " is required."));
        }

        // Make api Request
        try {

            // POST body
            JSONObject body = new JSONObject();
            body.put(ZenDeskMessage.PARAM_QUESTION, question);

            String url = context.getString(R.string.backend_url) + context.getString(R.string.end_point_chat);
            APIService.post(context, url, body, null, new APIReceiver() {
                @Override
                public void onResponse(Object data) {
                    try {
                        receiver.onResponse(parseJson((JSONObject) data));
                    } catch (Exception e) {
                        receiver.onError(e);
                    }
                }

                @Override
                public void onError(Exception error) {
                    receiver.onError(error);
                }
            });
        } catch (Exception e) {
            receiver.onError(e);
        }
    }
}

