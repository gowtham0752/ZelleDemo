package com.taxi.zelledemo

import android.app.Application

class MyClass: Application() {

    private var sInstance: MyClass? = null

    override fun onCreate() {
        super.onCreate()
        // Setup singleton instance
        sInstance = this
    }

    // Getter to access Singleton instance
    fun getInstance(): MyClass? {
        return sInstance
    }
}