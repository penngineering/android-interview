package com.png.interview.ui.maps

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.png.interview.R
import com.png.interview.api.models.heroes.Hero
import com.png.interview.api.models.maps.HotsMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class MapItemViewBinder
@Inject constructor(
    private val activity: AppCompatActivity
) {

    private var map: HotsMap? = null

    fun bind(item: HotsMap?) {
        if (item != null) {
            this.map = item
        }
    }

    val name
        get() = map?.name
}
