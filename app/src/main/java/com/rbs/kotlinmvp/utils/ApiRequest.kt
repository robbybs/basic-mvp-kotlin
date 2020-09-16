package com.rbs.kotlinmvp.utils

import com.rbs.kotlinmvp.models.MealsResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {
    @GET("filter.php?c=Seafood")
    fun getMeals(): Call<MealsResponse>

    @GET("filter.php?c=Seafood")
    fun getMealsRx(): Observable<MealsResponse>

    @GET("lookup.php")
    fun getMealsById(@Query("i") id: String): Call<MealsResponse>
}