package io.erva.mixpanelworkmanagertest.di

import android.annotation.SuppressLint
import org.koin.dsl.module
import timber.log.Timber

val initializersModule = module {

    single(createdAtStart = true) {
        Timber.plant(
            object : Timber.DebugTree() {
                @SuppressLint("DefaultLocale")
                override fun createStackElementTag(element: StackTraceElement): String {
                    return "%% " +
                        "(${element.fileName}:${element.lineNumber})" +
                        "#${element.methodName} " +
                        "thread[${Thread.currentThread().name}]"
                }
            }
        )
    }
}
