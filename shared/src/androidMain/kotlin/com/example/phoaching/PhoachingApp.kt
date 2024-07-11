package com.example.phoaching

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.example.phoaching.di.KoinInjector
import com.example.phoaching.di.platformModule
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext


class PhoachingApp: Application() {
    override fun onCreate() {
        super.onCreate()
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        KoinInjector.koinApp.androidContext(this)
        KoinInjector.loadModules(listOf(platformModule))
        activityInject()
    }

    private fun activityInject() {
        this.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {

            override fun onActivityResumed(activity: Activity) {
                KoinInjector.koin.loadModules(listOf(module {
                    single { activity }
                }))
            }

            override fun onActivityPaused(activity: Activity) {
                KoinInjector.koin.unloadModules(listOf(module {
                    single { activity }
                }))
            }


            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

            override fun onActivityStarted(activity: Activity) {}

            override fun onActivityStopped(activity: Activity) {}

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

            override fun onActivityDestroyed(activity: Activity) {}


        })
    }
}