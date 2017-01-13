package com.developerbyweekend.aloborg.conversation;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.developerbyweekend.aloborg.R;
import com.developerbyweekend.aloborg.model.ZenDeskMessage;

import java.util.ArrayList;

/**
 * Created by Ashish on 13/01/17.
 */
public class ZenDeskMessageAdapter extends ArrayAdapter<Message> {
    private Activity context;
    private ArrayList<Message> messages;

    public ZenDeskMessageAdapter(Activity context, ArrayList<Message> messages) {
        super(context, R.layout.list_item_message_left, messages);
        this.context = context;
        this.messages = messages;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Message message = messages.get(position);

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_item_message_left, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.lblMsgFrom);
        TextView textViewMsg= (TextView) listViewItem.findViewById(R.id.txtMsg);
        LinearLayout linearLayout = (LinearLayout) listViewItem.findViewById(R.id.linearLayoutLeftMsg);

        textViewName.setText(message.getFromName());
        textViewMsg.setText(message.getMessage());

        if (message.getFromName().contentEquals("ALOBORG")){
            textViewMsg.setBackground(context.getResources().getDrawable(R.drawable.bg_msg_from));
        }
        return listViewItem;
    }
}
