package br.com.git.base.modules.interceptors

import br.com.git.base.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/*
 * OauthInterceptor: class to intercept all requests and
 * add a OAuth2 authorization.
 * Is injected by Dagger2 and initialized in NetworkModule
 */

class OauthInterceptor @Inject constructor() : Interceptor {

    companion object {
        const val CONST_AUTHORIZATION = "Authorization"
        const val CONST_TOKEN_STRING = "token"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        //Create Authorization header authorization
        val newBuilder = request.newBuilder()
        newBuilder.header(CONST_AUTHORIZATION, "$CONST_TOKEN_STRING ${BuildConfig.AUTH_KEY}")
            .build()

        return chain.proceed(newBuilder.build())
    }
}