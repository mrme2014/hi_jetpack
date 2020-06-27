package org.devio.`as`.proj.hi_jetpack.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

object AppLifecycleOwner : LifecycleOwner {
    private val lifecycleRegistry = LifecycleRegistry(this)
    private var activityStartCount = 0
    private var front = true
    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    fun init(application: Application) {
        application.run {
            registerActivityLifecycleCallbacks(object : SimpleActivityLifecycleCallbacks() {
                override fun onActivityStopped(activity: Activity) {
                    activityStartCount--
                    if (activityStartCount <= 0 && front) {
                        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
                        front = false
                    }
                }

                override fun onActivityStarted(activity: Activity) {
                    activityStartCount++
                    if (activityStartCount > 0 && !front) {
                        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
                        front = true
                    }
                }
            })
        }
    }


    fun addObserver(lifecycleEventObserver: AppLifecycleObserver) {
        lifecycleRegistry.addObserver(lifecycleEventObserver)
    }


    abstract class AppLifecycleObserver : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            if (event == Lifecycle.Event.ON_STOP) {
                onAppStateChanged(false)
            } else if (event == Lifecycle.Event.ON_START) {
                onAppStateChanged(true)
            }
        }

        abstract fun onAppStateChanged(front: Boolean)

    }

    private open class SimpleActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {
        override fun onActivityPaused(activity: Activity) {

        }

        override fun onActivityStarted(activity: Activity) {

        }

        override fun onActivityDestroyed(activity: Activity) {

        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

        }

        override fun onActivityStopped(activity: Activity) {

        }

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

        }

        override fun onActivityResumed(activity: Activity) {

        }

    }

}