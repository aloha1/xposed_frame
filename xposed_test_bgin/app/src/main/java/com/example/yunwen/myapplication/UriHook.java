package com.example.yunwen.myapplication;

import android.net.Uri;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

/**
 * Created by yunwe on 9/25/2018.
 */
public class UriHook extends XC_MethodHook {

    // Log TAG of the Uri Hook Module
    public static final String TAG = "UriHook:";

    public static void initAllHooks(final XC_LoadPackage.LoadPackageParam loadPackageParam) {

        // Uri Hook Method
        findAndHookMethod(Uri.class, "parse", String.class, new XC_MethodHook() {

            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                if(param.args[0].toString().contains("http"))
                    XposedBridge.log(TAG + "URI: " + param.args[0] + "");
            }
        });

    }
}