package com.mobitant.seminar_4th.Adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.mobitant.seminar_4th.Fragment.SliderMainFragment

class SliderMainPagerAdapter(fm: FragmentManager?, val num_fragment: Int) : FragmentStatePagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment? {

        //보여질 fragment들은 모두 같은 클래스 상속
        var fragment = SliderMainFragment()

        //한 개 bundle에 담길 데이터의 개수 지정
        var bundle = Bundle(1)

        //Fragment별 배경이미지 지정
        when (p0) {
            0 -> bundle.putString(
                "background_url",
                "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045"
            )
            1 -> bundle.putString(
                "background_url",
                "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/57336208_1714122562067955_3478487248456908800_o.jpg?_nc_cat=110&_nc_ht=scontent-icn1-1.xx&oh=37e2d4b1ab1e6797ae00db0b61ba8c1c&oe=5D2A80BE"
            )
            2 -> bundle.putString(
                "background_url",
                "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/57484969_1714122525401292_2191765538210840576_o.jpg?_nc_cat=109&_nc_ht=scontent-icn1-1.xx&oh=cdefea1b7b9c21f74652ad3684d3e723&oe=5D6B2C6D"
            )
        }
        //Bundle 객체를 arguments에 전달
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int {
        return num_fragment
    }
}