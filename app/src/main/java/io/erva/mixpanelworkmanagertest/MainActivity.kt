package io.erva.mixpanelworkmanagertest

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import io.erva.mixpanelworkmanagertest.analytics.Analytics
import io.erva.mixpanelworkmanagertest.analytics.Event

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button_hi).setOnClickListener {
            Analytics.sendEvent(Event.EVENT_HI_CLICKED)
        }
    }
}
