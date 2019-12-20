package com.tld.takenotes.model.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.parceler.Parcel;

@Entity
@Parcel(
        value = Parcel.Serialization.BEAN, // <-- requires getters/setters if set
        analyze = {Note.class})
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;

    public String detail;


    public Note() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
