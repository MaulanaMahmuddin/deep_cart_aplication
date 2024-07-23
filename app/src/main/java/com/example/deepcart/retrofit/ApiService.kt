package com.example.deepcart.retrofit
import retrofit2.Call;
import retrofit2.http.GET;

interface ApiService {
    @GET("viewKeranjang")
    fun getViewKeranjang(): Call<ArrayList<PostResponse>>
}