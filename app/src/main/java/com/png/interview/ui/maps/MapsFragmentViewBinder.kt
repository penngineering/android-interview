package com.png.interview.ui.maps

import androidx.databinding.BaseObservable
import com.png.interview.api.models.maps.HotsMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class MapsFragmentViewBinder
@Inject constructor(
    val adapter: MapsAdapter
) : BaseObservable() {
    fun bind(it: List<HotsMap>) {
        adapter.bindData(it)
    }
}