package com.mobitant.seminar_4th.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.mobitant.seminar_4th.Adapter.ProductMainPagerAdapter
import com.mobitant.seminar_4th.Adapter.SliderMainPagerAdapter
import com.mobitant.seminar_4th.DB.SharedPreferenceController
import com.mobitant.seminar_4th.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //toolbar의 로그인 로그아웃 switch
        configureTitleBar()

        //MainActivity의 tabLayout과 navigation_category_main의 3개의 view들을 연결한다.
        configureMainTab()

        //toolbar의 로그인 글씨를 클릭했을 때 이벤트
        img_toolbar_main_action.setOnClickListener {
            if (SharedPreferenceController.getUserToken(this).isEmpty()) {
                startActivity<LoginActivity>()
            } else {
                SharedPreferenceController.clearUserToken(this)
                configureTitleBar()
            }
        }
    }

    //view가 다 완성되고 난후 titlebar데이터를 가져온다.
    override fun onResume() {
        super.onResume()
        configureTitleBar()
    }

    //인기, 신작, 완결의 레이아웃과 MainActivity의 TabLayout을 연결한다.
    private fun configureMainTab() {
        vp_main_product.adapter = ProductMainPagerAdapter(supportFragmentManager, 3)
        vp_main_product.offscreenPageLimit = 2

        /*      TabLayout과 ViewPager를 연결한다!!    */
        tl_main_category.setupWithViewPager(vp_main_product)

        val navCategoryMainLayout: View =
            (this.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
                .inflate(R.layout.navigation_category_main, null, false)
        tl_main_category.getTabAt(0)!!.customView =
            navCategoryMainLayout.findViewById(R.id.rl_nav_category_main_all) as RelativeLayout
        tl_main_category.getTabAt(1)!!.customView =
            navCategoryMainLayout.findViewById(R.id.rl_nav_category_main_new) as RelativeLayout
        tl_main_category.getTabAt(2)!!.customView =
            navCategoryMainLayout.findViewById(R.id.rl_nav_category_main_end) as RelativeLayout


        /*      TabIndicator 와 viewPager를 지정한다.   */
        vp_main_slider.adapter = SliderMainPagerAdapter(supportFragmentManager, 3)
        vp_main_slider.offscreenPageLimit = 2
        tl_main_indicator.setupWithViewPager(vp_main_slider)


    }

    private fun configureTitleBar() {
        if (SharedPreferenceController.getUserToken(this).isEmpty()) {
            img_toolbar_main_action.isSelected = false
        } else {
            img_toolbar_main_action.isSelected = true
        }
    }
}
