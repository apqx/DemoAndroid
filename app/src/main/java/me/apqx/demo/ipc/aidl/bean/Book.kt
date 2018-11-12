package me.apqx.demo.ipc.aidl.bean

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Book")
data class Book(@PrimaryKey(autoGenerate = true) var id: Int = 0,
                @ColumnInfo(name = "name") var name: String? = "",
                @ColumnInfo(name = "price") var price: Int = 0
                ) : Parcelable{

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(source: Parcel): Book {
            return Book(source)
        }

        override fun newArray(size: Int): Array<Book> {
            return Array(size) {
                Book()
            }
        }
    }

    private constructor(inParcel: Parcel) : this() {
        readFromParcel(inParcel)
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeInt(price)
    }

    private fun readFromParcel(inParcel: Parcel) {
        name = inParcel.readString()
        price = inParcel.readInt()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "id = $id; name = $name"
    }
}