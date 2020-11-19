package com.cakrasuryainti.panther

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import io.sentry.Sentry
import io.sentry.SentryLevel
import timber.log.Timber

@HiltAndroidApp
class PantherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }

    private class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }

            when (priority) {
                Log.INFO -> Sentry.captureMessage(message, SentryLevel.INFO)
                Log.WARN -> Sentry.captureMessage(message, SentryLevel.WARNING)
            }


            if (t != null) {
                if (priority == Log.ERROR) {
                    Sentry.captureException(t)
                } else if (priority == Log.WARN) {
                    Sentry.captureMessage(t.message ?: "", SentryLevel.WARNING)
                }
            }
        }

    }
}
