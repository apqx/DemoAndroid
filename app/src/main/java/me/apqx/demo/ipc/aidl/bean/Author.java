package me.apqx.demo.ipc.aidl.bean;

import android.os.Binder;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;

public class Author implements Parcelable {
    private String name;
    private int age;
    private Book book;


    protected Author(Parcel in) {
        // 从Parcel中读取数据
        name = in.readString();
        age = in.readInt();
        // 从Parcel读取Parcelable对象
        book = in.readParcelable(Thread.currentThread().getContextClassLoader());
    }

    public Author(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // 向Parcelable中写入数据
        dest.writeInt(age);
        dest.writeString(name);
        // 向Parcel写入Parcelable对象
        dest.writeParcelable(book, 0);
    }

    public static final Creator<Author> CREATOR = new Creator<Author>() {
        @Override
        public Author createFromParcel(Parcel in) {
            return new Author(in);
        }

        @Override
        public Author[] newArray(int size) {
            return new Author[size];
        }
    };
}
