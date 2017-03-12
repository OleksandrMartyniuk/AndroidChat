package com.sanyok.android.mychat.Model;

import com.sanyok.android.mychat.Model.Transactions.DateTime;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by sanyok on 3/6/2017.
 */

public class ChatMessage {

    public ChatMessage(String sndr, String text, String time)
    {
        Sender = sndr;
        Text = text;
        TimeStamp = new DateTime(time);
    }

    public ChatMessage(String sndr, String text, DateTime time)
    {
        Sender = sndr;
        Text = text;
        TimeStamp = time;
    }

    public ChatMessage(JSONObject msgObj){
        try{
            Sender = msgObj.getString("Sender");
        }
        catch (JSONException e){
            try{
                Text = msgObj.getString("Text");
            }
            catch (JSONException e1){
                try{
                    TimeStamp = new DateTime(msgObj.getString("TimeStamp"));
                }
                catch (JSONException e2){
                }
            }
        }

    }

    public ChatMessage()
    {
    }

    public String Sender;
    public String Text;
    public DateTime TimeStamp;
}
