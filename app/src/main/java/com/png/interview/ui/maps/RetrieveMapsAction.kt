package com.png.interview.ui.maps

import com.png.interview.api.HotsApi
import com.png.interview.api.common_model.action.operations.mapResult
import com.png.interview.api.models.heroes.Hero
import com.png.interview.api.common_model.action.operations.onErrorReturn
import com.png.interview.api.common_model.action.toActionResult
import com.png.interview.api.models.maps.HotsMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class RetrieveMapsAction
@Inject constructor(
    private val hotsApi: HotsApi
) {
    suspend fun getMaps(): List<HotsMap> =
        hotsApi.getAllMaps().toActionResult(resultConversion = {
            it
        })
            .onErrorReturn {
                emptyList()
            }.result
}