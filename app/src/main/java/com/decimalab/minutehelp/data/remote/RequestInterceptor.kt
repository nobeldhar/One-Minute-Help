package com.decimalab.minutehelp.data.remote

import android.util.Log
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class
RequestInterceptor @Inject constructor(
    private val preferencesHelper: SharedPrefsHelper,
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var newRequest: Request = chain.request()

        newRequest = newRequest.newBuilder()
            .addHeader(
                "Authorization",
                preferencesHelper.getAccessTokenFromPreference()
            )
            .build()


        Log.d(
            "OkHttp", String.format(
                "--> Sending request %s on %s%n%s",
                newRequest.url(),
                chain.connection(),
                newRequest.headers()
            )
        );
        return chain.proceed(newRequest)

    }
}
