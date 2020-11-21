package me.assetnest.assetnest_frontend_android.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class UtilProvider {
    private static me.assetnest.assetnest_frontend_android.utils.SharedPreferenceUtil sharedPreferenceUtil;

    public static void initialize(Context context){
        initSharedPreferenceUtil(context);
    }

    public static void initSharedPreferenceUtil(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE);
        sharedPreferenceUtil = new me.assetnest.assetnest_frontend_android.utils.SharedPreferenceUtil(sharedPreferences);
    }

    public static me.assetnest.assetnest_frontend_android.utils.SharedPreferenceUtil getSharedPreferenceUtil() {
        return sharedPreferenceUtil;
    }
}
