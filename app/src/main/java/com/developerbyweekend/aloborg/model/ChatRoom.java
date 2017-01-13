package com.developerbyweekend.aloborg.model;

/**
 * Created by Ashish on 13/01/17.
 */

public class ChatRoom {
    private int id;
    private String name;
    private String lastmessage;
    private String timestamp;

    public ChatRoom(){}

    public ChatRoom(String name, String lastmessage, String timestamp, int id){
        this.name=name;
        this.lastmessage=lastmessage;
        this.id=id;
        this.timestamp=timestamp;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String lastMessage(String message){
        return lastmessage;
    }


}
