package me.apqx.demo.view

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_text.*
import me.apqx.demo.R

class TextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text)
        val str = "0123456789"
        val spannableString = SpannableString(str)
        spannableString.setSpan(ForegroundColorSpan(Color.RED), 0, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(Color.GREEN), 5, 8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        tv_demo_text.text = generateSpannableStr("01234", "5678")
    }

    private fun generateSpannableStr(str1: String, str2: String): SpannableString? {
        val str = str1 + str2;
        val spannableString = SpannableString(str)
        spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#F64346"))
                , 0, str1.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#C9A37E"))
                , str1.length, str.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        return spannableString
    }
}