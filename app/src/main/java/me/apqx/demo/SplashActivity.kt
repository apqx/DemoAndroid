package me.apqx.demo

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import me.apqx.libtools.log.LogUtil
import java.lang.Exception

class SplashActivity : AppCompatActivity() {
    private lateinit var jobCountDownTimer: CountDownTimer

    private lateinit var jobCountdown: Job

    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startCountdown()

        btn_jump.setOnClickListener {
            stopCountdown()
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }

    private fun startCountdown() {
        LogUtil.d("start countdown")
        stopCountdown()
        jobCountdown = mainScope.launch {
            flow {
                try {
                    for (i in 5 downTo 0) {
                        delay(1000)
                        emit(i)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                LogUtil.d("countdown finish")
            }.collect {
                LogUtil.d("countdown $it")
                btn_jump.text = "${it}"
                if (it == 0) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }
            }
        }
    }

    private fun stopCountdown() {
        if (this::jobCountdown.isInitialized) {
            jobCountdown.cancel()
        }
    }
}