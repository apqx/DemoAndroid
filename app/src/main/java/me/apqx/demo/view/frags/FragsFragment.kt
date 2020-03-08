package me.apqx.demo.view.frags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.frag_fragments.*
import me.apqx.demo.MainViewModel
import me.apqx.demo.R
import me.apqx.demo.mvp.BaseFragment
import me.apqx.demo.mvp.BasePresenter
import me.apqx.demo.mvp.IBaseView
import me.apqx.demo.old.tools.LogUtil

class FragsFragment : BaseFragment<BasePresenter<IBaseView>>() {
    private var flag = 1

    private val fragList = ArrayList<Fragment>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.frag_fragments, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_add_fragment.setOnClickListener(this)
        btn_hide_fragment.setOnClickListener(this)
        btn_replace_fragment.setOnClickListener(this)
        btn_show_fragment.setOnClickListener(this)
        btn_remove_fragment.setOnClickListener(this)

        LogUtil.d("FragsFragment fragList.size = ${fragList.size}")
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_add_fragment -> {
                fragList.add(addFragment())
            }
            R.id.btn_replace_fragment -> {
                replaceFragment()
            }
            R.id.btn_hide_fragment -> {
                hideFragment()
            }
            R.id.btn_show_fragment -> {
                showFragment()

            }
            R.id.btn_remove_fragment -> {
                removeFragment()
            }
        }
    }

    private fun replaceFragment() {
        childFragmentManager.beginTransaction()
                .replace(R.id.vg_frag_container, generateFragment())
                // 把当前的这个Fragment加入返回栈
                .addToBackStack("$flag")
                .commit()
    }

    private fun hideFragment() {
        if (fragList.size > 0) {
            childFragmentManager.beginTransaction()
                    .hide(fragList[fragList.size - 1])
                    .commitNow()
        }
    }

    private fun showFragment() {
        if (fragList.size > 0) {
            childFragmentManager.beginTransaction()
                    .show(fragList[fragList.size - 1])
                    .commitNow()
        }
    }

    private fun removeFragment() {

//        childFragmentManager.beginTransaction().apply {
//            // TODO 动画
//            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
//            if (fragList.size > 0) {
//                remove(fragList[fragList.size - 1])
//                addToBackStack("frag ${fragList.size}")
//                fragList.removeAt(fragList.size - 1)
//            }
//            val blackFragment = childFragmentManager.findFragmentById(R.id.frag_black)
//            LogUtil.d("blackFragment = $blackFragment")
//            if (blackFragment != null) {
//                remove(blackFragment)
//            }
//            commit()
//        }


        childFragmentManager.popBackStack()
    }

    private fun addFragment(): Fragment {
        val fragment = generateFragment()

        childFragmentManager.beginTransaction()
                // 设置动画类型
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(R.id.vg_frag_container, fragment)
                // 把当前的这个Fragment加入返回栈
                .addToBackStack("$flag")
                .commit()
        return fragment
    }


    private fun generateFragment(): Fragment {
        return ItemFragment.getInstance(flag++)
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}