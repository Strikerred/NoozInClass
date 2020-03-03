package com.faustogomez.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.faustogomez.news.network.Article
import com.faustogomez.news.network.ArticleResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class MainActivity : AppCompatActivity() {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit: Retrofit? = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl("https://api.nytimes.com/svc/")
        .build()

    private var api: API = retrofit!!.create<API>(API::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getPopularArticles()
    }

    private fun getPopularArticles() = GlobalScope.launch (Dispatchers.Main){
        val firstResult: Article = api.getPopularArticlesAsync(BuildConfig.API_KEY).results.first()
        introText.text = firstResult.title
    }
}

interface API{
    @GET("/mostpopular/v2/viewed/1.json")
    suspend fun getPopularArticlesAsync(@Query("api-key") apiKey:String): ArticleResponse
}
