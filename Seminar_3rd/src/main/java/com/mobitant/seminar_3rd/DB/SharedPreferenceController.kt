
package com.mobitant.seminar_3rd.DB

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceController {

    val MY_ACCOUNT = "unique_string"

    //키(u_id) 값(time)을 입력한다.
    fun setUserID(ctx: Context, time:String){
        val preference : SharedPreferences =  ctx.getSharedPreferences(MY_ACCOUNT,Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor =preference.edit()
        editor.putString("u_id",time)
        editor.apply()
    }

    //키(u_id) 값(time)을 가져온다.
    fun getUserID(ctx: Context):String{
        val preference : SharedPreferences =  ctx.getSharedPreferences(MY_ACCOUNT,Context.MODE_PRIVATE)
        return preference.getString("u_id","")
    }

    //키(u_id) 값(time)을 삭제한다.
    fun clearUserID(ctx:Context){
        val preference : SharedPreferences = ctx.getSharedPreferences(MY_ACCOUNT,Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preference.edit()
        editor.clear()
        editor.apply()
    }

}