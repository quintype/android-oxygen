package com.quintype.oxygen.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */
class CardMetadata protected constructor(`in`: Parcel) : Parcelable, Serializable {

    @SerializedName("attributes")
    private var attributes: JsonObject? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeByte((if (attributes == null) 0 else 1).toByte())
        if (attributes != null)
            dest.writeString(this.attributes!!.toString())
    }

    init {
        if (`in`.readByte().toInt() != 0) {
            this.attributes = JsonParser().parse(`in`.readString()) as JsonObject
        }
    }

    fun attributes(): JsonObject? {
        return this.attributes
    }

    companion object CREATOR : Parcelable.Creator<CardMetadata> {
        override fun createFromParcel(parcel: Parcel): CardMetadata {
            return CardMetadata(parcel)
        }

        override fun newArray(size: Int): Array<CardMetadata?> {
            return arrayOfNulls(size)
        }
    }
}