package com.shabinder.spotiflyer.utils

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.util.Log
import com.shabinder.spotiflyer.BuildConfig
import com.shabinder.spotiflyer.MainActivity


/*
* Only Log in Debug Mode
**/
fun log(tag:String? = "SpotiFlyer",message:String? = "null"){
    if (BuildConfig.DEBUG) {
        Log.d(tag ?: "spotiflyer", message ?: "null")
    }
}
fun MainActivity.requestStoragePermission() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        this.requestPermissions(
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            786
        )
    }
}

fun openPlatform(packageName:String, websiteAddress:String){
    val manager: PackageManager = mainActivity.packageManager
    try {
        val intent = manager.getLaunchIntentForPackage(packageName)
            ?: throw PackageManager.NameNotFoundException()
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        mainActivity.startActivity(intent)
    } catch (e: PackageManager.NameNotFoundException) {
        val uri: Uri =
            Uri.parse(websiteAddress)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        mainActivity.startActivity(intent)
    }
}

fun openPlatform(websiteAddress:String){
    val uri = Uri.parse(websiteAddress)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    mainActivity.startActivity(intent)
}