package com.example.yunwen.myapplication;

import android.graphics.Color;
import android.os.Build;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
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
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedBridge.hookAllConstructors;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.getObjectField;

/**
 * Created by yunwe on 9/25/2018.
 */

public class HttpHook extends XC_MethodHook {

    public static final String TAG = "HttpHook:";

    public static void initAllHooks(final XC_LoadPackage.LoadPackageParam loadPackageParam) {

//        try {
//            final Class<?> httpUrlConnection = findClass("java.net.HttpURLConnection", loadPackageParam.classLoader);
//            hookAllConstructors(httpUrlConnection, new XC_MethodHook() {
//
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//
//                    if (param.args.length != 1 || param.args[0].getClass() != URL.class) {
//                        return;
//                    }
//
//                    XposedBridge.log(TAG + "HttpURLConnection: " + param.args[0] + "");
//                }
//            });
//        } catch (Error e) {
//            XModule.logError(e);
//        }
//

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

                    XposedBridge.log(TAG + "RESPONSE: method=" + urlConn.getRequestMethod() + " " +
                            "URL=" + urlConn.getURL().toString() + " " +
                            "Params=" + sb.toString());
                }

            }
        };


        try {
            final Class<?> okHttpClient = findClass("com.squareup.okhttp3.OkHttpClient", loadPackageParam.classLoader);
            if(okHttpClient != null) {

                findAndHookMethod(okHttpClient, "open", URL.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                        URL url = null;
                        if (param.args[0] != null)
                            url = (URL) param.args[0];
                        XposedBridge.log(TAG + "OkHttpClient URL: " + url.toString() + "");
                    }
                });
            }
        } catch (Error e) {
            XModule.logError(e);
        }

        try {
            final Class<?> okHttpClient = findClass("com.android.okhttp.Request", loadPackageParam.classLoader);
            if(okHttpClient != null) {
//                findAndHookMethod(okHttpClient, "open", URI.class, new XC_MethodHook() {
//                    @Override
//                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//
//                        URI uri = null;
//                        if (param.args[0] != null)
//                            uri = (URI) param.args[0];
//                        XposedBridge.log(TAG + "OkHttpClient URI: " + uri.toString() + "");
//                    }
//                });

                findAndHookMethod(okHttpClient, "url", URL.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                        URL url = null;
                        if (param.args[0] != null)
                            url = (URL) param.args[0];
                        XposedBridge.log(TAG + "Request URL: " + url.toString() + "");
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
                //com.squareup.okhttp.internal.http.HttpURLConnectionImpl
                final Class<?> httpURLConnectionImpl = findClass("com.android.okhttp.internal.http.HttpURLConnectionImpl", loadPackageParam.classLoader);
                if(httpURLConnectionImpl != null) {
                    findAndHookMethod("com.android.okhttp.internal.http.HttpURLConnectionImpl", loadPackageParam.classLoader, "getOutputStream", ResponseHook);
                    findAndHookMethod("com.android.okhttp.internal.http.HttpURLConnectionImpl", loadPackageParam.classLoader, "getInputStream", ResponseHook);
                }
            }
        } catch (Error e) {
            XModule.logError(e);
        }

        findAndHookMethod(SSLContext.class, "init",
                KeyManager[].class, TrustManager[].class, SecureRandom.class, new XC_MethodHook() {

                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        KeyManager[] km = (KeyManager[]) param.args[0];
                        TrustManager[] tm_ = (TrustManager[]) param.args[1];

                        if (tm_ != null && tm_[0] != null) {
                            X509TrustManager tm = (X509TrustManager) tm_[0];
                            X509Certificate[] chain = new X509Certificate[]{};

                            XposedBridge.log(TAG + "Possible pinning.");
                            boolean check = false;
                        }
                    }
                });
    }

    private URI rewriteUriToRunscopeUri(URI sourceUrl, String runscopeSlug, String methodHint) throws URISyntaxException {
        String host = sourceUrl.getHost();
        String newHost = host.replaceAll("-", "--").replaceAll("\\.", "-") + String.format("-%s.runscope.net", runscopeSlug);
        String newUrl = sourceUrl.toString().replace(host, newHost);

        if (host.contains("runscope.net")) {
            return null;
        }

        XposedBridge.log(String.format("About to rewrite '%s' => '%s (%s)'", sourceUrl.toString(), newUrl, methodHint));
        return new URI(newUrl);
    }
}
