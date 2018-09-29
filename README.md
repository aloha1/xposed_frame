# xposed_frame
This is a Http hook Module for Xposed Framework

# Features
With this module, we can get information about the networking application's behavior:
HTTP
Miscellaneous (URL.Parse());
Logcat (Using data/data/com.example.yunwen.myapplication.XModule/debug.log)

# Installation
Requirements: Xposed Framework, Xposed Installer
    adb install com.example.yunwen.myapplication.apk

How to uninstall
    adb uninstall com.example.yunwen.myapplication

# Why I left Activity in the module
To better inject the Xposed Module without searching in the framework;
Attempt to output the Log to the sqlite in this application. Would make progress if given more time.

# Given more time, what would you have worked on or what you change in your code/project? 
Catergorize the Log information;
Doing reverse engineering for the wiki app to better understand its structure;
Work with SSL pinning and organize the Hook methods for Managment of the network; 
Make use of the application to navigate to the app (wiki for example) which the user would like to monitor/hook.

