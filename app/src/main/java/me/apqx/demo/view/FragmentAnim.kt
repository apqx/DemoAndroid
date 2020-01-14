package me.apqx.demo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.frag_anim.*
import me.apqx.demo.R

class FragmentAnim: BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_anim, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_start_anim.setOnClickListener(this)
        btn_stop_anim.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_start_anim -> {
                startAnimation()
            }
            R.id.btn_stop_anim -> {
                stopAnim()
            }
        }
    }

    /**
     * 视图动画
     */
    private fun startAnimation() {
//        val animation = RotateAnimation(0f, 180f, RotateAnimation.RELATIVE_TO_SELF, 1F, RotateAnimation.RELATIVE_TO_SELF, 0.5F)
//        val animation = TranslateAnimation(0f, 0f, 0f, -600f)
//        val animation = ScaleAnimation(1f, 1f, 1f, -1f)
        val animation = ScaleAnimation(1f, 1f, 1f, -1f, tv_target.pivotX, tv_target.pivotY)

        animation.duration = 1000
        animation.fillAfter = true
        animation.repeatMode = Animation.REVERSE
        animation.repeatCount = Animation.INFINITE
        animation.interpolator = AnticipateInterpolator()
        animation.interpolator = AccelerateInterpolator(1f)
        animation.interpolator = OvershootInterpolator()

        tv_target.startAnimation(animation)
    }

    /**
     * 属性动画
     */
    private fun startAnimator() {

    }

    private fun stopAnim() {
        tv_target.clearAnimation()
    }
}