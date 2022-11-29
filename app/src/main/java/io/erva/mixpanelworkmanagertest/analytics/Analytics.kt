package io.erva.mixpanelworkmanagertest.analytics

import android.app.Application
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import timber.log.Timber

object Analytics : Tracker, KoinComponent {

    private val application: Application by inject()
    private val trackers = if (true) {
        listOf(MixpanelAnalytics(application))
    } else {
        emptyList()
    }

    override fun sendEvent(event: String) {
        Timber.d(event)
        trackers.forEach { it.sendEvent(event) }
    }

    override fun sendEvent(event: String, key: String, value: String) {
        Timber.d("$event [$key:$value]")
        trackers.forEach { it.sendEvent(event, key, value) }
    }
}

object Event {
    const val EVENT_HI_CLICKED = "hi_clicked"
    const val EVENT_BACKGROUND_SYNC = "background_sync"
}
