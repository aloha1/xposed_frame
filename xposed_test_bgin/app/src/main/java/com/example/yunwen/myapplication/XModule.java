package com.example.yunwen.myapplication;

import android.util.Log;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import okhttp3.OkHttpClient;

import static de.robv.android.xposed.XposedHelpers.findAndHookConstructor;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.setObjectField;

/**
 * Created by yunwe on 9/25/2018.
 */
public class XModule extends XC_MethodHook implements IXposedHookLoadPackage {

    private static final String TAG = "XModuleLog";
    private
    static final String ERROR = "XModule_Error";
    String currentPackageName = "";

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        currentPackageName = loadPackageParam.packageName;

        HttpHook.initAllHooks(loadPackageParam);

    }

    public static void logError(Error e){
        XposedBridge.log(XModule.ERROR + " " + e.getMessage());
    }
}
