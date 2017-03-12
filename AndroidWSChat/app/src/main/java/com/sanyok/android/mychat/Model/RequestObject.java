package com.sanyok.android.mychat.Model;

import java.util.Objects;

/**
 * Created by sanyok on 3/6/2017.
 */

public class RequestObject {

    public RequestObject(){

    }

    public RequestObject(String module, String cmd, Object Args){
        this.Module = module;
        this.Cmd = cmd;
        this.Args = Args;
    }

    public String Module;
    public String Cmd;
    public Object Args;
}
