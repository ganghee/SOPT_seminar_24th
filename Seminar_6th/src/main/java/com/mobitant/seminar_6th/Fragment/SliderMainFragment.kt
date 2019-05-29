package com.mobitant.seminar_6th.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mobitant.seminar_6th.Network.ApplicationController
import com.mobitant.seminar_6th.Network.Get.GetMainTopImageResponse
import com.mobitant.seminar_6th.Network.NetworkService
import com.mobitant.seminar_6th.R
import kotlinx.android.synthetic.main.fragment_slider_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SliderMainFragment : Fragment() {

    val networkService: NetworkService by lazy{
        ApplicationController.instance.networkService
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_slider_main, container, false)
    }

    //Activity 생성된 후, Activity에 View가 올라간 뒤에 호출되는 생명주기
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getMainTopImageResponse()
    }
    private fun getMainTopImageResponse() {
        val getMainTopImageResponse = networkService.getMainTopImageResponse(
            "application/json")

        getMainTopImageResponse.enqueue(object : Callback<GetMainTopImageResponse> {
            override fun onFailure(call: Call<GetMainTopImageResponse>, t: Throwable) {
                Log.e("MainTopImage Fail", t.toString())
            }

            //응답이 되었을 때 productOverviewRecyclerViewAdapter에 데이터를 연결 시켜 주어야 하므로
            //이때 연결 시키는 데이터는 서버에 있는 데이터이다.
            //notifyDataSetChanged()를 통해 Adapter에게 표시할 데이터가 실시간으로 변경 되었다는 사실을 알려준다.
            override fun onResponse(
                call: Call<GetMainTopImageResponse>,
                response: Response<GetMainTopImageResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        val tmp: ArrayList<String> = response.body()!!.data!!

                        arguments!!.putString("background_url",tmp[0])
                        arguments!!.putString("background_url",tmp[1])
                        arguments!!.putString("background_url",tmp[2])


                        val img_url:String? = arguments!!.getString("background_url")
                        Log.d("!!!","############"+tmp)
                        Log.d("!!!","@@@@@@@@@@@@@@@"+arguments)
                        //val thumnail:String? = arguments!!.getString("background_url",tmp[0])
                        //val color: Int = arguments!!.getInt("background_color")
                        //img_fragment_slider_main.setBackgroundColor(color)
                        Glide.with(this@SliderMainFragment)
                            .load(img_url)
                            .into(img_fragment_slider_main)

                    }
                }
            }
        })
    }
}
