package com.mobitant.android_seminar_24th

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.toast

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val s_time = intent.getStringExtra("start_time")
        toast("Current time:${s_time}")

        val edtOnFocusChangeListener: View.OnFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) v.setBackgroundResource(R.drawable.primary_border)
            else v.setBackgroundResource(R.drawable.gray_border)
        }

        edtSignupName.onFocusChangeListener = edtOnFocusChangeListener
        edtSignupID.onFocusChangeListener = edtOnFocusChangeListener
        edtSignupPW.onFocusChangeListener = edtOnFocusChangeListener

        btnSignupSubmit.setOnClickListener {
            val signup_u_name: String = edtSignupName.text.toString()
            val signup_u_id: String = edtSignupID.text.toString()
            val signup_u_pw: String = edtSignupPW.text.toString()

            if (isVaild(signup_u_id, signup_u_name, signup_u_pw)) {
                finish()
            }
        }

    }



    private fun isVaild(u_id: String, u_pw: String, u_name: String): Boolean {
        if (u_id == "") edtSignupID.requestFocus()
        else if (u_name == "") edtSignupName.requestFocus()
        else if (u_pw == "") edtSignupPW.requestFocus()
        else return true
        return false
    }
}
