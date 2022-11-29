package io.erva.mixpanelworkmanagertest.analytics

import android.content.Context
import com.mixpanel.android.mpmetrics.MixpanelAPI
import org.json.JSONObject

class MixpanelAnalytics(context: Context) : Tracker {

    private val mixpanel = MixpanelAPI.getInstance(
        context,
        ""
    )

    override fun sendEvent(event: String) {
        mixpanel.track(event)
    }

    override fun sendEvent(event: String, key: String, value: String) {
        mixpanel.track(
            event,
            JSONObject().apply {
                put(key, value)
            }
        )
    }
}
