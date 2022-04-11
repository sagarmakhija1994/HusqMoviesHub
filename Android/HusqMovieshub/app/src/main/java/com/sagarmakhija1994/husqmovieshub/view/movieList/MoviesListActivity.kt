package com.sagarmakhija1994.husqmovieshub.view.movieList

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sagarmakhija1994.husqmovieshub.`interface`.ApiInterface
import com.sagarmakhija1994.husqmovieshub.adapter.MovieItemAdapter
import com.sagarmakhija1994.husqmovieshub.databinding.ActivityMovieListBinding
import com.sagarmakhija1994.husqmovieshub.model.MovieModel
import com.sagarmakhija1994.husqmovieshub.repository.MainNetworkRepository
import com.sagarmakhija1994.husqmovieshub.repository.MyViewModelFactory
import com.sagarmakhija1994.husqmovieshub.utils.Constant
import com.sagarmakhija1994.husqmovieshub.utils.Constant.getJsonResponseFromRaw
import com.sagarmakhija1994.husqmovieshub.utils.Constant.hideProgressDialog
import com.sagarmakhija1994.husqmovieshub.utils.Constant.showProgressDialog
import java.lang.Exception


class MoviesListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieListBinding
    private lateinit var movieListViewModel: MoviesListViewModel
    private val retrofitService = ApiInterface.getInstance()
    private lateinit var movieAdapter: MovieItemAdapter
    private val movieItemList:ArrayList<MovieModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init(){
        movieListViewModel = ViewModelProvider(this, MyViewModelFactory(MainNetworkRepository(retrofitService))).get(
            MoviesListViewModel::class.java)
        movieAdapter = MovieItemAdapter(movieItemList,this)
        binding.rcvMovieList.adapter = movieAdapter
        binding.rcvMovieList.layoutManager = LinearLayoutManager(this)
        getMovie()
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            getMovie()
        }

        binding.imgSearch.setOnClickListener {
            getMoviesByKeyword()
        }
    }
    private fun getMovie(){
        showProgressDialog(this, "Loading", false)
        movieItemList.clear()
        /*for( i in 0 until adapterItemCount){
            movieAdapter.notifyItemChanged(i)
        }*/
        movieAdapter.notifyDataSetChanged()
        movieListViewModel.movieResponseBody.observe(this) {
            hideProgressDialog()
            it?.let {
                if(it.isSuccessful && (it.code() == 200 || it.code() == 201)){
                    val jsonObj = getJsonResponseFromRaw(it)
                    jsonObj?.let { jObj ->
                        try{
                            val jArray = jObj.getJSONArray("results")
                            for (i in 0 until jArray.length()){
                                val itemObject = jArray.getJSONObject(i)

                                val id = itemObject.getInt("id")
                                val title = itemObject.getString("title")
                                val original_title = itemObject.getString("original_title")
                                val overview = itemObject.getString("overview")
                                val poster_path = itemObject.getString("poster_path")
                                val rating = itemObject.getString("vote_average")
                                val adult = itemObject.getBoolean("adult")
                                val model = MovieModel(id,title,original_title,overview,poster_path,rating,adult)
                                movieItemList.add(model)
                                movieAdapter.notifyItemChanged(i)
                            }
                        }catch (e:Exception){
                            Log.e("error",""+e)
                        }
                    }
                }else{
                    Constant.showToast(this,"Fail to get movie list, please try again by its name!")
                }
            }
        }

        movieListViewModel.movieResponseError.observe(this) {
            hideProgressDialog()
            Log.e("getMovieError",""+it)
        }


        /**
        URL: https://api.themoviedb.org/4/list/1?page=1&api_key=955df425cadc4d65e1887a0cc444cf7d&language=en
        Working on Browser
        Always returning 401 (Might be the problem with Server)
        **/
        movieListViewModel.getAllMovies()

    }

    private fun getMoviesByKeyword(){
        val keyword = binding.editSearchKeyWord.text.toString()
        if(keyword.isEmpty()){
            Constant.showToast(this,"movie name must not be empty!")
            return
        }



        /**
        URL: https://api.themoviedb.org/3/search/movie?api_key=955df425cadc4d65e1887a0cc444cf7d&query=superman
        Working on Browser
        Always returning 401 (Might be the problem with Server)
        **/
        movieListViewModel.getMoviesByKeyword(keyword)
    }
}