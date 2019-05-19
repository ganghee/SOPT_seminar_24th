package com.mobitant.seminar_5th.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.mobitant.seminar_5th.Fragment.AllProductMainFragment
import com.mobitant.seminar_5th.Fragment.EndProductMainFragment
import com.mobitant.seminar_5th.Fragment.NewProductMainFragment

class ProductMainPagerAdapter (fm : FragmentManager, private val num_fragment:Int):FragmentStatePagerAdapter(fm){


    //Singleton Design Pattern: 기존에 생성되었던 객체를 재사용
    companion object{
        private var allProductMainFragment : AllProductMainFragment? = null
        private var newProductMainFragment : NewProductMainFragment? = null
        private var endProductMainFragment : EndProductMainFragment? = null
    }

    @Synchronized
    fun getAllProductMainFragment(): AllProductMainFragment {
        if (allProductMainFragment == null) allProductMainFragment = AllProductMainFragment()
        return allProductMainFragment!!
    }

    @Synchronized
    fun getNewProductMainFragment(): NewProductMainFragment {
        if (newProductMainFragment == null) newProductMainFragment = NewProductMainFragment()
        return newProductMainFragment!!
    }

    @Synchronized
    fun getEndProductMainFragment(): EndProductMainFragment{
        if (endProductMainFragment == null) endProductMainFragment = EndProductMainFragment()
        return endProductMainFragment!!
    }

    override fun getItem(p0: Int): Fragment? {
        return when(p0) {
            0 -> getAllProductMainFragment()
            1 -> getNewProductMainFragment()
            2 -> getEndProductMainFragment()
            else -> null
        }
    }

    override fun getCount(): Int {
        return num_fragment
    }
}