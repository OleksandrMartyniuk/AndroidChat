package com.sanyok.android.mychat;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.sanyok.android.mychat.Controller.ChatWebSocketClient;
import com.sanyok.android.mychat.Controller.RequestManager;
import com.sanyok.android.mychat.Model.ChatMessage;
import com.sanyok.android.mychat.Model.RequestObject;
import com.sanyok.android.mychat.Model.RoomObject;

import org.java_websocket.drafts.Draft_17;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by sanyok on 3/9/2017.
 */

public class ConnectionService extends Service {


    public final static String ACTION_PREFIX = "wschat.service.";
    public final static String BANNED = "banned";
    public final static String UNBANNED = "unbanned";
    public final static String ROOM_DATA = "roomdata";
    public final static String LOGIN_RESULT = "login";
    public final static String MESSAGE = "message";
    public final static String USER_ACTION = "user_action";
    public final static String PRIVATE = "private_message";
    public final static String ROOM_ACTION = "room_action";
    public final static String ROOM_HISTORY = "room_history";
    public final static String PRIVATE_HISTORY = "private_history";
/*
public delegate void adminDelegate();
        public static event adminDelegate Banned;
        public static event adminDelegate Unbanned;

        public delegate void roomDataDelegate(RoomObj[] rooms);
        public static event roomDataDelegate roomDataReceived;

        public delegate void loginDelegate(string username);
        public static event loginDelegate loginSuccessfull;
        public static event loginDelegate loggedAsAdmin;
        public static event loginDelegate loggedBanned;
        public static event loginDelegate loginFail;

        public delegate void messageDelegate(string room, ChatMessage msg);
        public delegate void notificationDelegate(string room);
        public delegate void dataDelegate(string room, ChatMessage[] msg);
        public delegate void userMovedDelegate(string username, string room);

        public static event messageDelegate messageRecieived;
        public static event notificationDelegate notificationReceived;
        public static event dataDelegate msgDataReceived;
        public static event userMovedDelegate UserEntered;
        public static event userMovedDelegate UserLeft;

        public delegate void pmDelegate(ChatMessage msg);
        public static event pmDelegate privateMessageReceived;

        public delegate void roomDelegate(string roomName);
        public static event roomDelegate roomCreated;
        public static event roomDelegate roomRemoved;
        public static event roomDelegate roomError;

        public delegate void RoomHistoryDelegate(string room, ChatMessage[] msgs);
        public delegate void PrivateHistoryDelegate(string user, ChatMessage[] msgs);
        public static event RoomHistoryDelegate RoomHistoryReceived;
        public static event PrivateHistoryDelegate PrivateHistoryReceived;

 */

    ChatWebSocketClient client;
    RequestManager manager;

    private final IBinder binder = new LocalBinder();

    final String LOG_PREFIX = "service";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(LOG_PREFIX, "Service started");
        final ConnectionService service = this;
        String wsuri = getResources().getString(R.string.WS_URI);

        try {
            client = new ChatWebSocketClient(new URI(wsuri), new Draft_17()) {

                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    manager = new RequestManager(client);
                    Log.d(LOG_PREFIX, "Connections opened");
                }

                @Override
                public void onMessage(String message) {
                    try {
                        JSONObject reqObj = new JSONObject(message);
                        String module = reqObj.getString("Module");
                        String cmd = reqObj.getString("Cmd");

                        switch (module) {
                            case "admin":
                                switch (cmd) {
                                    case "ban":
                                        //
                                        //TODO
                                        //
                                        break;
                                    case "unban":
                                        //
                                        //TODO
                                        //
                                        break;
                                }
                                break;
                            case "info":
                                if (cmd == "all") {
                                    ArrayList<RoomObject> rooms = new ArrayList<>();

                                        JSONArray rlist = reqObj.getJSONArray("Args");
                                        for (int i = 0; i < rlist.length(); i++)
                                        {
                                            RoomObject r = new RoomObject();
                                            JSONObject current = rlist.getJSONObject(i);
                                            r.Name = current.getString("Name");

                                            r.clients = new ArrayList<String>();
                                            JSONArray cl = current.getJSONArray("clients");
                                            for (int j = 0; j < cl.length(); j++) {
                                                r.clients.add(cl.getString(j));
                                            }
                                            rooms.add(r);
                                        }

                                    if (rooms.size() > 0) {
                                        //
                                        //TODO
                                        //
                                    }
                                }
                                break;
                            case "login":
                                String username = reqObj.getString("Args");
                                switch (cmd) {
                                    case "ok":
                                        //
                                        //TODO
                                        //
                                        break;
                                    case "admin":
                                        //
                                        //TODO
                                        //
                                        break;
                                    case "banned":
                                        //
                                        //TODO
                                        //
                                        break;
                                    default:
                                        //
                                        //TODO
                                        //
                                        break;
                                }
                                break;
                            case "msg":
                                JSONArray args = reqObj.getJSONArray("Args");
                                switch (cmd) {
                                    case "msg":
                                        String room = args.getString(0);
                                        JSONObject msgObj = args.getJSONObject(1);
                                        ChatMessage msg = new ChatMessage(msgObj);
                                        //
                                        //TODO
                                        //
                                        break;
                                    case "entered":
                                        room = args.getString(0);
                                        String user = args.getString(1);
                                        //
                                        //TODO
                                        //
                                        break;
                                    case "left":
                                        room = args.getString(0);
                                        user = args.getString(1);
                                        //
                                        //TODO
                                        //
                                        break;
                                }
                                break;
                            case "private":
                                args = reqObj.getJSONArray("Args");
                                String room = args.getString(0);
                                JSONObject msgObj = args.getJSONObject(1);
                                ChatMessage msg = new ChatMessage(msgObj);
                                //
                                //TODO
                                //
                                break;
                            case "room":
                                args = reqObj.getJSONArray("Args");
                                room = args.getString(0);
                                switch (cmd) {
                                    case "created":
                                        //
                                        //TODO
                                        //
                                        break;
                                    case "removed":
                                        //
                                        //TODO
                                        //
                                        break;
                                    default:
                                        //
                                        //TODO
                                        //
                                        break;
                                }
                                break;
                            case "history":
                                args = reqObj.getJSONArray("Args");
                                room = args.getString(0);
                                JSONArray msgJson = args.getJSONArray(1);
                                ArrayList<ChatMessage> msgList = new ArrayList<>();
                                for(int i=0; i< msgJson.length(); i++){
                                    JSONObject mobj = msgJson.getJSONObject(i);
                                    ChatMessage m = new ChatMessage(mobj);
                                    msgList.add(m);
                                }
                                switch (cmd) {

                                    case "room":
                                        //
                                        //TODO
                                        //
                                        break;
                                    case "private":
                                        //
                                        //TODO
                                        //
                                        break;
                                    default:
                                        break;
                                }
                                break;
                        }
                    } catch (JSONException e) {
                        Toast.makeText(service, "Parse error: " + message, Toast.LENGTH_LONG);
                        return;
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d(LOG_PREFIX, "Websocket connection closed: " + reason);
                }

                @Override
                public void onError(Exception ex) {
                    Log.d(LOG_PREFIX,"Websocket error: " + ex.getMessage());
                }
            };
        }
        catch (URISyntaxException e) {
            Log.d(LOG_PREFIX, e.getMessage());
        }

        client.connect();
        return super.onStartCommand(intent, flags, startId);
    }


    public class LocalBinder extends Binder{
        ConnectionService getService(){
            return ConnectionService.this;
        }
    }

    public RequestManager getManager(){
        return manager;
    }

    public void Test(){

        Log.d(LOG_PREFIX, "TEST");
    }

}
