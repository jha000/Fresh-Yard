package com.example.milkaggregatorapplication

import retrofit2.http.POST
import com.example.milkaggregatorapplication.model.RequestModel
import com.example.milkaggregatorapplication.model.MsgObj
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Path

interface IApiService {
    @POST("{phoneNoId}/messages")
    fun sendMessage(
        @Path("phoneNoId") phoneNoId: String?,
        @Body requestModel: RequestModel?
    ): Call<MsgObj?>?
}