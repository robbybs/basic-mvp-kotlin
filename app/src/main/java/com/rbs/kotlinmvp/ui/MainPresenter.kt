package com.rbs.kotlinmvp.ui

import com.rbs.kotlinmvp.base.BasePresenter
import com.rbs.kotlinmvp.models.MealsItem
import com.rbs.kotlinmvp.models.MealsResponse
import com.rbs.kotlinmvp.utils.ApiRequest
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter : BasePresenter<MainView>() {
    private lateinit var service: ApiRequest
    private lateinit var listMeals: List<MealsItem?>

    fun initialization() {
        if (isViewAttached()) getView()?.setService()
//      getMealsData() --> use this to get data just with retrofit
//      use code below to get data with retrofit + rx
        getObservableMeals().subscribe(getObserverMeals())
    }

    fun setService(service: ApiRequest) {
        this.service = service
    }

    private fun getMeals() = service.getMeals()

    private fun getObservableMeals() = service.getMealsRx().subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())

    private fun getObserverMeals(): Observer<MealsResponse> = object :
        DisposableObserver<MealsResponse>() {

        override fun onNext(response: MealsResponse) {
            listMeals = response.meals as List<MealsItem?>
        }

        override fun onError(e: Throwable) {
            if (isViewAttached()) getView()?.setErrorMessage(e.message.toString())
        }

        override fun onComplete() {
            if (isViewAttached()) getView()?.setData(listMeals)
        }
    }

    private fun getMealsData() {
        getMeals().enqueue(object : Callback<MealsResponse> {
            override fun onFailure(call: Call<MealsResponse>, t: Throwable) {
                if (isViewAttached()) getView()?.setErrorMessage(t.message.toString())
            }

            override fun onResponse(call: Call<MealsResponse>, response: Response<MealsResponse>) {
                if (response.body() != null) {
                    if (isViewAttached()) getView()?.setData(response.body()!!.meals)
                }
            }
        })
    }
}