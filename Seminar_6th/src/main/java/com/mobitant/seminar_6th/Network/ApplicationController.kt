package com.mobitant.seminar_6th.Network

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Application 클래스는 Android Component들 사이에서 공유 가능한 전역 클래스
//앱이 실행될 때 가장 먼저 실행된다.
//초기화 전역 객체들이 있다면 이곳에서 해준다.
//  순서
//1.Application()을 상속받는 클래스를 생성
//2.AndroidManifest.xml에 등록
//3.NetworkService 인터페이스를 초기화 해 준다.

class ApplicationController :Application(){

    //통신하고자 하는 API 서버의 기본 주소
    private val baseURL ="http://hyunjkluz.ml:2424/"
    lateinit var networkService: NetworkService

    companion object{
        lateinit var instance:ApplicationController
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        buildNetwork()
    }

    //Retrofit 객체 생성
    private fun buildNetwork() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //Retrofit 객체 활성화
       networkService = retrofit.create(NetworkService::class.java)
    }
}