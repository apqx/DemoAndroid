package me.apqx.demo.ipc.aidl.bean

import android.os.Parcel
import android.os.Parcelable

class Publisher() : Parcelable {

    var company: String = ""

    companion object CREATOR : Parcelable.Creator<Publisher>{
        override fun createFromParcel(source: Parcel): Publisher {
            return Publisher(source)
        }

        override fun newArray(size: Int): Array<Publisher> {
            return Array(size) {
                Publisher()
            }
        }
    }

    private constructor(inParcel: Parcel) : this() {
        readFromParcel(inParcel)
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(company)
    }

    fun readFromParcel(inParcel: Parcel) {
        company = inParcel!!.readString()
    }

    override fun describeContents(): Int {
        return 0
    }
}