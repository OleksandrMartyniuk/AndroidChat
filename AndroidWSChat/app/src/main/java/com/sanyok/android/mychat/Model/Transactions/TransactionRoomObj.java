package com.sanyok.android.mychat.Model.Transactions;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by sanyok on 3/6/2017.
 */

public class TransactionRoomObj {

    public TransactionRoomObj(){
        this.clients = new ArrayList<>();
    }

    public TransactionRoomObj(String name, List<String> clients){
        this.clients = clients;
        this.Name = name;
    }

    public String Name;
    public List<String> clients;
}
