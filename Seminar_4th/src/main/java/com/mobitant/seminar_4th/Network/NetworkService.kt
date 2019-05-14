package com.mobitant.seminar_4th.Network

import com.google.gson.JsonObject
import com.mobitant.seminar_4th.Network.Get.GetMainProductListResponse
import com.mobitant.seminar_4th.Network.Post.PostLoginResponse
import com.mobitant.seminar_4th.Network.Post.PostSignupResponse
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {
    //post방식은 HTTP Request의 Body에 Json 포맷으로 데이터를 담아서 전달.
    //로그인 api
    @POST("/api/auth/signin")
    fun postLoginResponse(
        @Header("Content-Type") content_type : String,
        @Body() body:JsonObject
    ): Call<PostLoginResponse>

    //회원가입 api
    @POST("/api/auth/signup")
    fun postSignupResponse(
        @Header("Content-Type") content_type: String,
        @Body() body:JsonObject
    ): Call<PostSignupResponse>

    //product list 가져오기
    //인기 작품목록을 가져올 때 GET http://hyunjkluz.ml:2424/api/webtoons/main/1
    //신작 작품목록을 가져올 때 GET http://hyunjkluz.ml:2424/api/webtoons/main/2
    //완결 작품목록을 가져올 때 GET http://hyunjkluz.ml:2424/api/webtoons/main/3
    @GET("/api/webtoons/main/{flag}")
    fun getMainProductListResponse(
        @Header("Content-Type") content_type: String,
        @Query("flag") flag: Int
    ) :Call<GetMainProductListResponse>
}