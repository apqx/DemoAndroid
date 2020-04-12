package me.apqx.demo.view.web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.apqx.demo.R
import me.apqx.demo.mvp.BaseFragment
import me.apqx.demo.mvp.BasePresenter
import me.apqx.demo.mvp.IBaseView
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class WebFragment : BaseFragment<BasePresenter<IBaseView>>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_web, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRetrofit()
    }

    private fun initRetrofit() {
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
    }

    override fun onClick(p0: View?) {

    }
}