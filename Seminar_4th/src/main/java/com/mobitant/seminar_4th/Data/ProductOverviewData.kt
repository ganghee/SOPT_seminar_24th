package com.mobitant.seminar_4th.Data

//한 번의 Layout에 보여질 item들을 담을 Data Class만들기
//product list 데이터
data class ProductOverviewData (var thumbnail:String, var idx : Int, var title: String, var likes: Int, var author: String, var isFinished: Int)