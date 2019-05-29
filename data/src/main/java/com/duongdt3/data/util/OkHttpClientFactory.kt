package com.duongdt3.data.util

import android.content.Context
import com.duongdt3.data.BuildConfig
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

class OkHttpClientFactory {
    fun createTrustHttpsClient(context: Context): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }

            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {

            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
            }
        })

        // Install the all-trusting trust manager
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())
        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory = sslContext.socketFactory

        okHttpBuilder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        okHttpBuilder.hostnameVerifier(object : HostnameVerifier {
            override fun verify(hostname: String, session: SSLSession): Boolean {
                return true
            }
        })

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        okHttpBuilder.addInterceptor(httpLoggingInterceptor)


        if (BuildConfig.DEBUG) {
            //init Stetho
            Stetho.initializeWithDefaults(context)
        }

        if (BuildConfig.DEBUG) {
            //add Stetho NetworkInterceptor
            okHttpBuilder.addNetworkInterceptor(StethoInterceptor())
        }

        return okHttpBuilder.build()
    }
}