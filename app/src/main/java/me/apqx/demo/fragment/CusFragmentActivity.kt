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
import me.apqx.demo.view.FragmentView

class CusFragmentActivity : FragmentActivity() {

    private lateinit var fragmentComponent: FragmentComponent
    private lateinit var fragmentOthers: FragmentOthers
    private lateinit var fragmentView: FragmentView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        initView()
        initListener()
    }

    private fun initView() {
        showFragView()
    }

    private fun initListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.view -> {
                    showFragView()
                    true
                }
                R.id.component -> {
                    showFragComonent()
                    true
                }
                else -> {
                    showFragOthers()
                    true
                }
            }
        }
    }

    private fun showFragView() {
        if (!this::fragmentView.isInitialized) {
            fragmentView = FragmentView()
        }
        if (!fragmentView.isResumed)
            showFragByReplace(fragmentView)
    }

    private fun showFragComonent() {
        if (!this::fragmentComponent.isInitialized) {
            fragmentComponent = FragmentComponent()
        }
        if (!fragmentComponent.isResumed)
            showFragByReplace(fragmentComponent)
    }

    private fun showFragOthers() {
        if (!this::fragmentOthers.isInitialized) {
            fragmentOthers = FragmentOthers()
        }
        if (!fragmentOthers.isResumed)
            showFragByReplace(fragmentOthers)
    }


    private fun showFragByReplace(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.frag_view, fragment)
//                .addToBackStack(null)
                .commit()
    }

    private fun showFragByAdd(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .add(R.id.frag_view, fragment)
                .commit()
    }
}