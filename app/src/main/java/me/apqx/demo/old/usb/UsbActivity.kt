package me.apqx.demo.old.usb

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_usb.*

import me.apqx.demo.R
import me.apqx.libtools.log.LogUtil

class UsbActivity : AppCompatActivity(){
    private lateinit var usbManager: UsbManager
    private lateinit var usbDevice: UsbDevice;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usb)
    }

    public fun onClick(view: View) {
        when (view.id) {
            R.id.btn_connect -> {
                usbManager = getSystemService(Context.USB_SERVICE) as UsbManager
                for (usb in usbManager.deviceList.values) {
                    LogUtil.d("vid = ${usb.vendorId} pid = ${usb.productId} name = ${usb.deviceName}")

                    tvShowAppend(tv_show, "vid = ${usb.vendorId} pid = ${usb.productId} name = ${usb.deviceName}")
                    if (usb.vendorId == 0xffff && usb.productId == 0x35) {
                        tvShowAppend(tv_show, "findDevice!")
                        if (!usbManager.hasPermission(usbDevice)) {
                            val intentFilter = IntentFilter("com.android.example.USB_PERMISSION")
                            val intent = PendingIntent.getBroadcast(this, 0, Intent("com.android.example.USB_PERMISSION"), 0)
                            usbManager.requestPermission(usbDevice, intent)
                        }
                    }
                }
            }

            R.id.btn_disconnect -> {

            }
        }
    }

    private fun tvShowAppend(tv: TextView, str: String) {
        val text = tv_show.text.toString()
        tv_show.text = "$text \n $str"
    }
}