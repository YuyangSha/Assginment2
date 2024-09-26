package com.example.instrumentrentalapp

import android.os.Parcel
import android.os.Parcelable

data class Instrument(
    val name: String,
    val price: Int,
    val rating: Float,
    val type: String // 添加 type 属性
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readFloat(),
        parcel.readString() ?: "" // 添加 type 的反序列化
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(price)
        parcel.writeFloat(rating)
        parcel.writeString(type) // 添加 type 的序列化
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Instrument> {
        override fun createFromParcel(parcel: Parcel): Instrument {
            return Instrument(parcel)
        }

        override fun newArray(size: Int): Array<Instrument?> {
            return arrayOfNulls(size)
        }
    }
}



