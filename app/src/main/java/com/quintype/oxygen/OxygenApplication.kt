package com.quintype.oxygen

import android.app.Application
import android.content.Context

class OxygenApplication : Application() {
    companion object {
        var mInstance: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this.applicationContext
    }
}