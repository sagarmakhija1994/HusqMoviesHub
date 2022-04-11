package com.sagarmakhija1994.husqmovieshub.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sagarmakhija1994.husqmovieshub.R
import com.sagarmakhija1994.husqmovieshub.model.MovieModel
import com.sagarmakhija1994.husqmovieshub.utils.ConstantData.Image_BASE_URL
import com.sagarmakhija1994.husqmovieshub.utils.squre_views.SquareImageWidth

class MovieItemAdapter(private val dataSet: ArrayList<MovieModel>, private val activity: Activity) :
    RecyclerView.Adapter<MovieItemAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgCover: SquareImageWidth = view.findViewById(R.id.imgCover)
        val txtTitle: TextView = view.findViewById(R.id.txtTitle)
        val txtOgTitle: TextView = view.findViewById(R.id.txtOgTitle)
        val txtRating: TextView = view.findViewById(R.id.txtRating)
        val txtAdult: TextView = view.findViewById(R.id.txtAdult)
        val txtOverview: TextView = view.findViewById(R.id.txtOverview)
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_movie_item, viewGroup, false)

        return ViewHolder(view)
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val dataModel = dataSet[position]
        Glide.with(activity).load(Image_BASE_URL+dataModel.posterPath).into(viewHolder.imgCover)
        viewHolder.txtTitle.text = dataModel.title
        viewHolder.txtOgTitle.text = dataModel.originalTitle
        viewHolder.txtRating.text = dataModel.rating
        viewHolder.txtAdult.text = "18+: ${dataModel.adult}"
        viewHolder.txtOverview.text = dataModel.overview
    }
    override fun getItemCount() = dataSet.size
}