package com.sagarmakhija1994.husqmovieshub.`interface`

import com.sagarmakhija1994.husqmovieshub.utils.ConstantData
import com.sagarmakhija1994.husqmovieshub.utils.ConstantData.URL_GET_MOVIE_LIST_V3
import com.sagarmakhija1994.husqmovieshub.utils.ConstantData.URL_GET_MOVIE_LIST_V4
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    companion object {
        private var retrofitService: ApiInterface? = null
        fun getInstance() : ApiInterface {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(ConstantData.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ApiInterface::class.java)
            }
            return retrofitService!!
        }
    }

    @POST(URL_GET_MOVIE_LIST_V4)
    fun getMovieListV4(@Header("Content-Type") ContentType: String,
                     @Header("Authorization") authorizationId: String,
                     @Path("type") type:String,
                     @Query("page") page:String,
                     @Query("api_key") api_key:String,
                     @Query("language") language:String): Call<ResponseBody>

    @POST(URL_GET_MOVIE_LIST_V3)
    fun getMovieListV3(@Header("Content-Type") ContentType: String,
                     @Header("Authorization") authorizationId: String,
                     @Query("api_key") api_key:String,
                     @Query("query") query:String): Call<ResponseBody>

}