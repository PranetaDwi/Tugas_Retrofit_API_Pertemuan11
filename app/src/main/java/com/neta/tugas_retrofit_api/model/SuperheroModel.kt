package com.neta.tugas_retrofit_api.model

import com.google.gson.annotations.SerializedName

data class SuperheroModel(
    @SerializedName("result")
    val `result` : List<SuperheroData>
)

data class SuperheroData(
    @SerializedName ("id")
    val `id`: Int,
    @SerializedName ("title")
    val `title`: String,
    @SerializedName ("image")
    val `image`: String
)
