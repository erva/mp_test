package io.erva.mixpanelworkmanagertest.sync

import android.content.Context
import androidx.work.*
import io.erva.mixpanelworkmanagertest.analytics.Analytics
import io.erva.mixpanelworkmanagertest.analytics.Event
import org.koin.core.component.KoinComponent
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SyncWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams), KoinComponent {

    override fun doWork(): Result {
        Timber.d("Start sync work")
        Analytics.sendEvent(Event.EVENT_BACKGROUND_SYNC)
        return Result.success()
    }
}

fun scheduleSyncTask(context: Context) {
    val syncWorkName = "SyncWorkName"
    val constraints = Constraints.Builder().apply {
        setRequiredNetworkType(NetworkType.CONNECTED)
    }.build()
    val repeatingWork = PeriodicWorkRequestBuilder<SyncWorker>(
        repeatInterval = PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS,
        repeatIntervalTimeUnit = TimeUnit.MILLISECONDS,
        flexTimeInterval = PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS,
        flexTimeIntervalUnit = TimeUnit.MILLISECONDS
    ).setConstraints(constraints)
        .build()
    Timber.d("Schedule sync work")
    WorkManager.getInstance(context)
        .enqueueUniquePeriodicWork(syncWorkName, ExistingPeriodicWorkPolicy.KEEP, repeatingWork)
}
