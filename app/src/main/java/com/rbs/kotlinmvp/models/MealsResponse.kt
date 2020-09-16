package com.rbs.kotlinmvp.models

import com.google.gson.annotations.SerializedName

data class MealsResponse (
    @field:SerializedName("meals")
    val meals: List<MealsItem?>? = null
)