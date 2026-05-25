package com.example.ridhoapps.data.api

import com.example.ridhoapps.data.model.CatFactModel
import retrofit2.http.GET

interface CatFactApiService {
    @GET("fact")
    suspend fun getCatFact(): CatFactModel
}