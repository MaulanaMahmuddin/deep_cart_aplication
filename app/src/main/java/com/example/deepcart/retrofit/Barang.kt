package com.example.deepcart.retrofit

import com.google.gson.annotations.SerializedName

data class Barang(

    @SerializedName("idBarang") var idBarang: Int? = null,
    @SerializedName("gBarang") var gBarang: String? = null,
    @SerializedName("nBarang") var nBarang: String? = null,
    @SerializedName("qtyBarang") var qtyBarang: Int? = null,
    @SerializedName("hrgBarang") var hrgBarang: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null

)