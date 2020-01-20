package me.apqx.demo.old.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_fragment.*
import me.apqx.demo.R
import me.apqx.demo.view.ComponentFragment
import me.apqx.demo.view.FragmentOthers
import me.apqx.demo.view.ViewFragment

class CusFragmentActivity : FragmentActivity() {

    private lateinit var componentFragment: ComponentFragment
    private lateinit var fragmentOthers: FragmentOthers
    private lateinit var viewFragment: ViewFragment

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
        if (!this::viewFragment.isInitialized) {
            viewFragment = ViewFragment()
        }
        if (!viewFragment.isResumed)
            showFragByReplace(viewFragment)
    }

    private fun showFragComonent() {
        if (!this::componentFragment.isInitialized) {
            componentFragment = ComponentFragment()
        }
        if (!componentFragment.isResumed)
            showFragByReplace(componentFragment)
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