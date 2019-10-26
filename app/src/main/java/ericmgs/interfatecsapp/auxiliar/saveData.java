package ericmgs.interfatecsapp.auxiliar;

import android.content.Context;
import android.content.SharedPreferences;

import java.sql.Time;

import ericmgs.interfatecsapp.R;

public class saveData {
    private Context context;

    public saveData(Context context) {
        this.context = context;
    }

    public void setData(int key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(key),
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(key), value);
        editor.apply();
    }

    public void setData(int key, Time value) {
        long millis = value.getTime();

        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(key),
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(context.getString(key), millis);
        editor.apply();
    }

    public void setData(int key, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(key),
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(key), value);
        editor.apply();
    }

    public void setData(int key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(key),
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(context.getString(key), value);
        editor.apply();
    }

    public String getStringData(int key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(key),
                Context.MODE_PRIVATE);
        String result = sharedPreferences.getString(context.getString(R.string.pref_status), "");

        return result;
    }


    public Time getTimeData(int key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(key),
                Context.MODE_PRIVATE);
        Long result = sharedPreferences.getLong(context.getString(R.string.pref_remaining_time), 0);
        Time time = new Time(result);
        return time;
    }

    public int getIntData(int key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(key),
                Context.MODE_PRIVATE);
        int result = sharedPreferences.getInt(context.getString(R.string.pref_status), 0);
        return result;
    }

    public boolean getBoolData(int key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(key),
                Context.MODE_PRIVATE);
        boolean result = sharedPreferences.getBoolean(context.getString(R.string.pref_logged), false);

        return result;
    }
}
