package me.apqx.demo.fragment

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_fragment.*
import me.apqx.demo.R
import me.apqx.demo.view.FragmentComponent
import me.apqx.demo.view.FragmentOthers

class FragmentActivity: FragmentActivity() {

    private lateinit var fragmentComponent: FragmentComponent
    private lateinit var fragmentOthers: FragmentOthers

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_fragment)
        initView()
        initListener()
    }

    private fun initView() {

    }

    private fun initListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.view -> {
                    true
                }
                R.id.component -> {
                    true
                }
                else -> {
                    true
                }
            }
        }
    }

    private fun showFragView() {

    }

    private fun showFragComonent() {

    }

    private fun showFragOthers() {

    }


    private fun showFragByReplace(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.frag_view, fragment)
                .commit()
    }

    private fun showFragByAdd(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .add(R.id.frag_view, fragment)
                .commit()
    }
}