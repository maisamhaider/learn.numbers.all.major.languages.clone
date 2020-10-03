package learn.numbers.all.major.languages.clone.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import learn.numbers.all.major.languages.clone.annotations.MyAnno;


public class Pref {
    Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public Pref(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setData(String data, String sKey) {
        editor.putString(sKey, data).commit();
    }

    public void setData(boolean data, String key) {
        editor.putBoolean(key, data).commit();
    }

    public String getStringData(String sKey,boolean translator) {
        if (translator)
        {
            return  preferences.getString(sKey, "en");
        }
        else return preferences.getString(sKey, MyAnno.English);
    }


    public boolean getBooleanData(String key) {
        return preferences.getBoolean(key, false);
    }

}
