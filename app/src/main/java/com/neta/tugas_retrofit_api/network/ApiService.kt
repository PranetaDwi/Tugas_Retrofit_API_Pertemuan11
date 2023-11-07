package com.neta.tugas_retrofit_api.network

import com.neta.tugas_retrofit_api.model.SuperheroModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("rest-api-sample/data.php")
    fun getSuperheroData(): Call<SuperheroModel>
}