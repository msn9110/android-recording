package com.danielkim.soundrecorder;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;



public class MySharedPreferences {
    private static String PREF_HIGH_QUALITY = "pref_high_quality";
    private static String PREF_FTP_TRANSFER = "pref_ftp_transfer";
    private static String PREF_FTP_HOST = "pref_ftp_host";
    private static String PREF_FTP_DIR = "pref_ftp_dir";
    private static String PREF_FTP_USER = "pref_ftp_user";
    private static String PREF_FTP_PASSWD = "pref_ftp_passwd";


    public static void setPrefHighQuality(Context context, boolean isEnabled) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PREF_HIGH_QUALITY, isEnabled);
        editor.apply();
    }

    public static boolean getPrefHighQuality(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(PREF_HIGH_QUALITY, false);
    }

    public static void setPrefFTPTransfer(Context context, boolean isEnabled) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PREF_FTP_TRANSFER, isEnabled);
        editor.apply();
    }

    public static boolean getPrefFTPTransfer(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(PREF_FTP_TRANSFER, false);
    }

    public static void setPrefFTP(Context context, String key, String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getPrefFTP(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    public static void setPrefFTPSiteItem(Context context, FTPSiteItem item) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_FTP_HOST, item.host);
        editor.putString(PREF_FTP_DIR, item.remoteDir);
        editor.putString(PREF_FTP_USER, item.username);
        editor.putString(PREF_FTP_PASSWD, item.password);
        editor.apply();
    }

    public static FTPSiteItem getPrefFTPSiteItem(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String host, dir, user, passwd;
        host = preferences.getString(PREF_FTP_HOST, null);
        dir = preferences.getString(PREF_FTP_DIR, null);
        user = preferences.getString(PREF_FTP_USER, null);
        passwd = preferences.getString(PREF_FTP_PASSWD, null);
        if (host != null && dir != null && user != null && passwd != null)
            return new FTPSiteItem(host, dir, user, passwd);
        return null;
    }
}
