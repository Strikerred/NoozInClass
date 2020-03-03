package com.faustogomez.news.network

import android.util.Log
import com.faustogomez.news.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class NewsAPI(){
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit: Retrofit? = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl("https://api.nytimes.com/svc/")
        .build()

    private var api: API = retrofit!!.create<API>(API::class.java)

    suspend fun getPopularArticles(): List<Article>?{
        return try{
            val response = api.getPopularArticlesAsync(BuildConfig.API_KEY)
            response.results

        }catch (e: Exception){
            Log.e(e.toString(), e.localizedMessage)
            null
        }
    }
}

interface API{
    @GET("mostpopular/v2/viewed/1.json")
    suspend fun getPopularArticlesAsync(@Query("api-key") apiKey:String): ArticleResponse
}