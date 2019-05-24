package com.mobitant.seminar_5th.DB

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceController {

    val MY_ACCOUNT = "unique_string"

    //키(u_id) 값(time)을 입력한다.
    fun setUserToken(ctx: Context, time: String) {
        val preference: SharedPreferences = ctx.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preference.edit()
        editor.putString("u_token", time)
        editor.commit()
    }

    //키(u_id) 값(time)을 가져온다.
    fun getUserToken(ctx: Context): String {
        val preference: SharedPreferences = ctx.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        return preference.getString("u_token", "")
    }

    //키(u_id) 값(time)을 삭제한다.
    fun clearUserToken(ctx: Context) {
        val preference: SharedPreferences = ctx.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preference.edit()
        editor.clear()
        editor.commit()
    }

}