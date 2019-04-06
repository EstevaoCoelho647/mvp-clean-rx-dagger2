package br.com.git.base.modules

import br.com.git.base.BuildConfig
import br.com.git.base.modules.interceptors.OauthInterceptor
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/*
 * Class to provides all network configurations.
 * Provides: HttpLoggingInterceptor, OAuthInterceptor,
 * Moshi, OkHttp and Retrofit instances.
 */

@Module
class NetworkModule {

    @Singleton
    @Provides
    @Named("LoggingInterceptor")
    fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

    @Singleton
    @Provides
    @Named("oauthInterceptor")
    fun provideOauthInterceptor(): Interceptor =
        OauthInterceptor()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder().add(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .build()

    @Singleton
    @Provides
    fun providesOkHttp(
        @Named("LoggingInterceptor")
        loggingInterceptor: Interceptor,
        @Named("oauthInterceptor")
        oauthInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(oauthInterceptor)
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(oktHttpClient: OkHttpClient, moshi: Moshi, @Named("BaseUrl") baseUrl: String): Retrofit =
        Retrofit.Builder()
            .client(oktHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}


