package com.iteso.sesion9.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Oscar Vargas
 * @since 26/02/18.
 *
 * @version 1.0.1 Add Parcelable Interfaz and code parameter
 */

public class ItemProduct implements Parcelable{
    private int code;
    private String title;
    private String description;
    private Integer image;
    private Store store;
    private Category category;

    public ItemProduct(){

    }


    ItemProduct(Parcel in) {
        this.code = in.readInt();
        this.title = in.readString();
        //this.store = in.readString();//checar
        this.description = in.readString();
        this.image = (Integer) in.readValue(Integer.class.getClassLoader());
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Category getCategory(){ return category; }

    public void setCategory(Category category){ this.category = category; }

    @Override
    public String toString() {
        return "ItemProduct{" +
                "code=" + code +
                ", title='" + title + '\'' +
                ", store='" + store + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                '}';
    }

    public ItemProduct(int code, String title, Store store, String description, Integer image) {
        this.code = code;
        this.title = title;
        this.store = store;
        this.description = description;
        this.image = image;
    }

    public ItemProduct(String title, Store store) {
        this.title = title;
        this.store = store;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.title);
        dest.writeString(this.store.getName());
        dest.writeString(this.description);
        dest.writeValue(this.image);
    }

    public static final Creator<ItemProduct> CREATOR = new Creator<ItemProduct>() {
        @Override
        public ItemProduct createFromParcel(Parcel source) {
            return new ItemProduct(source);
        }

        @Override
        public ItemProduct[] newArray(int size) {
            return new ItemProduct[size];
        }
    };
}

