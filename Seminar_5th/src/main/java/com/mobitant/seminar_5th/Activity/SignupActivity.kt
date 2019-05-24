package com.mobitant.seminar_5th.Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.mobitant.seminar_5th.Network.ApplicationController
import com.mobitant.seminar_5th.Network.NetworkService
import com.mobitant.seminar_5th.Network.Post.PostSignupResponse
import com.mobitant.seminar_5th.R
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class SignupActivity : AppCompatActivity() {

    val networkService : NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val s_time = intent.getStringExtra("start_time")
        toast("Current time:${s_time}")

        //포커스가 되면 배경이 바뀐다.
        val edtOnFocusChangeListener: View.OnFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) v.setBackgroundResource(R.drawable.primary_border)
            else v.setBackgroundResource(R.drawable.gray_border)
        }

        edtSignupName.onFocusChangeListener = edtOnFocusChangeListener
        edtSignupID.onFocusChangeListener = edtOnFocusChangeListener
        edtSignupPW.onFocusChangeListener = edtOnFocusChangeListener

        //회원가입이 클릭되었을 경우
        btnSignupSubmit.setOnClickListener {
            val signup_u_name: String = edtSignupName.text.toString()
            val signup_u_id: String = edtSignupID.text.toString()
            val signup_u_pw: String = edtSignupPW.text.toString()

            if (isVaild(signup_u_id, signup_u_name, signup_u_pw)) {
                postSignupResponse(signup_u_id, signup_u_pw, signup_u_name)
            }
        }

    }

    //date형식의 데이터를 다음 화면에 전달한다.
    private fun postSignupResponse(u_id: String, u_pw: String, u_name: String) {

        //id,password,name 데이터를 받아서 JSON 객체로 만든다.
        var jsonObject = JSONObject()
        jsonObject.put("id", u_id)
        jsonObject.put("password", u_pw)
        jsonObject.put("name", u_name)

        //gsonObject는 body로 들어간다.
        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject
        val postSignupResponse: Call<PostSignupResponse> =
            networkService.postSignupResponse("application/json", gsonObject)
        postSignupResponse.enqueue(object : Callback<PostSignupResponse> {
            override fun onFailure(call: Call<PostSignupResponse>, t: Throwable) {
                Log.e("Login failed", t.toString())
            }

            override fun onResponse(call: Call<PostSignupResponse>, response: Response<PostSignupResponse>) {
                if (response.isSuccessful) {
                    toast(response.body()!!.message)
                    if (response.body()!!.status == 201) {
                        //Resquest Signup
                        val simpleDateFormat = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                        val e_time = simpleDateFormat.format(Date())

                        val intent = Intent()
                        intent.putExtra("end_time", e_time)
                        setResult(Activity.RESULT_OK, intent)

                        finish()
                    }
                }
            }

        })
    }
    //아이디, 이름, 패스워드 입력이 안되어 있을 경우 focus를 한다.
    private fun isVaild(u_id: String, u_pw: String, u_name: String): Boolean {
        if (u_id == "") edtSignupID.requestFocus()
        else if (u_name == "") edtSignupName.requestFocus()
        else if (u_pw == "") edtSignupPW.requestFocus()
        else return true
        return false
    }
}
