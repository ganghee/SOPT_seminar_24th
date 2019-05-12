package com.mobitant.android_seminar_24th

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() {
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

        btnLoginSubmit.setOnClickListener {
            val login_u_id = edtLoginID.text.toString()
            val login_u_pw: String = edtLoginPW.text.toString()

            if(isValid(login_u_id, login_u_pw)) postLoginResponse(login_u_id, login_u_pw)
        }
        txtLoginSignup.setOnClickListener{
            startActivity<SignupActivity>()
        }

        val id = intent.getIntExtra("id" ,1)
        toast("id: ${id}")


    }
    fun isValid(u_id: String, u_pw: String): Boolean{
        if(u_id == "") edtLoginID.requestFocus()
        else if(u_pw == "") edtLoginPW.requestFocus()
        else return true
        return false
    }

    fun postLoginResponse(u_id: String, u_pw: String){
        // Request Login
        finish()
    }
}
