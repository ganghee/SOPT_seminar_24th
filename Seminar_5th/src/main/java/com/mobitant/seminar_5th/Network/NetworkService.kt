package com.mobitant.seminar_5th.Network

import com.google.gson.JsonObject
import com.mobitant.seminar_5th.Network.Get.GetMainProductListResponse
import com.mobitant.seminar_5th.Network.Post.PostCommentResponse
import com.mobitant.seminar_5th.Network.Post.PostLoginResponse
import com.mobitant.seminar_5th.Network.Post.PostSignupResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
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
        @Path("flag") flag: Int
    ) :Call<GetMainProductListResponse>

    //사진, 음성, 동영상 같은 파일을 전달할 때는 @Multipart라는 방식으로 통신한다.
    //Header에 Content_type을 붙이는 것이 아니라 @Multipart에 Annotation을 통해 타입을 명시한다.
    //@Body에 json형식의 데이터를 보내는 것이 아니기 때문에
    //@Part에 각각의 데이터를 담습니다.

    //@Part에 Int, Double등의 값을 담을 때에는 자료형을 그대로 명시합니다.
    //@Part에 String의 값을 담을 때에는 RequestBody에 담아줘야 합니다.
    //파일(이미지, 음악)을 전달할 때에는  MultipartBody.Part 에 담아줘야 합니다.
    @Multipart
    @POST("/api/webtoons/episodes/cmts")
    fun postCommentResponse(
        @Header("token") token: String,
        //@Header("Content_Type") content_type: String,

        @Part("epldx") epldx: Int,
        @Part("content") content: RequestBody,
        @Part cmtlmg: MultipartBody.Part
    ): Call<PostCommentResponse>
}