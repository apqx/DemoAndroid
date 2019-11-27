package me.apqx.demo.widget.recycler.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.apqx.demo.widget.recycler.bean.DATA_TYPE_HORIZONTAL
import me.apqx.demo.widget.recycler.bean.DATA_TYPE_NORMAL
import me.apqx.demo.widget.recycler.bean.Man

class ManViewModel : ViewModel() {

    private val list: MutableList<Man> = ArrayList()
    val manLiveData = MutableLiveData<MutableList<Man>>()

    init {
        manLiveData.value = list
    }

    fun refreshNew() {

        for (i in 0 until 10) {
//            val type = if (i % 10 == 0) DATA_TYPE_HORIZONTAL else DATA_TYPE_NORMAL
            val type =  DATA_TYPE_NORMAL
            list.add(Man("${list.size + 1}", "", type))
        }
        manLiveData.value = list
    }
}