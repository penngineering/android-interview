package com.png.interview.ui.maps

import androidx.lifecycle.ViewModel
import com.png.interview.ui.heroes.RetrieveHeroesAction
import javax.inject.Inject

class MapsViewModel
@Inject constructor(private val mapsAction: RetrieveMapsAction): ViewModel() {
    suspend fun getMaps() = mapsAction.getMaps()
}
