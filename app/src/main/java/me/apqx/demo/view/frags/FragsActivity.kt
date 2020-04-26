package me.apqx.demo.view.frags

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_frag.*
import me.apqx.demo.R
import me.apqx.demo.view.adapter.FragmentPagerAdapter

class FragsActivity : AppCompatActivity() {
    private val fragAdapter by lazy {
        FragmentPagerAdapter(supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frag)
        vp_frags.adapter = fragAdapter;
        tl_frags.setupWithViewPager(vp_frags)
    }
}