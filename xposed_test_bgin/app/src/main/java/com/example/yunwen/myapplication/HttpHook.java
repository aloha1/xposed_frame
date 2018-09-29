package com.example.yunwen.myapplication;


import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import com.example.yunwen.myapplication.dao.XModuleLog;
import com.example.yunwen.myapplication.dao.XModuleLogRepo;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;

/**
 * Created by yunwe on 9/25/2018.
 */

public class HttpHook extends XC_MethodHook {

    // Log TAG of the Http Hook Module
    public static final String TAG = "HttpHook:";

    public static void initAllHooks(final XC_LoadPackage.LoadPackageParam loadPackageParam) {
        // Http Response Hook Method
        XC_MethodHook ResponseHook = new XC_MethodHook() {

            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                HttpURLConnection urlConn = (HttpURLConnection) param.thisObject;

                if (urlConn != null) {
                    StringBuilder sb = new StringBuilder();
                    int code = urlConn.getResponseCode();
                    if(code==200){

                        Map<String, List<String>> properties = urlConn.getHeaderFields();
                        if (properties != null && properties.size() > 0) {

                            for (Map.Entry<String, List<String>> entry : properties.entrySet()) {
                                sb.append(entry.getKey() + ": " + entry.getValue() + ", ");
                            }
                        }
                    }

                    //Log Response in Full format of URL
                    XposedBridge.log(TAG + "RESPONSE: method=" + urlConn.getRequestMethod() + " " +
                            "URL=" + urlConn.getURL().toString() + " " +
                            "Params=" + sb.toString());
                }
            }
        };

        // OkHttp Request Hook Method
        try {
            final Class<?> okHttpClient = findClass("com.android.okhttp.Request", loadPackageParam.classLoader);
            if(okHttpClient != null) {
                findAndHookMethod(okHttpClient, "open", URI.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        URI uri = null;
                        if (param.args[0] != null)
                            uri = (URI) param.args[0];
                        XposedBridge.log(TAG + "OkHttpClient URI: " + uri.toString() + "");
                    }
                });

            }
        } catch (Error e) {
            XModule.logError(e);
        }
        try {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
                findAndHookMethod("libcore.net.http.HttpURLConnectionImpl", loadPackageParam.classLoader, "getOutputStream", ResponseHook);
            } else {
                final Class<?> httpURLConnectionImpl = findClass("com.android.okhttp.internal.http.HttpURLConnectionImpl", loadPackageParam.classLoader);
                if(httpURLConnectionImpl != null) {
                    findAndHookMethod("com.android.okhttp.internal.http.HttpURLConnectionImpl", loadPackageParam.classLoader, "getOutputStream", ResponseHook);
                    findAndHookMethod("com.android.okhttp.internal.http.HttpURLConnectionImpl", loadPackageParam.classLoader, "getInputStream", ResponseHook);
                }
            }
        } catch (Error e) {
            XModule.logError(e);
        }
    }
}
