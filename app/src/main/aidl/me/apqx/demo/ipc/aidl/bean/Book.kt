package me.apqx.demo.ipc.aidl.bean

import android.os.Parcel
import android.os.Parcelable

class Book() : Parcelable{
    var name: String = ""
    var price: Int = 0
    var publisher: Publisher? = null

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(source: Parcel): Book {
            return Book(source)
        }

        override fun newArray(size: Int): Array<Book> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    private constructor(inParcel: Parcel) : this() {
        readFromParcel(inParcel)
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeInt(price)
        dest.writeParcelable(publisher, 0)
    }

    fun readFromParcel(inParcel: Parcel) {
        name = inParcel.readString()
        price = inParcel.readInt()
        publisher = inParcel.readParcelable(Thread.currentThread().contextClassLoader)
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}