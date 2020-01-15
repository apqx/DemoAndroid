package me.apqx.demo.view

import android.animation.*
import android.graphics.Color
import android.graphics.Path
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.frag_anim.*
import me.apqx.demo.R

class FragmentAnim : BaseFragment() {
    private lateinit var objectAnimator: ObjectAnimator
    private lateinit var animation: Animation

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_anim, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_start_anim.setOnClickListener(this)
        btn_stop_anim.setOnClickListener(this)

        val argbEvaluator = ArgbEvaluator()
        tv_target.setBackgroundColor(argbEvaluator.evaluate(0.5f, Color.RED, Color.GREEN) as Int)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_start_anim -> {
//                startAnimation()
                startAnimator()
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
        animation = RotateAnimation(0f, 180f, RotateAnimation.RELATIVE_TO_SELF, 1F, RotateAnimation.RELATIVE_TO_SELF, 0.5F)
        animation = TranslateAnimation(0f, 0f, 0f, -600f)
        animation = ScaleAnimation(1f, 1f, 1f, -1f)
        animation = ScaleAnimation(1f, 1f, 1f, -1f, tv_target.pivotX, tv_target.pivotY)

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
        objectAnimator = ObjectAnimator.ofFloat(tv_target, "translationX", 0f, 300f, -300f, -100f)
        val path = Path()
        path.addCircle(0f, 0f, 100f, Path.Direction.CCW)
        path.lineTo(100f, 100f)
        path.lineTo(200f, -100f)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            objectAnimator = ObjectAnimator.ofFloat(tv_target, "translationX", "translationY", path)

        objectAnimator = ObjectAnimator.ofFloat(tv_target, "alpha", 1f, 0f, 1f)

        tv_target.pivotX = 0f
        tv_target.pivotY = 0f
        objectAnimator = ObjectAnimator.ofFloat(tv_target, "rotation", 360f)

        val propertyValuesHolder1 = PropertyValuesHolder.ofFloat("translationX", 100f)
        val propertyValuesHolder2 = PropertyValuesHolder.ofFloat("rotation", 360f)
        objectAnimator = ObjectAnimator.ofPropertyValuesHolder(tv_target, propertyValuesHolder1, propertyValuesHolder2)

        val valueAnimator = ValueAnimator.ofFloat(0f, 100f)
        val argbEvaluator = ArgbEvaluator()
        tv_target.setBackgroundColor(Color.GREEN)
        valueAnimator.addUpdateListener {
            tv_target.text = "${it.animatedFraction}"
//            tv_target.translationX = it.animatedValue as Float
            tv_target.setBackgroundColor(argbEvaluator.evaluate(it.animatedFraction, Color.parseColor("#ff55efc4"), Color.parseColor("#fffd79a8")) as Int)


        }

        valueAnimator.duration = 3000
//        objectAnimator.repeatMode = ValueAnimator.REVERSE
//        objectAnimator.repeatCount = ValueAnimator.INFINITE
        valueAnimator.interpolator = AccelerateInterpolator()
        objectAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onAnimationEnd(animation: Animator?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onAnimationCancel(animation: Animator?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onAnimationStart(animation: Animator?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        valueAnimator.start()
    }

    private fun stopAnim() {
        tv_target.clearAnimation()
        if (this::objectAnimator.isInitialized && objectAnimator.isRunning) {
            objectAnimator.cancel()
        }
    }
}