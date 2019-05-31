package com.mobitant.seminar_6th.Activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.mobitant.seminar_6th.Adapter.ProductMainPagerAdapter
import com.mobitant.seminar_6th.Adapter.SliderMainPagerAdapter
import com.mobitant.seminar_6th.DB.SharedPreferenceController
import com.mobitant.seminar_6th.Network.ApplicationController
import com.mobitant.seminar_6th.Network.Get.GetMainTopImageResponse
import com.mobitant.seminar_6th.Network.NetworkService
import com.mobitant.seminar_6th.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
     var context: Context =this
    val networkService: NetworkService by lazy{
        ApplicationController.instance.networkService
    }
    lateinit var sliderMainPagerAdapter: SliderMainPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //toolbar의 로그인 로그아웃 switch
        configureTitleBar()

        //MainActivity의 tabLayout과 navigation_category_main의 3개의 view들을 연결한다.
        configureMainTab()


        toast("토큰값"+SharedPreferenceController.getUserToken(this))

        //toolbar의 로그인 글씨를 클릭했을 때 이벤트
        img_toolbar_main_action.setOnClickListener {
            if (SharedPreferenceController.getUserToken(this).isEmpty()) {
                startActivity<LoginActivity>()
            } else {
                alert(message = "로그아웃 하시겠습니까?"){
                    positiveButton("Yes"){
                        SharedPreferenceController.clearUserToken(context)
                        configureTitleBar()
                    }
                    negativeButton("No"){
                    }
                }.show()
            }
        }
    }

    //view가 다 완성되고 난후 titlebar데이터를 가져온다.
    override fun onResume() {
        super.onResume()
        configureTitleBar()
        toast("토큰값"+SharedPreferenceController.getUserToken(this))
        Log.d("토큰값","@@"+SharedPreferenceController.getUserToken(this))
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
        sliderMainPagerAdapter = SliderMainPagerAdapter(supportFragmentManager,3)
        vp_main_slider.adapter = SliderMainPagerAdapter(supportFragmentManager, 3)
        vp_main_slider.offscreenPageLimit = 2
        tl_main_indicator.setupWithViewPager(vp_main_slider)

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

                        sliderMainPagerAdapter.getItem(0)!!.arguments!!.putString("background_url",tmp[0])
                        sliderMainPagerAdapter.getItem(1)!!.arguments!!.putString("background_url",tmp[1])
                        sliderMainPagerAdapter.getItem(2)!!.arguments!!.putString("background_url",tmp[2])

                        Log.d("SliderMainFragment","#######    #####"+tmp)

                        //val thumnail:String? = arguments!!.getString("background_url",tmp[0])
                        //val color: Int = arguments!!.getInt("background_color")
                        //img_fragment_slider_main.setBackgroundColor(color)

                    }
                }
            }
        })
    }

    private fun configureTitleBar() {
        if (SharedPreferenceController.getUserToken(this).isEmpty()) {
            img_toolbar_main_action.isSelected = false
        } else {
            img_toolbar_main_action.isSelected = true
        }

    }
}
