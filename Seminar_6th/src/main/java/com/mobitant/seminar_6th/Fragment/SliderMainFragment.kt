package com.mobitant.seminar_6th.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mobitant.seminar_6th.R
import kotlinx.android.synthetic.main.fragment_slider_main.*

class SliderMainFragment : Fragment() {

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

        val img_url:String = arguments!!.getString("background_url")
        //val color: Int = arguments!!.getInt("background_color")
        //img_fragment_slider_main.setBackgroundColor(color)
        Glide.with(this)
            .load(img_url)
            .into(img_fragment_slider_main)

    }

}
