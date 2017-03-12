package com.sanyok.android.mychat.Controller;

import com.sanyok.android.mychat.Model.ChatMessage;
import com.sanyok.android.mychat.Model.RequestObject;
import com.sanyok.android.mychat.Model.Transactions.DateTime;

import org.java_websocket.client.*;

import java.util.Date;

/**
 * Created by sanyok on 3/6/2017.
 */

public class RequestManager {

    ChatWebSocketClient client;

    public RequestManager(ChatWebSocketClient client){
        this.client = client;
    }

    private void SendMessage(RequestObject r){
        client.SendMessage(r);
    }

    public void Login(String name,String password)
    {
        SendMessage(new RequestObject("login", "in", new Object[] { name, password }));
    }
    public void LoginGmail(String name)
    {
        SendMessage(new RequestObject("login", "Gmail",  name ));
    }
    public void LoginFacebook(String name)
    {
        SendMessage(new RequestObject("login", "Facebook", name));
    }
    public void LoginReg(String name, String password, String email)
    {
        SendMessage(new RequestObject("login", "Registration", new Object[] { name, password, email }));
    }
    public void LoginForgot(String name)
    {
        SendMessage(new RequestObject("login", "Forgot", name));
    }

    public void Logout(String name)
    {
        SendMessage(new RequestObject("logout", null, name));
    }

    public void SendMessage(String msg, String room)
    {
        SendMessage(new RequestObject("msg", "msg",
                new Object[] { room, new ChatMessage(client.Username, msg, new DateTime()) }));
    }

    public void SetActiveRoom(String room)
    {
        SendMessage(new RequestObject("msg", "active", room));
    }

    public void LeaveRoom(String room)
    {
        SendMessage(new RequestObject("msg", "leave", room));
    }

    public void CreateRoom(String roomName)
    {
        SendMessage(new RequestObject("room", "create", roomName));
    }

    public void CloseRoom(String roomName)
    {
        SendMessage(new RequestObject("room", "close", roomName));
    }

    public void RequestData()
    {
        SendMessage(new RequestObject("info", "get", "null"));
    }

    public void RequestMessageList(String room, Date last)
    {
        SendMessage(new RequestObject("history", "room", new Object[] { room, last }));
    }

    public void RequestPrivateMessageList(String username, DateTime last)
    {
        SendMessage(new RequestObject("history", "private",
                new Object[] { client.Username, username, last }));
    }

    public void SendPrivateMessage(String userName, ChatMessage msg)
    {
        SendMessage(new RequestObject("private", userName, msg));
    }

    public void AdminBan(String userName, DateTime exp)
    {
        SendMessage(new RequestObject("admin", "ban", new Object[] { userName, exp }));
    }

    public void AdminBanEternal(String userName)
    {
        SendMessage(new RequestObject("admin", "ban",
                new Object[] { userName, DateTime.MaxValue() }));
    }
    public void AdminUnban(String userName)
    {
        SendMessage(new RequestObject("admin", "unban", userName));
    }

}
