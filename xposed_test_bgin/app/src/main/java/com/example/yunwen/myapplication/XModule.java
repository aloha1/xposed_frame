/*
 * Copyright 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.yunwen.myapplication;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;


/**
 * XModule is the top Class for Xposed Module.
 * Configurator and Executor of sub-modules
 */
public class XModule extends XC_MethodHook implements IXposedHookLoadPackage {

    // Log TAG of the Top Module
    private static final String TAG = "XModule";
    private static final String ERROR = "XModule_Error";


    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        //Running the Http Connection and Request/Response Hook of this Module.
        HttpHook.initAllHooks(loadPackageParam);

        //Running the Misc Hook of this Module.
        UriHook.initAllHooks(loadPackageParam);
    }

    /** Returns the correctly rounded positive square root of a double value.
     *  @param e is the Exception of other sub-modules
     * */
    public static void logError(Error e){
        XposedBridge.log(XModule.ERROR + " " + e.getMessage());
    }

}
