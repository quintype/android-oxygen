package com.quintype.oxygen.services

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.quintype.oxygen.OxygenConstants
import com.quintype.oxygen.TLSSocketFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created TemplateCollectionWithRx by rakshith on 7/23/18.
 */

class RetrofitApiClient {
    companion object {
        private var retrofit: Retrofit? = null
        /**
         * interceptor and builder is used to print the log
         */
        private var interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
        //                .setLevel(HttpLoggingInterceptor.Level.BODY)
//                .setLevel(HttpLoggingInterceptor.Level.HEADERS)
        var builder: OkHttpClient.Builder = OkHttpClient().newBuilder().addInterceptor(interceptor)
            .sslSocketFactory(TLSSocketFactory())
            .hostnameVerifier { hostname, session -> true }
        .connectTimeout(30, TimeUnit.SECONDS)

        fun getRetrofitApiClient(): Retrofit {
            if (OxygenConstants.BASE_URL != null)
                retrofit = Retrofit
                    .Builder()
                    .baseUrl(OxygenConstants.BASE_URL)
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            return retrofit as Retrofit
        }
    }
}