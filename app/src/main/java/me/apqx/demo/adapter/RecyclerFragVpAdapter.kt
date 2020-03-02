package me.apqx.demo.adapter

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class RecyclerFragVpAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    private val list = ArrayList<Item>()


    override fun getItem(position: Int): Fragment {
        return list[position].fragment
    }

    override fun destroyItem(container: View, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list[position].title
    }

    fun setData(list: java.util.ArrayList<Item>) {
        this.list.clear()
        this.list.addAll(list)
    }

    public class Item(val title: String, val fragment: Fragment) {
        override fun toString(): String {
            return title
        }
    }
}