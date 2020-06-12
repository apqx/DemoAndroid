package me.apqx.demo.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.frag_img.*
import me.apqx.demo.R
import me.apqx.demo.mvp.BaseFragment
import me.apqx.demo.mvp.BasePresenter
import me.apqx.demo.mvp.IBaseView
import me.apqx.libtools.log.LogUtil

import java.math.BigDecimal
import java.math.RoundingMode


class ImgFragment : BaseFragment<BasePresenter<IBaseView>>() {
    val imgUrlList = arrayOf("https://res.cngoldres.com/upload/jjh/2020/0320/bfc3482c15f2d88dbed4c5149f148c22.png"
            , "https://res.cngoldres.com/upload/jjh/2020/0320/ae043d957ca6da8a725ea066628b5c96.png")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_img, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_img_change.setOnClickListener(this)

    }

    private var flag = 0
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_img_change -> {
                // 似乎是ImageView第一次加载的时候确定宽度和高度，然后再加载新的图片，宽和高就不变了
                val position = flag++ % imgUrlList.size
                // 如果要加载的View是GONE状态，似乎是不会执行加载流程的
                Glide.with(requireContext())
                        .load(imgUrlList[position])
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                                LogUtil.d("Glide onLoadFailed ${e?.message}")
                                return false
                            }

                            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                // 如果宽度固定，高度随img而变化
                                if (resource == null) return false
                                val width = iv_img_frag.width
                                LogUtil.d("Glide onResourceReady $width - ${resource.intrinsicWidth} : ${resource.intrinsicHeight}")
                                val height = BigDecimal(resource.intrinsicHeight)
                                        // 除法必须设置小数点位数，越多越精确
                                        .divide(BigDecimal(resource.intrinsicWidth), 5, RoundingMode.HALF_UP)
                                        .multiply(BigDecimal(width))
                                        .toInt()
                                LogUtil.d("setImageView height = $height")
                                iv_img_frag.layoutParams.height = height
                                return false
                            }
                        })
                        .into(iv_img_frag)
            }
        }
    }
}