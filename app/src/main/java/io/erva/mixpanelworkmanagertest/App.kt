package io.erva.mixpanelworkmanagertest

import android.app.Application
import androidx.work.Configuration
import io.erva.mixpanelworkmanagertest.analytics.Analytics
import io.erva.mixpanelworkmanagertest.di.initializersModule
import io.erva.mixpanelworkmanagertest.sync.scheduleSyncTask
import java.util.concurrent.Executors
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application(), Configuration.Provider {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                initializersModule
            )
        }
        Analytics
        scheduleSyncTask(this)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setExecutor(Executors.newFixedThreadPool(1))
            .build()
    }
}
