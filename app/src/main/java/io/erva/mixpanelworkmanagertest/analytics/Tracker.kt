package io.erva.mixpanelworkmanagertest.analytics

interface Tracker {

    fun sendEvent(event: String)

    fun sendEvent(event: String, key: String, value: String)
}
