package com.sanyok.android.mychat.Controller;

import android.util.JsonWriter;
import android.util.Log;

import com.google.gson.Gson;
import com.sanyok.android.mychat.Model.RequestObject;

import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;

/**
 * Created by sanyok on 3/6/2017.
 */

public abstract class ChatWebSocketClient extends org.java_websocket.client.WebSocketClient {


    public String Username;

    public ChatWebSocketClient(URI uri) {
        super(uri);
    }

    public ChatWebSocketClient(URI uri, Draft draft){
        super(uri, draft);
    }

    public void SendMessage(RequestObject message){
        Gson gson = new Gson();
        String msg = gson.toJson(message);
        this.send(msg);
    }
}
