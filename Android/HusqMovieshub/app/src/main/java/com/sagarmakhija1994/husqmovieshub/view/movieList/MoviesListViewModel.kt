package com.sagarmakhija1994.husqmovieshub.view.movieList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sagarmakhija1994.husqmovieshub.repository.MainNetworkRepository
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesListViewModel constructor(private val repository: MainNetworkRepository)  : ViewModel() {

    val movieResponseBody: MutableLiveData<Response<ResponseBody>> = MutableLiveData<Response<ResponseBody>>()
    val movieResponseError: MutableLiveData<Throwable> = MutableLiveData<Throwable>()
    var currentPage:Int = 1
    var listType:String = "1"
    fun getAllMovies() {//Always returning 401 (Might be the problem with Server)
        val getMovieApiResponse = repository.getMovieListV4(listType, currentPage.toString())
        getMovieApiResponse.enqueue( object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                movieResponseBody.postValue(response)
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                movieResponseError.postValue(t)
            }
        })
    }

    fun getMoviesByKeyword(query:String) {//Always returning 401 (Might be the problem with Server)
        val getMovieApiResponse = repository.getMovieListV3(query)
        getMovieApiResponse.enqueue( object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                movieResponseBody.postValue(response)
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                movieResponseError.postValue(t)
            }
        })
    }
}