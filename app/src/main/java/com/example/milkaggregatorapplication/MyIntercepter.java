package com.example.milkaggregatorapplication;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MyIntercepter implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request=chain.request();
        Request newRequest=request.newBuilder()
                .header("Authorization","Bearer EAAGqHqufV0UBAFZB4dcXGxHBg9SuKL5G4K4HLpTtSmpws8sxGGlAznKNZA92y3TkAbrJdgtjG0aZC9bitXEGUDHGFtqO4e00L5wWjxYf0fyRBzRQlqOgc3ZAY6ubbQdiMLux1jXCvZA2SqHjUnOuJOUT9MdLGZCYrzhEPJb0f2i9j8u8ZBJZAJ8C6z5yDd6knqX0E7ZBlBw09GAZDZD")
                .header("Content-Type","application/json")

                .build();

        return chain.proceed(newRequest);
    }
}
