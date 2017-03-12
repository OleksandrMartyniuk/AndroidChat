package com.sanyok.android.mychat.Model.Transactions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {

    final SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    final SimpleDateFormat outputFormatter = new SimpleDateFormat("[HH:mm:ss]");

    private Date date;

    public DateTime() {
        date = new Date();
    }

    public DateTime(String dateTime){
        try{
            date = inputFormatter.parse(dateTime);
        }
        catch (ParseException e){
            date = new Date();
        }
    }

    public  DateTime(Date date){
        this.date = date;
    }

    public static DateTime MaxValue(){
        return new DateTime(new Date(Long.MAX_VALUE));
    }

    public String getTime(){
        return outputFormatter.format(date);
    }
}
