package com.example.milkaggregatorapplication

import okhttp3.Interceptor.Chain.request
import okhttp3.Request.newBuilder
import okhttp3.Request.Builder.header
import okhttp3.Request.Builder.build
import okhttp3.Interceptor.Chain.proceed
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import kotlin.Throws

class MyIntercepter : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {
        val request: Request = chain.request()
        val newRequest = request.newBuilder()
            .header(
                "Authorization",
                "Bearer EAAGqHqufV0UBAFZB4dcXGxHBg9SuKL5G4K4HLpTtSmpws8sxGGlAznKNZA92y3TkAbrJdgtjG0aZC9bitXEGUDHGFtqO4e00L5wWjxYf0fyRBzRQlqOgc3ZAY6ubbQdiMLux1jXCvZA2SqHjUnOuJOUT9MdLGZCYrzhEPJb0f2i9j8u8ZBJZAJ8C6z5yDd6knqX0E7ZBlBw09GAZDZD"
            )
            .header("Content-Type", "application/json")
            .build()
        return chain.proceed(newRequest)
    }
}