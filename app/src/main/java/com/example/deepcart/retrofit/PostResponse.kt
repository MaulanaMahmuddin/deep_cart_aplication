package com.example.deepcart.retrofit

import com.google.gson.annotations.SerializedName

data class PostResponse (
    @SerializedName("idVKeranjang"  ) var idVKeranjang  : Int?       = null,
    @SerializedName("idKeranjang"   ) var idKeranjang   : Int?       = null,
    @SerializedName("idBarang"      ) var idBarang      : Int?       = null,
    @SerializedName("gBarang"       ) var gBarang       : String?    = null,
    @SerializedName("nBarang"       ) var nBarang       : String?    = null,
    @SerializedName("hrgBarang"     ) var hrgBarang     : String?    = null,
    @SerializedName("qtyVKeranjang" ) var qtyVKeranjang : Int?       = null,
    @SerializedName("keranjang"     ) var keranjang     : Keranjang? = Keranjang(),
    @SerializedName("barang"        ) var barang        : Barang?    = Barang()

)

{
    fun calculateTotal(qtyBarang: Int): Double {
        val hrgBarangAsDouble = hrgBarang?.toDoubleOrNull() ?: 0.0
        return hrgBarangAsDouble * qtyBarang
    }
}

