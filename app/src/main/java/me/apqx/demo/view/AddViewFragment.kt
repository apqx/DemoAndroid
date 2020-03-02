package me.apqx.demo.view

import android.animation.AnimatorInflater
import android.animation.LayoutTransition
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.frag_add_view.*
import me.apqx.demo.R
import me.apqx.demo.mvp.BaseFragment
import me.apqx.demo.mvp.BasePresenter
import me.apqx.demo.mvp.IBaseView
import me.apqx.demo.widget.view.DisplayUtils

class AddViewFragment : BaseFragment<BasePresenter<IBaseView>>() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_add_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cv_1.setOnClickListener(this)
        cv_2.setOnClickListener(this)
        cv_3.setOnClickListener(this)

        btn_add_view.setOnClickListener(this)
        btn_delete_view.setOnClickListener(this)
        btn_show_view.setOnClickListener(this)

//        setLayoutAnimation()
        setLayoutTransition()
    }

    private fun setLayoutTransition() {
        val layoutTransition = LayoutTransition()
        val animator = AnimatorInflater.loadAnimator(context!!, R.animator.add_card)
        layoutTransition.setAnimator(LayoutTransition.APPEARING, animator)

        ll_add_view_container.layoutTransition = layoutTransition
    }

    private fun setLayoutAnimation() {
        val animation = AnimationUtils.loadAnimation(context!!, R.anim.add_card)
        val layoutAnimationController = LayoutAnimationController(animation)
        ll_add_view_container.layoutAnimation = layoutAnimationController
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_add_view -> {
                addView()
            }
            R.id.btn_delete_view -> {
                deleteView()
            }
            R.id.btn_show_view -> {
                showHideView()
            }
            else -> {
                v?.visibility = View.GONE
            }
        }
    }

    private fun showHideView() {
        for (i in 0 until ll_add_view_container.childCount) {
            val view = ll_add_view_container.getChildAt(i)
            if (view.visibility == View.GONE) {
                ll_add_view_container.getChildAt(i).visibility = View.VISIBLE
                break
            }
        }
    }

    private fun addView() {
        checkNotNull(context) { "Context is null" }
        val view = generateView()
        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtils.dpToPx(context!!, 180f))
        layoutParams.topMargin = DisplayUtils.dpToPx(context!!, 5f)
        layoutParams.bottomMargin = DisplayUtils.dpToPx(context!!, 5f)
        layoutParams.leftMargin = DisplayUtils.dpToPx(context!!, 10f)
        layoutParams.rightMargin = DisplayUtils.dpToPx(context!!, 10f)

        ll_add_view_container.addView(view, layoutParams)
    }

    private fun generateView(): View {
        val cardView = CardView(context!!)
        cardView.setOnClickListener(this)
        return cardView
    }

    private fun deleteView() {
        if (ll_add_view_container.childCount > 0)
            ll_add_view_container.removeViewAt(ll_add_view_container.childCount - 1)
    }
}