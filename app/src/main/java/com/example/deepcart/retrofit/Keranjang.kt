package com.example.deepcart.retrofit

import com.google.gson.annotations.SerializedName

data class Keranjang (

    @SerializedName("idKeranjang"   ) var idKeranjang   : Int?    = null,
    @SerializedName("namaKeranjang" ) var namaKeranjang : String? = null,
    @SerializedName("created_at"    ) var createdAt     : String? = null,
    @SerializedName("updated_at"    ) var updatedAt     : String? = null

)