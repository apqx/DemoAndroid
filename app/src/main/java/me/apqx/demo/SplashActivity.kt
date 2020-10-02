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

class SplashActivity : AppCompatActivity() {
    private lateinit var jobCountDownTimer: CountDownTimer

    private lateinit var jobCountdown: Job

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

    private fun startCountdown() {
        LogUtil.d("start countdown")
        stopCountdown()
        jobCountdown = GlobalScope.launch(Dispatchers.Main) {
            flow {
                for (i in 5 downTo 0) {
                    delay(1000)
                    emit(i)
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