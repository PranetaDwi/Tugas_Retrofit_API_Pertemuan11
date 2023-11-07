package com.neta.tugas_retrofit_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.neta.tugas_retrofit_api.databinding.ActivityMainBinding
import com.neta.tugas_retrofit_api.model.SuperheroData
import com.neta.tugas_retrofit_api.model.SuperheroModel
import com.neta.tugas_retrofit_api.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var superheroAdapter: SuperheroAdapter
    private lateinit var apiService: ApiService // ubah nama variabel menjadi apiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvHero.layoutManager = LinearLayoutManager(this)

        superheroAdapter = SuperheroAdapter(emptyList<SuperheroData>()){SuperheroData->

        } // ubah menjadi emptyList()

        binding.rvHero.adapter = superheroAdapter

        val retrofit = Retrofit.Builder()
            .baseUrl("https://demo.lazday.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java) // inisialisasi apiService

        apiService.getSuperheroData().enqueue(object : Callback<SuperheroModel> {
            override fun onResponse(call: Call<SuperheroModel>, response: Response<SuperheroModel>) {
                if (response.isSuccessful) {
                    val heroModel = response.body()
                    val heroes = heroModel?.result ?: emptyList()
                    superheroAdapter.setData(heroes) // ubah menjadi superheroAdapter
                }
            }

            override fun onFailure(call: Call<SuperheroModel>, t: Throwable) {
                // tangani kegagalan
            }
        })

    }
}
