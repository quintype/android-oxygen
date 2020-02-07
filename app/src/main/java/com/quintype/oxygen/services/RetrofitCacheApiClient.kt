package com.quintype.oxygen.services

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.quintype.oxygen.OxygenConstants
import com.quintype.oxygen.TLSSocketFactory
import com.quintype.oxygen.utils.widgets.NetworkUtils
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitCacheApiClient(context: Context) {
    private val mContext = context
    private var retrofit: Retrofit? = null
    private val myCache = Cache(
        mContext.cacheDir,
        cacheSize
    )

    /**
     * interceptor and builder is used to print the log
     */
    private var httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    //                .setLevel(HttpLoggingInterceptor.Level.BODY)
//                .setLevel(HttpLoggingInterceptor.Level.HEADERS)


    private val cacheInterceptor: (Interceptor.Chain) -> Response = { chain ->
        // Get the request from the chain.
        var request = chain.request()
        /*
         *  Leveraging the advantage of using Kotlin,
         *  we initialize the request and change its header depending on whether
         *  the device is connected to Internet or not.
         */
        request = if (NetworkUtils.isConnected(mContext)) {
            /*
                    *  If there is Internet, get the cache that was stored 5 seconds ago.
                    *  If the cache is older than 30 seconds, then discard it,
                    *  and indicate an error in fetching the response.
                    *  The 'max-age' attribute is responsible for this behavior.
                    */
            request.newBuilder().header("Cache-Control", "public, max-age=" + 30).build()
        } else {
            /*
                    *  If there is no Internet, get the cache that was stored 7 days ago.
                    *  If the cache is older than 2 days, then discard it,
                    *  and indicate an error in fetching the response.
                    *  The 'max-stale' attribute is responsible for this behavior.
                    *  The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
                    */
            request.newBuilder().header(
                "Cache-Control",
                "public, only-if-cached, max-stale=${TimeUnit.DAYS.toMillis(2)}"
            ).build()
        }
        // Add the modified request to the chain.
        chain.proceed(request)

    }

    private val okHttpClientBuilder = OkHttpClient.Builder()
        .cache(myCache)
        .addInterceptor(cacheInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .sslSocketFactory(TLSSocketFactory())
        .hostnameVerifier { _, _ -> true }
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()


    fun getRetrofitApiClient(): Retrofit {
        if (OxygenConstants.BASE_URL != null)
            retrofit = Retrofit
                .Builder()
                .baseUrl(OxygenConstants.BASE_URL)
                .client(okHttpClientBuilder)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit as Retrofit
    }

    companion object {
        private const val cacheSize = (5 * 1024 * 1024).toLong()

    }
}