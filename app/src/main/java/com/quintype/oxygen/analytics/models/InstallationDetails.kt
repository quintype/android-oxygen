package com.quintype.oxygen.analytics.models

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings


class InstallationDetails(val context: Context) {
    companion object {
        const val DEVICE_TYPE = "Phone"
        fun newInstance(context: Context): InstallationDetails {
            return InstallationDetails(context)
        }
    }

    var platform:String="android"
    var osVersionCode: Int = 0
    var appVersionName: String? = null
    var appVersionCode: Int = 0
    var deviceId: String? = null
    var deviceName: String? = null
    var deviceType: String? = null
    var deviceManufacturer: String? = null
    private var appInstallVersionName: String? = null
    private var appInstallVersionCode: Int = 0

    init {
        osVersionCode = Build.VERSION.SDK_INT

        val pInfo: PackageInfo?
        try {
            pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            this.appInstallVersionCode = pInfo.versionCode
            this.appInstallVersionName = pInfo.versionName
            this.appVersionCode = this.appInstallVersionCode
            this.appVersionName = this.appInstallVersionName
            this.deviceName = Build.MODEL
            this.deviceType = DEVICE_TYPE
            this.deviceManufacturer = Build.MANUFACTURER
        } catch (e: PackageManager.NameNotFoundException) {
            //this should not occur
        }
        this.deviceId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }
}