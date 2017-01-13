package com.developerbyweekend.aloborg.api;

/**
 * Created by Ashish on 13/01/17.
 */


public interface APIReceiver {
    void onResponse(Object data);
    void onError(Exception error);
}


