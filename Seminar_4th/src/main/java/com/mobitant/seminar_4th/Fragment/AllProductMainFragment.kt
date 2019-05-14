package com.mobitant.seminar_4th.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobitant.seminar_4th.Adapter.ProductOverviewRecyclerViewAdapter
import com.mobitant.seminar_4th.Data.ProductOverviewData
import com.mobitant.seminar_4th.Network.ApplicationController
import com.mobitant.seminar_4th.Network.Get.GetMainProductListResponse
import com.mobitant.seminar_4th.Network.NetworkService
import com.mobitant.seminar_4th.R
import kotlinx.android.synthetic.main.fragment_all_product_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllProductMainFragment : Fragment() {

    lateinit var productOverviewRecyclerViewAdapter: ProductOverviewRecyclerViewAdapter
    val networkService: NetworkService by lazy{
        ApplicationController.instance.networkService
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_product_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var dataList: ArrayList<ProductOverviewData> = ArrayList()

        //Adapter와 Recyclerview연결
        //격자 형태로 item들을 배치
        productOverviewRecyclerViewAdapter = ProductOverviewRecyclerViewAdapter(context!!, dataList)
        rv_product_overview_all.adapter = productOverviewRecyclerViewAdapter
        rv_product_overview_all.layoutManager = GridLayoutManager(context!!, 3)

        getMainProductListResponse()


        //수평 스크롤 형태
        //rv_product_overview_all.layoutManager = LinearLayoutManager(context)
        //수직 스크롤 형태
        //rv_product_overview_all.layoutManager = LinearLayoutManager(context,LinearLayout.VERTICAL,false)
        //그리드 형태 3열
        //rv_product_overview_all.layoutManager = GridLayoutManager(context!!,3)
        //지그재그 형태로 각 열의 높이가 다를 때
        //rv_product_overview_all.layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)

    }

    //flag지정하기
    private fun getMainProductListResponse() {
        val getMainProductListResponse = networkService.getMainProductListResponse(
            "application/x-www-form-urlencoded", 1)

        getMainProductListResponse.enqueue(object : Callback<GetMainProductListResponse> {
            override fun onFailure(call: Call<GetMainProductListResponse>, t: Throwable) {
                Log.e("AllMainProduct Fail", t.toString())
            }

            //응답이 되었을 때 productOverviewRecyclerViewAdapter에 데이터를 연결 시켜 주어야 하므로
            //이때 연결 시키는 데이터는 서버에 있는 데이터이다.
            //notifyDataSetChanged()를 통해 Adapter에게 표시할 데이터가 실시간으로 변경 되었다는 사실을 알려준다.
            override fun onResponse(
                call: Call<GetMainProductListResponse>,
                response: Response<GetMainProductListResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        val tmp: ArrayList<ProductOverviewData> = response.body()!!.data!!
                        productOverviewRecyclerViewAdapter.dataList = tmp
                        productOverviewRecyclerViewAdapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }
}
