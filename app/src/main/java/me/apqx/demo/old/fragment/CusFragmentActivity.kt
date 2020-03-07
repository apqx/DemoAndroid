package me.apqx.demo.old.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_fragment.*
import me.apqx.demo.R
import me.apqx.demo.view.tab.TabComponentFragment
import me.apqx.demo.view.tab.TabOtherFragment
import me.apqx.demo.view.tab.TabViewFragment

class CusFragmentActivity : FragmentActivity() {

    private lateinit var tabComponentFragment: TabComponentFragment
    private lateinit var tabOtherFragment: TabOtherFragment
    private lateinit var tabViewFragment: TabViewFragment

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
                R.id.tabViewFragment -> {
                    showFragView()
                    true
                }
                R.id.tabComponentFragment -> {
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
        if (!this::tabViewFragment.isInitialized) {
            tabViewFragment = TabViewFragment()
        }
        if (!tabViewFragment.isResumed)
            showFragByReplace(tabViewFragment)
    }

    private fun showFragComonent() {
        if (!this::tabComponentFragment.isInitialized) {
            tabComponentFragment = TabComponentFragment()
        }
        if (!tabComponentFragment.isResumed)
            showFragByReplace(tabComponentFragment)
    }

    private fun showFragOthers() {
        if (!this::tabOtherFragment.isInitialized) {
            tabOtherFragment = TabOtherFragment()
        }
        if (!tabOtherFragment.isResumed)
            showFragByReplace(tabOtherFragment)
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