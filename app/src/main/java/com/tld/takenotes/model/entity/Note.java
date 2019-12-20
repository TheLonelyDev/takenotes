package com.tld.takenotes.model.entity;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.parceler.Parcel;

@Entity
@Parcel(
        value = Parcel.Serialization.BEAN,
        analyze = {Note.class})
public class Note extends BaseObservable  {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;

    public String detail;


    public Note() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
