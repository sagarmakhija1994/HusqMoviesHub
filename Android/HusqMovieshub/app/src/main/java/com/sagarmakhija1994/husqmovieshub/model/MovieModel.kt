package com.sagarmakhija1994.husqmovieshub.model

data class MovieModel(var id: Int, var title: String, var originalTitle: String,
                      var overview: String, var posterPath: String, var rating: String,
                      var adult: Boolean)