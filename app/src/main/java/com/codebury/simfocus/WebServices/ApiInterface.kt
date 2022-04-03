package com.codebury.simfocus.WebServices

import com.codebury.simfocus.ModelData.LogInDataClass
import com.codebury.simfocus.ModelData.RegisterDataX
import com.google.gson.GsonBuilder
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface ApiInterface {
    @POST("/api/email-register")
    fun registerUser(@Body userData: RegisterDataX): Call<RegisterDataX>
    @Headers("Accept: application/json")
    @POST("/api/login")
    fun signInUser(@Body userData: LogInDataClass ): Call<LogInDataClass>

    companion object {
        private val okHttpClientBuilder = OkHttpClient.Builder().apply {

                addInterceptor(OkHttpProfilerInterceptor())

        }
        var gson = GsonBuilder()
            .setLenient()
            .create()
        fun getRetrofitObject(): ApiInterface{

            val retrofit =Retrofit.Builder().client(okHttpClientBuilder.build()).baseUrl("https://5f17-14-99-102-226.ngrok.io").addConverterFactory(GsonConverterFactory.create(
                gson))
                    .build().create(ApiInterface::class.java)
            return retrofit
        }


    }
}