package com.android.work.ipc.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fanlongbo on 2018/2/1.
 * aidl通信时传递的对象需要实现Parcelable接口
 */

public class Book implements Parcelable {
    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.id);
    }

    public Book() {
    }

    protected Book(Parcel in) {
        this.name = in.readString();
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
