package me.apqx.demo.view.tab

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.frag_component.*
import me.apqx.demo.MainActivity
import me.apqx.demo.R
import me.apqx.demo.mvp.BaseFragment
import me.apqx.demo.mvp.BasePresenter
import me.apqx.demo.mvp.IBaseView
import me.apqx.demo.view.DrawActivity
import me.apqx.demo.view.ScrollActivity
import java.lang.ref.SoftReference

class TabComponentFragment: BaseFragment<BasePresenter<IBaseView>>() {

    private lateinit var viewSoftReference: SoftReference<View>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        if (this::viewSoftReference.isInitialized && viewSoftReference.get() != null) {
//            return viewSoftReference.get()
//        }
        val itemView = inflater.inflate(R.layout.frag_component, container, false)
        viewSoftReference = SoftReference(itemView)
        return itemView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_recycler.setOnClickListener(this)
        btn_notification.setOnClickListener(this)
        btn_mvvm.setOnClickListener(this)
        btn_view_pager.setOnClickListener(this)
        btn_img.setOnClickListener(this)
        btn_draw.setOnClickListener(this)
        btn_scroll.setOnClickListener(this)
    }



    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_recycler -> {
                findNavController().navigate(TabComponentFragmentDirections.actionTabComponentFragmentToRecyclerFragment())
            }
            R.id.btn_notification -> {
                findNavController().navigate(TabComponentFragmentDirections.actionTabComponentFragmentToNotificationFragment())
            }
            R.id.btn_mvvm -> {
                findNavController().navigate(TabComponentFragmentDirections.actionTabComponentFragmentToMvvmFragment())
            }
            R.id.btn_view_pager -> {
                findNavController().navigate(TabComponentFragmentDirections.actionTabComponentFragmentToViewPagerFragment())
            }
            R.id.btn_img -> {
                findNavController().navigate(TabComponentFragmentDirections.actionTabComponentFragmentToImgFragment())
            }
            R.id.btn_draw -> {
                startActivity(Intent(context, DrawActivity::class.java))
            }
            R.id.btn_scroll -> {
                startActivity(Intent(context, ScrollActivity::class.java))
            }
        }
    }
}