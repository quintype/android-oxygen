package com.quintype.oxygen.models.latest.home.magazines

import com.google.gson.annotations.SerializedName

data class NewMagazineResponseModel(
    @SerializedName("magazines")
    val magazines: ArrayList<MagazinesItem>?
)