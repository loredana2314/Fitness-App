package no.shortcut.fitnessapp.common.databinding

import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter
import no.shortcut.fitnessapp.common.extensions.onFinishedEditing

object ViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("visibleOrGone")
    fun setVisibleOrGone(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("onFinishEditing")
    fun setOnFinishEditing(view: EditText, onFinishEditing: () -> Unit) {
        view.onFinishedEditing(onFinishEditing)
    }
}
