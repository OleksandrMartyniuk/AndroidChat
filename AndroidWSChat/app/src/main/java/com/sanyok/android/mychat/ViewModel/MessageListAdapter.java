package com.sanyok.android.mychat.ViewModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sanyok.android.mychat.Model.ChatMessage;
import com.sanyok.android.mychat.R;

import java.util.ArrayList;

/**
 * Created by sanyok on 3/9/2017.
 */

public class MessageListAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater inflater;
    ArrayList<ChatMessage> messages;

    public MessageListAdapter(Context context, ArrayList<ChatMessage> messages) {

        this.ctx = context;
        this.messages = messages;

        inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int i) {
        return messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View view = convertView;
        if(view == null){
            view = inflater.inflate(R.layout.message_item, parent, false);
        }

        ChatMessage msg = (ChatMessage)getItem(i);

        //////////////// replace username if sender is me

        ((TextView) view.findViewById(R.id.time)).setText(msg.TimeStamp.toString());
        ((TextView) view.findViewById(R.id.item_username)).setText(msg.Sender);
        ((TextView) view.findViewById(R.id.message_text)).setText(msg.Text);


        return view;
    }
}
