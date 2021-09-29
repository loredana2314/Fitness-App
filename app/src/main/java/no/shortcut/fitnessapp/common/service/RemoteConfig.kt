package no.shortcut.fitnessapp.common.service

interface RemoteConfig {

    suspend fun init(): RemoteConfig

    fun isVersionLocked(): Boolean
}
