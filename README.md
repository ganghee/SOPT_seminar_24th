# ⭐️ SOPT 24th Android part 정리 ⭐️
- [1차 세미나](#-1차-세미나) - Activity, Intent, Layout, Event
- [2차 세미나](#-2차-세미나) - Fragment, FragmentPagerAdapter, 내부 DB
- [3차 세미나](#-3차-세미나) - Selector, Glide, RecyclerView
- [4차 세미나](#-4차-세미나) - HTTP, JSON, Retrofit
- [5차 세미나](#-5차-세미나) - 디자인 합동세미나
- [6차 세미나](#-6차-세미나) - REST API, 서버 합동세미나
- [7차 세미나](#-7차-세미나) - 형상관리, GitHub
--------------
|  LoginActivity  |  SignupActivity | MainActivity  |  ProductActivity |
|:---:|:---:|:---:|:---:|
|![image](https://user-images.githubusercontent.com/35513039/66894544-a7bdbb00-f02b-11e9-9bdb-073a7bbdd781.png)|![image](https://user-images.githubusercontent.com/35513039/66894984-bc4e8300-f02c-11e9-9906-e9c79d89f3d4.png)|![image](https://user-images.githubusercontent.com/35513039/66895061-e1db8c80-f02c-11e9-91c7-6b2fb11c8559.png)|![image](https://user-images.githubusercontent.com/35513039/66895072-e9029a80-f02c-11e9-97ab-d06d23510b6b.png)|
|   WebtoonActivity  |  CommentActivity    |  CommentWriteActivity   | 
|![image](https://user-images.githubusercontent.com/35513039/66895083-ec962180-f02c-11e9-8ff4-7d4aed9fe0b1.png)|![image](https://user-images.githubusercontent.com/35513039/66895096-f28c0280-f02c-11e9-9e7c-e510ae4dcd62.png)|![image](https://user-images.githubusercontent.com/35513039/66895108-f750b680-f02c-11e9-8fe7-e14a152c800c.png)|

------------

## 🔥 1차 세미나

1. 🌙  __Directory structure__
   
   - AndroidManifest: 앱에 대한 기본 정보 정의 (패키지명, 앱 이름, 앱 아이콘, 앱 권한 등) 
   - Activity: 각 Activity에서의 동작을 구현 
   - drawable: 앱에서 사용할 이미지 리소스 
   - values: 앱에서 사용할 '값(대명사)' 들을 정의 (colors, string, style) 
   - build.gradle: 프로젝트 빌드에 사용되는 환경 설정, 특정 앱의 빌드에 사용되는 환경설정을 따로 정의할 수 있다.(SDK버젼, 외부 라이브러리, 앱 버전) 

2. 🌙  __Activity__
   
   - UI가 있는 앱의 단일 화면 
   - 하나의 앱에는 여러 개의 Activity가 존재 
   - AndroidManifest.xml 파일에 `<activity>` 태그를 통해 선언 
   - 각 Activity마다 독립적인 생명주기가 존재 
 
      <img src="https://user-images.githubusercontent.com/35513039/66895368-907fcd00-f02d-11e9-97fd-d4be89941167.png" width="50%" height="50%" />
   `onCreate` -> `onStart` -> `onResume` -> `onPause` -> `onStop` -> `onDestroy` \
   매소드 오버라이딩을 통해 Activity의 각 상태마다 사용자가 원하는 기능구현

3. 🌙  __Intent__

    안드로이드 컴포넌트(Activity, Service, Content provider, Broadcast receiver) 간의 통신매체 \
    새로운 Activity의 호출 시에 사용
    Activity간의 정보 전달에 사용 
    ```
    // 데이터 전달
    startActivity<LoginActivity>("id" to 1)
    ```
    ```
    // 데이터 수신
    val number = intent.getIntExtra("id") // number = 1
    ```

4. 🌙  __View__

    Layout을 구성하는 모든 구성 요소 \
    View -> TextView, EditTextView, Button, ImageButton, ImageView ... \
    ViewGroup -> RelativeLayout, LinearLayout, ConstraintLayout ... 

    LinearLayout:  Group 내의 View들을 선형으로 배치하는 Layout \
    RelativeLayout: Group 내의 View들을 부모 및 형제 View를 기준으로 상대적으로 배치하는 Layout 

    속성 \
    id: View의 고유한 이름 지정 \
    margin: View의 테두리 바깥으로 확보하는 간격 \
    padding: View의 테두리와 컨텐츠의 간격 \
    visibility: visivle -> 잘 보임 invisible -> 자리를 차지하나 보이지 않음 Gone -> View가 자리를 차지하지도 않고, 보이지도 않음

    __둥근 모서리 스타일 만들기__
    - drawable에서 xml파일 생성하기
    - 코드 입력
        ```
        <shape xmlns:android="http://schemas.android.com/apk/res/android"
        android:shape="rectangle">
        <solid android:color="#FFFFFF" />   //체우기 속성
        <stroke android:width="1dp"         //테두리 속성
                android:color="@color/colorPrimaryYellow" /> 
        <corners                            //모서리 속성
                android:radius="20dp"/>
        </shape>
        ```


5. 🌙  __Activity와 Layout__
   
   activity의 setContentView함수에 파라미터로 레이아웃 파일명을 지정하여 Layout 과 연결할 수 있다. \
   ``` setContentView(R.layout.activity_main) ```

## 🔥 2차 세미나

1. 🌙  __Activity간 양방향 데이터 전달__

    Login 창에서 Signup창으로 이동할 때 REQUEST_CODE_LOGIN_ACTIVITY 상수 전달
    Signup창에서 Login 창으로 되돌아 왔을 때 onActivityResult를 오버라이딩하여 requistCode가 정상적으로 왔는지 확인 \
    이러한 작업을 통해 액티비티간 데이터 전달이 잘 되었는지 알 수 있다 \
    추가적으로 Signup창에서 Login창으로 되돌아 왔을 때 현재시간을 toast로 보여준다.

    LoginActivity.kt
    ```
    val REQUEST_CODE_LOGIN_ACTIVITY = 1000
    startActivityForResult<SignupActivity>(REQUEST_CODE_LOGIN_ACTIVITY,"start_time" to s_time)
    ```

    SignupActivity.kt
    ```
        val simpleDateFormat - SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val e_time = simpleDateFormat.format(Date())

        startActivityForResult<LoginActivity>(Activity.RESULT_OK ,"end_time" to e_time)
    ```

    LoginActivity.kt
    ```
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super(requestCode, resultCode, data)

            if(requestCode == REQUEST_CODE_LOGIN_ACTIVITY || resultCode == Activity.RESULT_OK) {
                val e_time = data!!.getStringExtra("end_time")
                toast("End time: ${e_time}")
            }
        }
    ```

2. 🌙  __내부 DB__

    - __Shared Preference__ \
        간단한 데이터를 읽고 쓰기에 유용 \
        Key: Value 쌍으로 데이터를 저장 \
        [Context].getSharedPreference([Key],[Mode]) 문법으로 단일 인스턴스에 접근 \
        앱 초기 설정(알림 On, 광고 메시지 수신 동의), 편의 기능(자동로그인, 쿠키)등에 이용

    - __SQLite__ \
        Android OS에서 기본으로 제공하는 파일 기반 관계형 데이터베이스
        일반적인 RDBMS성능보다는 떨어지지만 사용이 간편하고 중소 규모의 데이터를 처리하기에는 성능도 충분

    - SharedPreference를 이용하여 로그인 정보 저장 

        LoginActivity.kt
        ```
        fun postLoginResponse(u_id: String, u_pw: String) {
            SharedPreferenceController.setUserID(this, u_id)
        }
        ```

        SharedPreferenceController.kt
        ```
        object SharedPreferenceController{
            val MY_ACCOUNT = "unique_string"

            fun setUserID(ctx: Context, time: String){
                val preference: SharedPreferences = ctx.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = preference.edit()
                editor.putString("u_id",time)
                editor.commit()
            }

            fun getUserID(ctx: Context, time: String){
                val preference: SharedPreferences = ctx.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
                return preference.getString("u_id","")
            }

            fun clearUserID(ctx: Context) {
                val preference: SharedPreferences = ctx.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = preference.edit()
                editor.clear()
                editor.commit()
            }
        }
        ```

        MainActivity.kt
        ```
        private fun configureTitleBar() {
            if(SharedPreferenceController.getUserID(this).isEmpty()){
                txt_toolbar_main_action.text = "로그인"
            } else {
                txt_toolbar_main_action.text = "로그아웃"
            }
        }
        ```

3. 🌙  __Fragment__

    - 재사용 가능한 '부분 Activity'의 개념
    - Activity와 같이 Layout, Lifecycle, Controller를 가지는 독립적인 모둘
    - 다른 Activity 위에 올라가 보여짐
    - Activity 실행 중에 추가, 제거가 가능.
    - Activity 는 하나 이상의 Fragment를 가짐.
    - Activity와 유사하게 생명주기가 존재 
    <img src="https://user-images.githubusercontent.com/35513039/66895648-44815800-f02e-11e9-87d3-ceffa9614dcf.png" width="50%" height="50%" />
    
     `onAttach()`-> `onCreate()` -> `onCreateView()` -> `onActivityCreated()` -> `onStart()` -> `onResume()` -> `onPause()` -> `onStop()` -> `onDestroyView()` -> `onDestroy()` -> `onDetach()`
    - Fragment는 상위 Activity의 생명주기에 직접적으로 영향을 받는다. 만일 상위 Activity가 소멸되면 그 위 모든 Fragment들도 소멸된다.
    - Fragment는 다른 Fragment위에도 올라갈 수 있다.
    - Activity간에는 Intent로 데이터를 전달하지만, Fragment간에는 Bundle로 데이터를 전달한다.
    - View가 완전히 생성된 이후에 호출되는 Fragment의 생명주기는 onActivityCreated()이다

4. 🌙  __FragmentPagerAdapter, FragmentStatePagerAdapter__

    - FragmentPagerAdapter \
    프레그먼트 갯수가 고정되었는 경우에 사용한다. 예를 들어 4개의 프레그먼트가 있다고 하자 FragmentPagerAdapter로 설정한 경우 프레그먼트들이 메모리에서 완전히 사라지지 않고 남아있다 생명주기 `onDestoryView()`까지만 실행되기 때문이다.


    Go to Fragment1 (Launch activity)
    ```
    Fragment1: onCreateView
    Fragment1: onStart
    Fragment2: onCreateView
    Fragment2: onStart
    ```

    Go to Fragment2
    ```
    Fragment3: onCreateView
    Fragment3: onStart
    ```

    Go to Fragment3
    ```
    Fragment1: onStop
    Fragment1: onDestroyView
    Fragment4: onCreateView
    Fragment4: onStart
    ```

    Go to Fragment4
    ```
    Fragment2: onStop
    Fragment2: onDestroyView
    ```

    - __FragmentStatePagerAdapter__ \
    프레그먼트 갯수가 변할 때, 많은 프레그먼트가 있을 때 사용한다. 프레그먼트가 `onDestory()`까지 실행되어 메모리에서 완전히 없어지게 된다. 메모리 관리에 효율적이다.

    Go to Fragment1 (Launch activity)
    ```
    Fragment1: onCreateView
    Fragment1: onStart
    Fragment2: onCreateView
    Fragment2: onStart
    ```

    Go to Fragment2
    ```
    Fragment3: onCreateView
    Fragment3: onStart
    ```

    Go to Fragment3
    ```
    Fragment1: onStop
    Fragment1: onDestroyView
    Fragment1: onDestroy
    Fragment1: onDetach
    Fragment4: onCreateView
    Fragment4: onStart
    ```

    Go to Fragment4
    ```
    Fragment2: onStop
    Fragment2: onDestroyView
    Fragment2: onDestroy
    Fragment2: onDetach

## 🔥 3차 세미나
1. 🌙  __Selector__

    상황에 따라 리소스를 다르게 설정해야 할 때가 있다 \
    selector 이미지 만들기

    action_selector.xml
    ```
    <selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:state_selected="false"
        android:drawable="@drawable/dot_default"/>
    <item
        android:state_selected="true"
        android:drawable="@drawable/dot_selected"/>
    </selector>
    ```

    MainActivity.kt
    ```
    private fun configureTitleBar() {
        if (SharedPreferenceController.getUserToken(this).isEmpty()) {
            img_toolbar_main_action.isSelected = false
        } else {
            img_toolbar_main_action.isSelected = true
        }

    }
    ```
2. 🌙  __Glide__

    Picasso 라이브러리와 매우 유사 \
    추가적으로 썸네일, 동영상 스틸 및 GIF 애니메이션 로딩 가능 \
    [Glide 라이브러리](https://github.com/bumptech/glide) 
    ```
    Glide.with(this)
         .load("https://user-images.githubusercontent.com/35513039/66894544-a7bdbb00-f02b-11e9-9bdb-073a7bbdd781.png")
         .into(img_fragment_silder_main)
    ```

    빌더 패턴으로 되어있고 3개의 파라미터를 요구한다. \
    with: 안드로이드의 많은 API를 이용하기 위해 필요 \
    load: 웹 상에서의 이미지 경로 URL or 안드로이드 리소스 ID or 로컬 파일 or URI \
    into: 다운로드 받은 이미지를 보여줄 이미지 뷰

3. 🌙  __RecyclerView__

    3.1 Data Class
    > 한 번의 Layout에 보여질 item들을 담을 Data 구조체

    3.2 Layout XML 
    > 반복적인 View UI를 구성하는 한 번의 UI Layout

    3.3 View Holder (Adapter 내부 Class)
    > 반복적인 View UI를 구성하는 Layout에 속한 View들의 ID를 변수에 저장

    3.4 RecyclerView Adapter
    > 반복적인 View UI를 구성하는 Layout에 속한 View와 Data Class의 item을 연결

    3.5 RecyclerView의 다양한 형태
    > 수직 스크롤 형태: default가 수직 스크롤이다
    rv_episode_overview_list.layoutManager = LinearLayoutManager(this) 

    >수평 스크롤 형태: rv_episode_overview_list.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false) 

    >그리드 형태: 두 번째인자는 한 줄에 몇 개의 item이 보일지 설정 \
    rv_episode_overview_list.layoutManager = GridLayoutManager(this, 3)
    
    >그리드 형태: 지그재그 형태로 각 열의 높이가 다를 때
    rv_episode_overview_list.layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)



## 🔥 4차 세미나

1. 🌙 __HTTP__

    HyperText(텍스트, 이미지, 동영상, 림크 등)를 전달하는 방식에 대한 약속,규칙,규약 \
    클라이언트 -> 서버 : HTTP Request \
    서버 -> 클라이언트 : HTTP Response

2. 🌙  __JSON__

    key, value 쌍의 집합으로 데이터를 표현하는 방법 \
    사람이 읽을 수 있는 텍스트 기반의 표준 포맷 \
    디버깅이 매우 쉬움

    ```
    data class Human{
        val name: String,
        val age: Int,
        val gender: Boolean,
        val hobby: ArrayList<String>
    }
    ```

3. 🌙  __REST__

    웹에 존재하는 모든 자원(하이퍼 텍스트)에 접근할 수 있도록 직관적인 URI를 부여하는 방법론 \
    3가지 구조로 구성 \
    1. 자원: URI(Uniform Resource identifier/Locator)
    2. 행위: HTTP Method(GET, POST, PUT, DELETE)를 통해 데이터의 조회, 삽입, 수정, 삭제 등 행위를 수행
    3. 표현: `http://restapi.example.com/sports/soccer/players/13`

4. 🌙  __Retrofit__

    [Retrofit 라이브러리](https://square.github.io/retrofit/)

    Retrofit 객체 생성
    ```
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://byunjkluz.ml:2424/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NetworkService::class.java)
    ```

    NetworkService.kt
    ```
    interface NetworkService{
        @POST("/api/auth/signin")
        fun postLoginResponse(
            @Header("Content-Type") content_type: String,
            @Body() body: JsonObject
            ):Call<PostLoginResponse>
    }
    ```

    LoginActivity.kt
    ```
    fun postLoginResponse(u_id: String, u_pw: String) {

        //id,password를 받아서 JSON객체로 만든다.
        var jsonObject = JSONObject()
        jsonObject.put("id", u_id)
        jsonObject.put("password", u_pw)

        /** networkService를 통해 실제로 통신을 요청
         *  application/x-www-form-urlencoded 는 해더로 전송된다.
         *  gsonObject 는 body로 전송된다.
         */
        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject
        val postLoginResponse: Call<PostLoginResponse> =
            networkService.postLoginResponse("application/json", gsonObject)

        postLoginResponse.enqueue(object : Callback<PostLoginResponse> {
            override fun onFailure(call: Call<PostLoginResponse>, t: Throwable) {
                Log.e("login failed",t.toString())
            }

            override fun onResponse(call: Call<PostLoginResponse>, response: Response<PostLoginResponse>) {
                if(response.isSuccessful){
                    if(response.body()!!.status == 201){
                        //Request Login
                        SharedPreferenceController.setUserToken(applicationContext, response.body()!!.data!!)
                        finish()
                    }
                }
            }
        })
    }
    ```

5. 🌙  __Application Class__

    Android Component들 사이에서 공유 가능한 전역 클래스 \
    앱이 실행될 때 가장 먼저 실행 

    실행 순서

    1. Application()을 상속받는 클래스 생성
    2. AndroidManifest.xml에 등록
    3. NetworkService 인터페이스(전역 객체)를 초기화

## 🔥 5차 세미나
- __디자이너와 클라이언트 합동 세미나__ \
[제플린](https://zeplin.io/)을 이용하여 디자이너와 커뮤니케이션

- __제플린__
    - 제플린(Zeplin)은 디자이너 및 개발자를 위한 공동 작업 응용 프로그램 제플린은 스케치 또는 포토샵과 연동하여 자동으로 작업한 결과물을 이미지 파일 Asset과 디자인 가이드로 생성 

    - 가이드를 생성하고 요소의 크기를 확인하는 이 모든 과정을 자동화하여 시간과 노력을 절약해줍니다. 

    - [참고 자료](http://blog.rightbrain.co.kr/?p=8427) 

- __디자이너가 작업한 UI를 작성하기__

    <img src="https://user-images.githubusercontent.com/35513039/67063352-7a931900-f1a1-11e9-9b9e-d9ffaab5a55f.png" width="50%" height="50%" />
   
    MainActivity.kt
    ```
    class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //툴바의 종 모양을 클릭했을 때 이벤트
        //Alert가 위에서 나온다.
        notice.onClick {
            Alerter.create(this@MainActivity)
                .setTitle("알림 입니다.")
                .setText("닫기를 눌러 주세요")
                .addButton("닫기",R.style.AlertButton, View.OnClickListener {
                    Alerter.hide()
                })
                .setBackgroundColorInt(Color.parseColor("#e74c3c"))
                .setIcon(R.drawable.icn_ios_notice)
                .show()
        }

        //학사일정 보러가기를 눌렀을 때 이벤트
        // CalenderActivity로 넘어감
        calender.onClick{
            startActivity<CalenderActivity>()
        }

        //news 의 문자열이 흐르게 하려면 text가 선택되어 있어야 한다.
        txt_main_news.isSelected = true

        //아래의 8개의 대시보드를 누르면 아이콘의 색상이 변한다.
        //다시 한번 더 누르면 하얀색으로 돌아온다.
        img_bubble.onClick { img_bubble.switchState() }
        img_community.onClick { img_community.switchState() }
        img_folio.onClick { img_folio.switchState() }
        img_info.onClick { img_info.switchState() }
        img_mail.onClick { img_mail.switchState() }
        img_news.onClick { img_news.switchState() }
        img_phone.onClick { img_phone.switchState() }
        img_library.onClick {img_library.switchState()}
        }
    }
    ```

    activity_main.xml
    ```
        <ScrollView xmlns:tools="http://schemas.android.com/tools"
                xmlns:android="http://schemas.android.com/apk/res/android"
                android:layout_height="match_parent" android:layout_width="match_parent"
                xmlns:app="http://schemas.android.com/apk/res-auto">

            <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    tools:context=".MainActivity"
                    android:orientation="vertical" >

                <!--툴바 가져오기-->
                <include
                        android:id="@+id/toolbar_main"
                        layout="@layout/toolbar_main"/>

                <!--툴바 아래의 학교 슬로건과 학사일정 버튼-->
                <RelativeLayout
                        android:background="@drawable/bg_backgroundimage_1"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:gravity="center">
                    <LinearLayout
                            android:gravity="center_horizontal"
                            android:orientation="vertical"
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            android:layout_marginTop="50dp"
                    >
                        <TextView
                                android:id="@+id/text"
                                android:gravity="center_horizontal"
                                android:textColor="#fff"
                                android:textSize="20sp"
                                android:text="인간을 위해 미래를 꿈꾸는 \n창의의 SeoulTech"
                                android:textStyle="bold"
                                android:layout_width="wrap_content"
                                android:layout_height="wrap_content"
                        />
                        <ImageView
                                android:id="@+id/calender"
                                android:src="@drawable/btn_calendar"
                                android:layout_width="wrap_content"
                                android:layout_height="wrap_content"
                                android:layout_marginTop="20dp"
                        />
                    </LinearLayout>
                </RelativeLayout>

                <!--NEWS text-->
                <TextView
                        android:id="@+id/txt_main_news"
                        android:ellipsize="marquee"
                        android:singleLine="true"
                        android:gravity="center"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:background="#FFFFFF"
                        android:textSize="12sp"
                        android:textColor="#373634"
                        android:lineSpacingExtra="14sp"
                        android:text="@string/news"
                        android:padding="20dp"
                />

                <!--background 지정을 위한 레이아웃-->
                <LinearLayout
                        android:background="@drawable/img_bg"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:orientation="vertical">

                <!--대학생활, 학생 교직원증, e-class 이미지 버튼-->
                <LinearLayout
                        android:layout_below="@+id/txt_main_news"
                        android:orientation="horizontal"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content">
                    <RelativeLayout
                            android:background="#99003e7e"
                            android:layout_weight="1"
                            android:layout_width="match_parent"
                            android:layout_height="154dp">
                        <ImageView
                                android:layout_width="wrap_content"
                                android:layout_height="wrap_content"
                                android:src="@drawable/icn_case_white"
                                android:layout_centerInParent="true"/>
                    </RelativeLayout>
                    <RelativeLayout
                            android:background="#99b5121b"
                            android:layout_weight="1"
                            android:layout_width="match_parent"
                            android:layout_height="154dp">
                        <ImageView
                                android:layout_width="82dp"
                                android:layout_height="71.1dp"
                                android:src="@drawable/icn_identification_white_2"
                                android:layout_centerInParent="true"/>
                    </RelativeLayout>
                    <RelativeLayout
                            android:background="#999fa1a4"
                            android:layout_weight="1"
                            android:layout_width="match_parent"
                            android:layout_height="154dp">
                        <ImageView
                                android:layout_width="wrap_content"
                                android:layout_height="wrap_content"
                                android:src="@drawable/icn_eclass_white"
                                android:layout_centerInParent="true"/>
                    </RelativeLayout>
                </LinearLayout>

                <!--8개의 아이콘과 아래의 게시글 제목-->
                <LinearLayout
                        android:orientation="vertical" android:layout_height="wrap_content"
                        android:layout_width="match_parent">
                    <GridLayout
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            tools:context=".MainActivity"
                            android:columnCount="4"
                            android:rowCount="2"
                            android:background="#0000"
                            android:orientation="horizontal"
                    >

                        <LinearLayout
                                android:background="#0000"
                                android:orientation="vertical"
                                android:padding="16dp"
                                android:gravity="center"
                                android:layout_width="30dp"
                                android:layout_height="wrap_content"
                                android:layout_columnWeight="1"
                                android:layout_rowWeight="1">

                            <com.github.zagum.switchicon.SwitchIconView
                                    android:id="@+id/img_library"
                                    app:si_enabled="false"
                                    app:si_no_dash="true"
                                    app:si_disabled_alpha=".8"
                                    app:si_disabled_color="#fff"
                                    app:si_tint_color="#F44336"
                                    android:scaleType="centerInside"
                                    android:src="@drawable/icn_book_white"
                                    android:layout_width="80dp"
                                    android:layout_height="80dp"
                            />
                        </LinearLayout>

                        <LinearLayout
                                android:background="#0000"
                                android:orientation="vertical"
                                android:padding="16dp"
                                android:gravity="center"
                                android:layout_width="30dp"
                                android:layout_height="wrap_content"
                                android:layout_columnWeight="1"
                                android:layout_rowWeight="1">

                            <com.github.zagum.switchicon.SwitchIconView
                                    android:id="@+id/img_bubble"
                                    app:si_enabled="false"
                                    app:si_no_dash="true"
                                    app:si_disabled_alpha=".8"
                                    app:si_disabled_color="#fff"
                                    app:si_tint_color="#00BCD4"
                                    android:scaleType="centerInside"
                                    android:src="@drawable/icn_bubble_white"
                                    android:layout_width="80dp"
                                    android:layout_height="80dp"
                            />
                        </LinearLayout>


                        <LinearLayout
                                android:background="#0000"
                                android:orientation="vertical"
                                android:padding="16dp"
                                android:gravity="center"
                                android:layout_width="30dp"
                                android:layout_height="wrap_content"
                                android:layout_columnWeight="1"
                                android:layout_rowWeight="1">

                            <com.github.zagum.switchicon.SwitchIconView
                                    android:id="@+id/img_mail"
                                    app:si_enabled="false"
                                    app:si_no_dash="true"
                                    app:si_disabled_alpha=".8"
                                    app:si_disabled_color="#fff"
                                    app:si_tint_color="#FFEB3B"
                                    android:scaleType="centerInside"
                                    android:src="@drawable/icn_mail_white"
                                    android:layout_width="80dp"
                                    android:layout_height="80dp"
                            />
                        </LinearLayout>


                        <LinearLayout
                                android:background="#0000"
                                android:orientation="vertical"
                                android:padding="16dp"
                                android:gravity="center"
                                android:layout_width="30dp"
                                android:layout_height="wrap_content"
                                android:layout_columnWeight="1"
                                android:layout_rowWeight="1">

                            <com.github.zagum.switchicon.SwitchIconView
                                    android:id="@+id/img_community"
                                    app:si_enabled="false"
                                    app:si_no_dash="true"
                                    app:si_disabled_alpha=".8"
                                    app:si_disabled_color="#fff"
                                    app:si_tint_color="#8BC34A"
                                    android:scaleType="centerInside"
                                    android:src="@drawable/icn_community_white"
                                    android:layout_width="80dp"
                                    android:layout_height="80dp"
                            />
                        </LinearLayout>


                        <LinearLayout
                                android:background="#0000"
                                android:orientation="vertical"
                                android:padding="16dp"
                                android:gravity="center"
                                android:layout_width="30dp"
                                android:layout_height="wrap_content"
                                android:layout_columnWeight="1"
                                android:layout_rowWeight="1">

                            <com.github.zagum.switchicon.SwitchIconView
                                    android:id="@+id/img_phone"
                                    app:si_enabled="false"
                                    app:si_no_dash="true"
                                    app:si_disabled_alpha=".8"
                                    app:si_disabled_color="#fff"
                                    app:si_tint_color="#3F51B5"
                                    android:scaleType="centerInside"
                                    android:src="@drawable/icn_number_white"
                                    android:layout_width="80dp"
                                    android:layout_height="80dp"
                            />
                        </LinearLayout>

                        <LinearLayout
                                android:background="#0000"
                                android:orientation="vertical"
                                android:padding="16dp"
                                android:gravity="center"
                                android:layout_width="30dp"
                                android:layout_height="wrap_content"
                                android:layout_columnWeight="1"
                                android:layout_rowWeight="1">

                            <com.github.zagum.switchicon.SwitchIconView
                                    android:id="@+id/img_info"
                                    app:si_enabled="false"
                                    app:si_no_dash="true"
                                    app:si_disabled_alpha=".8"
                                    app:si_disabled_color="#fff"
                                    app:si_tint_color="#FF9800"
                                    android:scaleType="centerInside"
                                    android:src="@drawable/icn_information_white"
                                    android:layout_width="80dp"
                                    android:layout_height="80dp"
                            />
                        </LinearLayout>

                        <LinearLayout
                                android:background="#0000"
                                android:orientation="vertical"
                                android:padding="16dp"
                                android:gravity="center"
                                android:layout_width="30dp"
                                android:layout_height="wrap_content"
                                android:layout_columnWeight="1"
                                android:layout_rowWeight="1">

                            <com.github.zagum.switchicon.SwitchIconView
                                    android:id="@+id/img_news"
                                    app:si_enabled="false"
                                    app:si_no_dash="true"
                                    app:si_disabled_alpha=".8"
                                    app:si_disabled_color="#fff"
                                    app:si_tint_color="#9C27B0"
                                    android:scaleType="centerInside"
                                    android:src="@drawable/icn_news_white"
                                    android:layout_width="80dp"
                                    android:layout_height="80dp"
                            />
                        </LinearLayout>

                        <LinearLayout
                                android:background="#0000"
                                android:orientation="vertical"
                                android:padding="16dp"
                                android:gravity="center"
                                android:layout_width="30dp"
                                android:layout_height="wrap_content"
                                android:layout_columnWeight="1"
                                android:layout_rowWeight="1">

                            <com.github.zagum.switchicon.SwitchIconView
                                    android:id="@+id/img_folio"
                                    app:si_enabled="false"
                                    app:si_no_dash="true"
                                    app:si_disabled_alpha=".8"
                                    app:si_disabled_color="#fff"
                                    app:si_tint_color="#E91E63"
                                    android:scaleType="centerInside"
                                    android:src="@drawable/icn_light_white"
                                    android:layout_width="80dp"
                                    android:layout_height="80dp"
                            />
                        </LinearLayout>

                    </GridLayout>
                    <LinearLayout
                            android:layout_margin="20dp"
                            android:background="@drawable/notice"
                            android:layout_gravity="center"
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            android:orientation="vertical"
                    />

                </LinearLayout>

                </LinearLayout>
            </LinearLayout>
        </ScrollView>
    ```


    - **추가된 라이브러리**

        - 알림창을 띄어주는 라이브러리 [Git Site](https://github.com/Tapadoo/Alerter)
            ```
            implementation "com.tapadoo.android:alerter:3.0.0"
            ```

            ![image](https://github.com/Tapadoo/Alerter/raw/master/documentation/alert_coloured.gif)


        - 아이콘의 색상이 바뀌는 라이브러리
            ```
            implementation 'com.github.zagum:Android-SwitchIcon:1.3.8'
            ```
            
            ![image](https://github.com/zagum/Android-SwitchIcon/raw/master/art/sample.gif)


## 🔥 6차 세미나
1. 🌙  **REST API**

    - **URI, URL** 
        |URI(Uniform Resource identifier|URL(Uniform Resource Locator|
        |:---:|:---:|
        |통합 자원 식별자|통합 자원 지시자|
        |자원을 나타내는 유일한 주소, 자원을 식별할 수 있는 문자열 고유하게 정보 리소스를 식별하고 위치를 지정 하위개념으로 URL |특정 서버의 한 리소스에 대한 구체적인 위치를 서술 네트워크 상 해당 자원이 어디 있는지 알려주는 규약|
        |어떤 자원의 위치를 의미하고 HTTP는 주어진 URI로 객체를 찾아오고 Method가 그 위치에 대한 행위를 뜻함|어떤 특정 지점의 위치 또는 파일 리소스에 접근하기 위한 주소|

    - **uri 예시**
        |Method|Path|info|
        |:---:|:---:|:---:|
        |GET|~/ranking/party/:party_cd/:isLike|정당별 의원 조회|
        |GET|~/ranking/party/:city_cd/:isLike|지역별 의원 조회|
        |GET|~/legislator/:idx|의원 상세 정보|
        |GET|~/api.calendar?type={type}&id={id}&year={year}&month={month}|특정 월 캘린더 리스트|
        |GET|~/api/artist/detail:artistiId|아티스트 정보 상세 조회|

    - **uri 작성요령**
        - 네이밍이 직관적 이어야 하고 형용사보다는 염사가 이해하기 좋음
        - 항상 일관된 규칙으로 작성해야 혼동되지 않음 


    - **API**
        - Application Programming Interface
        - 서버 애플리케이션의 기능을 사용하기 위한 방법/ 수단 
        - 구현 방식을 알지 못해도 서비스가 서로 통신 가능
        - 리소스에 대한 액세스 권한을 제공하고 보안과 제어를 유지할 수 있게 해주며 액세스 권한을 어떻게, 누구에게 제공할지 여부만 결정
        - URI는 서버 설계 도면/ API는 서버 사용 설명서
        - URI는 서버 구성 요소를 나타냄

    - **REST(Representational State Transfer)**
        - RESTful: REST의 원리를 따르느 시스템
        - 리소스 지향 아키텍쳐 -> 모든 거을 리소스 즉 명사로 표현함
    
    - **REST API**
        - Uniform Interface : HTTP 표준에만 따른다면, 어떠한 기술이라던지 사용이 가능한 인터페이스 스타일
        - Stateless(무상테성): HTTP Session과 같은 컨택스트 저장소에 상태정보를 저장하지 않음
        - Layered System: 대상 서버에 직접 연결되었는지, 또는 중간 서버를 통해 연결되었는지를 알 수 없음 중간 서버는 로드 밸런싱 기능이나 공유 캐시 기능을 제공함
        - Self-descriptiveness(자체 표현 구조): REST API 자체가 매우 쉬워서 API 메시지 자체만 보고도 API를 이해할 수 있음 리소스와 메서드를 이용해서 어떤 메서드에 무슨행위를 하는지를 알 수 있으며, 또한 메시지 포멧 역시 JSON을 이용해서 직관적으로 이해가 가능한 구조
        - Client-Server 구조: REST서버는 API를 제공하고 제공된 API를 이용해서 비즈니스 로직 처리 및 저장을 책임진다.
        - Cacheable: HTTP라는 기존의 웹 표준을 그대로 사용하기 때문에 캐싱 기능 적용이 가능하다.
        
        
## 🔥 7차 세미나
1. 🌙  **형상관리** \
끊임없이 소스코드 변화 상황을 모니터링 하고 관리하는 것이다. \
소스코드를 버전별로 관리할 수 있기 때문에 실수로 코드를 삭제하거나 수정 전으로 되돌리고 싶을 때 유용하게 사용 \
팀 프로젝트에서 사용하면, 누가 무엇을 어떵게 수정했는지 알 수 있기 때문에 로컬에서 각자 작성한 코드를 병합하거나 수정된 소스코드를 추적하는데도 사용

    - **SVN**
        - 중앙 집중식 버전 관리 시스템
        - 개발자 별 Local History 관리를 지원하지 않는다.
        - 중앙 서버에 에러가 생기면 모든 것이 잘 못된다.
        - 서버가 다운될 경우, 서버가 다시 복구될 때 까지 다른 사람과의 협업 및 버전관리가 어려움
        <img src="https://user-images.githubusercontent.com/35513039/67066161-348f8280-f1ac-11e9-9b6b-cbfd3dba2276.png" width="50%" height="50%" />

    - **GIT**
        - 분산형 버전 관리 시스템
        - 클라이언트들이 마지막 파일의 스냅샷을 가져오는 대신 저장소를 통째로 복제
        - 개발자 별로 Local History를 관리
        - 중앙 서버에 에러가 생겨도 클라이언트로부터 역복제를 통해 복구 가능
        - Commit한 내용에 실수가 있더라도, 중앙 서버에 바로 반영되지 않기 때문에 걱정이 없다.
        <img src="https://user-images.githubusercontent.com/35513039/67066191-57ba3200-f1ac-11e9-8194-ad3efa9a1df1.png" width="50%" height="50%" />

2. 🌙  **GitHub**
    - 버전관리 시스템인 Git을 이용하는 프로젝트들을 위한 원격저장소(서버)를 제공하는 서비스
    - 오픈소스는 무료, 비공개 프로젝트는 유료 정책
    - 저장소 크기의 제한이 없다.
    - 소유자 중심의 서비스를 제공하기 때문에, 특정 저장소에 접근하려면 아이디/저장소명으로 접근해야한다.

    - **용어**
        - **기여자(Contributor)**: 프로젝트의 소스에 수정하거나 추가한 사용자

        - **조직(Organization)**: 프로젝트와 관련된 사용자들을 초대하여 모임 하나의 organization에 여러개의 repository가 있을 수 있다.

        - **원격 저장소(Remote Repository)**: 파일이 원격 저장소 전용 서버에서 관리되면 여러 사람이 함께 공유하는 장소

        - **로컬 저장소(Local Repository)**: 개인 PC에 파일이 저장되는 개인 저장소. 내 PC에서 작업 하던 내용을 원격 저장소로 업로드할 수 있고, 원격 저장소에 있는 파일들을 불러올 수도 있다.

        - **브랜치(Branch)**: 작업자들이 버전관리를 위하여 저장소의 메인 프로젝트(master 브랜치)로부터 분리된 작업 환경. 다른 브램치에서의 변경 내용으로부터 독립적이기 때문에 여러 개발자가 동시에 작업을 진행할 수 있다.

        - **Clone**: Repository을 복제하여 자신의 로컬 저장소에 붙여넣는 것

        - **Pull**: 다른 사람이 원격 저장소에 기록한 변경사항을 내 로컬에도 적용하는 것

        - **Commit**: 파일들의 추가/변경 사항들에 대해 로컬 저장소에 기록하는 것 \
        시간 순으로 저장이 되며, 최근 커밋부터 거슬러 올라가면 과거 변경 이력을 확인 가능

        - **Push**: 로컬 저장소에 기록된 변경사항을 다른 사람들이 확인할 수 있도록 원격 저장소에도 반영하는 것

        - **Merge**: 하나 혹은 두개의 브랜치에서 변경된 코드들을 다른 브랜치의 코드와 합치는 것

