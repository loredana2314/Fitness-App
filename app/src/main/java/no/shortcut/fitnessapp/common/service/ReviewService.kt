package no.shortcut.fitnessapp.common.service

import android.app.Activity

interface ReviewService {
    fun requestReview(activity: Activity, onCompleteCallback: ((Boolean) -> Unit)?)
}
