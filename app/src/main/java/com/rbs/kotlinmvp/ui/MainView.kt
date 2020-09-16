package com.rbs.kotlinmvp.ui

import com.rbs.kotlinmvp.base.View
import com.rbs.kotlinmvp.models.MealsItem

interface MainView : View {
    fun setService()

    fun setErrorMessage(message: String)

    fun setData(data: List<MealsItem?>?)
}