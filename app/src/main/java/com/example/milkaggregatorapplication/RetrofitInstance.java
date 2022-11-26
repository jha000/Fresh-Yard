package com.example.milkaggregatorapplication;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static String BASE_URL="https://graph.facebook.com/";
    private static String VERSION="v15.0/";

    private static Retrofit retrofit;

    public static Retrofit getRetrofitClient(){

        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL+VERSION)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient().newBuilder().addInterceptor(new MyIntercepter()).build())
                    .build();

        }

        return retrofit;


    }
}
