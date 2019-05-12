package com.mobitant.seminar_2nd

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*

class LoginActivity : AppCompatActivity() {

    val REQUEST_CODE_LOGIN_ACTIVITY = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edtLoginID.setOnFocusChangeListener{ v, hasFocus ->
            if(hasFocus) v.setBackgroundResource(R.drawable.primary_border)
            else v.setBackgroundResource(R.drawable.gray_border)
        }

        edtLoginPW.setOnFocusChangeListener{ v, hasFocus ->
            if(hasFocus) v.setBackgroundResource(R.drawable.primary_border)
            else v.setBackgroundResource(R.drawable.gray_border)
        }

        //로그인 버튼을 클릭할 때 이벤트
        btnLoginSubmit.setOnClickListener {
            val login_u_id = edtLoginID.text.toString()
            val login_u_pw: String = edtLoginPW.text.toString()

            if(isValid(login_u_id, login_u_pw)) postLoginResponse(login_u_id, login_u_pw)
        }

        //회원가입 버튼을 클릭할 때 이벤트
        txtLoginSignup.setOnClickListener{

            val simpleDateFormat = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val s_time = simpleDateFormat.format(Date())

            //REQUEST_CODE_LOGIN_ACTIVITY 키와 Date값을 회원가입 화면에 전달한다.
            startActivityForResult<SignupActivity>(REQUEST_CODE_LOGIN_ACTIVITY,"start_time" to s_time)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //회원가입창에서 로그인창으로 넘어올 때 RESULT_OK상수가 넘어온다면
        //end_time 데이터를 받아 토스트를 띄운다.
        if(requestCode == REQUEST_CODE_LOGIN_ACTIVITY){
            if(requestCode == Activity.RESULT_OK){
                val e_time = data!!.getStringExtra("end_time")
                toast("End time: ${e_time}")
            }
        }
    }

    //id와 pw가 입력이 안되어 있으면 그곳에 포커스가 된다
    fun isValid(u_id: String, u_pw: String): Boolean{
        if(u_id == "") edtLoginID.requestFocus()
        else if(u_pw == "") edtLoginPW.requestFocus()
        else return true
        return false
    }

    //ID가 입력이 되어있으면 종료한다.
    fun postLoginResponse(u_id: String, u_pw: String){
        // Request Login
        SharedPreferenceController.setUserID(this,u_id)

        finish()
    }
}
