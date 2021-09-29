package no.shortcut.fitnessapp.common

import no.shortcut.fitnessapp.common.service.*
import org.koin.dsl.module

val commonModule = module {
    single<RemoteConfig> {
        FirebaseRemoteConfigRepository()
    }
    single<PreferenceService> { DataStorePreferences(get()) }
    single<AnalyticsService> { FirebaseAnalyticsService(get()) }
    single<ReviewService> { GoogleReviewService(get()) }
}
