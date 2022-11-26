package com.example.milkaggregatorapplication;

import com.example.milkaggregatorapplication.model.MsgObj;
import com.example.milkaggregatorapplication.model.RequestModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IApiService {
    @POST("{phoneNoId}/messages")
    Call<MsgObj> sendMessage(@Path("phoneNoId")String phoneNoId,
                             @Body RequestModel requestModel

                             );
}
