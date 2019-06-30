package com.quintype.oxygen.models.collection

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MagazineResponse(parcel: Parcel) : Parcelable, Serializable {

    @SerializedName("collection")
    var mCollectionResponse: CollectionResponse? = null
    @SerializedName("images")
    var mMagazineImages: ArrayList<MagazineImages>? = null

    init {
        mCollectionResponse = parcel.readParcelable(CollectionResponse::class.java.classLoader)
        mMagazineImages = parcel.createTypedArrayList(MagazineImages)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(mCollectionResponse, flags)
        parcel.writeTypedList(mMagazineImages)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MagazineResponse> {
        override fun createFromParcel(parcel: Parcel): MagazineResponse {
            return MagazineResponse(parcel)
        }

        override fun newArray(size: Int): Array<MagazineResponse?> {
            return arrayOfNulls(size)
        }
    }

    fun clone(): MagazineResponse {
        //need to create a duplicate object
        val stringProject = Gson().toJson(this, MagazineResponse::class.java)
        return Gson().fromJson<MagazineResponse>(stringProject, MagazineResponse::class.java)
    }

}