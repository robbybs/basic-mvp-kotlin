package com.rbs.kotlinmvp.base

interface Presenter <V : View> {
    fun attachView(view: V)

    fun detachView()

    fun getView(): V?
}