package com.png.interview.api

import com.png.interview.api.common_model.ErrorResponse
import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.api.models.heroes.Hero
import com.png.interview.api.models.maps.HotsMap
import retrofit2.http.GET
import retrofit2.http.Path

interface HotsApi {
    @GET("heroes/")
    suspend fun getAllHeroes(): NetworkResponse<List<Hero>, ErrorResponse>

    @GET("heroes/{name}")
    suspend fun getHero(@Path("name") name: String): NetworkResponse<Hero, ErrorResponse>

    @GET("maps/")
    suspend fun getAllMaps(): NetworkResponse<List<HotsMap>, ErrorResponse>
}