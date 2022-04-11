package com.sagarmakhija1994.husqmovieshub.repository

import com.sagarmakhija1994.husqmovieshub.`interface`.ApiInterface
import com.sagarmakhija1994.husqmovieshub.utils.ConstantData.API_KEY_V3
import com.sagarmakhija1994.husqmovieshub.utils.ConstantData.AUTHORIZATION_KEY
import com.sagarmakhija1994.husqmovieshub.utils.ConstantData.CONTENT_TYPE_APPLICATION_JSON_UTF8
import com.sagarmakhija1994.husqmovieshub.utils.ConstantData.LANGUAGE_EN

class MainNetworkRepository constructor(private val retrofitService: ApiInterface) {
    fun getMovieListV4(listType:String, pageNumber:String) = retrofitService.getMovieListV4(CONTENT_TYPE_APPLICATION_JSON_UTF8,
        AUTHORIZATION_KEY,listType,pageNumber, API_KEY_V3, LANGUAGE_EN)

    fun getMovieListV3(keyword:String) = retrofitService.getMovieListV3(CONTENT_TYPE_APPLICATION_JSON_UTF8,
        AUTHORIZATION_KEY, API_KEY_V3, keyword)
}