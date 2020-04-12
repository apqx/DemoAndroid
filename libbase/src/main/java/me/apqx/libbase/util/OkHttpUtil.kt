package me.apqx.libbase.util

import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object OkHttpUtil {
    private fun getUnsafeOkHttpClient(): OkHttpClient? {
        return try {
            // 创建一个不验证证书链的TrustManager
            val trustAllCerts = arrayOf<TrustManager>(
                    object : X509TrustManager {
                        @Throws(CertificateException::class)
                        override fun checkClientTrusted(chain: Array<X509Certificate?>?, authType: String?) {
                        }

                        @Throws(CertificateException::class)
                        override fun checkServerTrusted(chain: Array<X509Certificate?>?, authType: String?) {
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate?>? {
                            return arrayOf()
                        }
                    }
            )
            // 使用该TrustManager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            // 创建SSLSocketFactory
            val sslSocketFactory = sslContext.socketFactory
            // 创建OkHttpClient
            OkHttpClient.Builder()
                    .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                    .hostnameVerifier { hostname, session -> true }
                    .build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}