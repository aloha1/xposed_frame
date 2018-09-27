package com.example.yunwen.myapplication;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by yunwe on 9/25/2018.
 */

public class XModule implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("load app:"+lpparam.packageName);
        if (lpparam.packageName.equals("com.example.yunwen.myapplication")) {
            XposedBridge.log("...........find it............");
        }
    }
}
