package com.rbs.kotlinmvp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.rbs.kotlinmvp.R
import com.rbs.kotlinmvp.models.MealsItem
import com.rbs.kotlinmvp.utils.RetrofitRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializePresenter()
    }

    override fun initializePresenter() {
        presenter = MainPresenter()
        presenter.attachView(this)
        presenter.initialization()
    }

    override fun setService() {
        val service = RetrofitRequest.getService()
        presenter.setService(service)
    }

    override fun setErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun setData(data: List<MealsItem?>?) {
        rv_meals.setHasFixedSize(true)
        rv_meals.layoutManager = GridLayoutManager(this, 2)
        rv_meals.adapter = MainAdapter(data, this)
    }
}