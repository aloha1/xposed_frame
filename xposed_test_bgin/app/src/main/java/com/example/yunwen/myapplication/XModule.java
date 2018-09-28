package com.example.yunwen.myapplication;


import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


/**
 * Created by yunwe on 9/25/2018.
 */
public class XModule extends XC_MethodHook implements IXposedHookLoadPackage {

    private static final String TAG = "XModuleLog";
    static final String ERROR = "XModule_Error";

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        HttpHook.initAllHooks(loadPackageParam);
    }

    public static void logError(Error e){
        XposedBridge.log(XModule.ERROR + " " + e.getMessage());
    }

}
