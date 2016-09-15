package com.withhari.trafficfinesnepal.extras;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A helper
 * Created by Hari Lamichhane on 9/15/2016.
 */

public class Helper {

    public static boolean isFirstRun(Context c) {
        return getPrefs(c).getBoolean("first_run", true);
    }

    public static void setFirstRun(Context c, boolean b) {
        getPrefs(c).edit().putBoolean("first_run", b).commit();
    }

    public static SharedPreferences getPrefs(Context c) {
        return c.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
    }

    public static String loadData(Context activity, String s) {
        StringBuilder out = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    activity.getAssets().open(s)
            ));
            String line;
            while ((line = br.readLine()) != null) {
                out.append(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }


    public static boolean isConnected(Context context) {
        ConnectivityManager m = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return m.getActiveNetworkInfo() != null && m.getActiveNetworkInfo().isConnectedOrConnecting();
    }


}
