package com.mobitant.seminar_5th.Activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bumptech.glide.Glide
import com.mobitant.seminar_5th.DB.SharedPreferenceController
import com.mobitant.seminar_5th.Network.ApplicationController
import com.mobitant.seminar_5th.Network.NetworkService
import com.mobitant.seminar_5th.Network.Post.PostCommentResponse
import com.mobitant.seminar_5th.R
import kotlinx.android.synthetic.main.activity_write_comment.*
import kotlinx.android.synthetic.main.toolbar_write_comment.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream

class CommentWriteActivity : AppCompatActivity() {

    lateinit var selectedPicUri: Uri

    val REQUEST_CODE_SELECT_IMAGE:Int = 1004

    var product_id = -1
    var episode_id = -1

    val networkService : NetworkService by lazy{
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_comment)

        configureToolbar()
    }

    //
    private fun configureToolbar() {
        product_id = intent.getIntExtra("idx",-1)
        episode_id = intent.getIntExtra("episode_id",-1)
        if(product_id == -1 || episode_id == -1) { finish() }


        //toolbar의 취소 버튼을 눌렀을 때 이벤트
        //뒤로가기
        txt_toolbar_write_comment_cancel.onClick { finish() }

        //toolbar의 확인 버튼을 눌렀을 때 이벤트
        //서버에 댓글 작성한 데이터들이 저장된다.
        txt_toolbar_write_comment_submit.onClick {
            postCommentResponse()
        }

        //camera버튼을 눌렀을 때 이벤트
        //카메라 어플로 이동한다.
        img_bottom_write_comment_camera.onClick {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
            intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE)
        }


    }

    //아이디, 제목, 댓글 내용을 가져와서 서버에 저장한다.
    private fun postCommentResponse() {
        val token = SharedPreferenceController.getUserToken(this)
        val title = edit_comment_write_title.text.toString()
        val comment = txt_comment_write_comment.text.toString()
        if (isValid(token,title,comment)){
            val title_rb = RequestBody.create(MediaType.parse("text/plain"),title)
            val comment_rb = RequestBody.create(MediaType.parse("text/plain"),comment)

            val options = BitmapFactory.Options()
            val inputStream: InputStream = contentResolver.openInputStream(selectedPicUri)
            val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)

            val photoBody = RequestBody.create(MediaType.parse("image/jpg"),byteArrayOutputStream.toByteArray())
            //위의 코드는 jpg(안드로이드로 사진 찍으면 기본 확장자) 이미지 파일 전송을 위한 코드입니다.

            val picture_rb = MultipartBody.Part.createFormData("cmtImg", File(selectedPicUri.toString()).name,photoBody)
            //위의 코드에서, 첫 번째 매개변수 "cmtlmg"는 꼭 서버 API에 명시된 이름으로(networkService @Part "cmtlmg")로 지정

            val postCommentResponse = networkService.postCommentResponse(token,episode_id,comment_rb,picture_rb)

            postCommentResponse.enqueue(object: Callback<PostCommentResponse> {
                override fun onFailure(call: Call<PostCommentResponse>, t: Throwable) {
                    Log.e("write comment failed", t.toString())
                }

                override fun onResponse(call: Call<PostCommentResponse>, response: Response<PostCommentResponse>) {
                    if(response.isSuccessful){
                        toast(response.body()!!.message)
                        if(response.body()!!.status == 200) finish()
                    }
                }
            })
        }
    }

    //카메라 어플을 실행할 때 REQUEST_CODE_SELECT_IMAGE 의 상수 데이터를 던지고
    //제대로 수행하여 다시 댓글 작성 화면으로 왔을때 requestCode 가 REQUEST_CODE_SELECT_IMAGE 인 경우에
    //img_comment_write_roll 에 이미지를 넣는다
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_SELECT_IMAGE){
            if(resultCode == Activity.RESULT_OK){
                data?.let {
                    selectedPicUri = it.data
                    Glide.with(this)
                        .load(selectedPicUri)
                        .thumbnail(0.1f)
                        .into(img_comment_write_roll)
                }
            }
        }
    }

    //사용자 이름, 제목, 댓글 내용이 있는지 여부.
    fun isValid(token: String, title: String,comment:String): Boolean{
        if(token == "") toast("token 값이 없습니다.")
        else if(title == "") edit_comment_write_title.requestFocus()
        else if(comment == "") txt_comment_write_comment.requestFocus()
        else return true
        return false
    }
}
