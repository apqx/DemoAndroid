package me.apqx.demo.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import me.apqx.demo.view.frags.ItemFragment

/**
 * 只有当前的Fragment会进入Resumed状态
 */
class FragmentPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager
        , BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return ItemFragment.newInstance(position)
    }

    override fun getCount(): Int {
        return 10;
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "TAB$position"
    }
}