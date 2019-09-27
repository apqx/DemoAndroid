package me.apqx.demo.widget.view

import android.app.Activity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.layout_activity_relative.*
import me.apqx.demo.R

class RelativeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_relative)
    }

    public fun onClick(view: View) {
        when (view.id) {
            R.id.btn_relative -> {
                btn_2.visibility = if (btn_2.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            }
            R.id.btn_constraint -> {
                btn_c2.visibility = if (btn_c2.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            }
        }
    }
}