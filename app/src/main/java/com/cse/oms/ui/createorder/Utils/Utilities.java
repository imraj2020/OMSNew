package com.cse.oms.ui.createorder.Utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Utilities {
    public static void showLogcatMessage(String message) {
        Log.d("tag", message);
    }

    public static String getFormattedDateTime(Calendar calendar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.ENGLISH);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getFormattedDateTimeFromTimestamp(long timestamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getFormattedDate(Calendar calendar) {
        String tempMonth = "";
        String tempDay = "";
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (day < 10)
            tempDay = "0" + day;
        else
            tempDay += day;
        month = month + 1; //since month is 0 based but in server it is 1 based
        if (month < 10)
            tempMonth += "0" + month;
        else
            tempMonth += month;

        return tempDay + "-" + tempMonth + "-" + year;
    }
}
