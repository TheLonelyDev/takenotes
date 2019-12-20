package com.tld.takenotes.model.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.parceler.Parcel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Parcel(
        value = Parcel.Serialization.BEAN, // <-- requires getters/setters if set
        analyze = { Note.class })
public class Note
{
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;

    public String detail;


    public Note() {}

    public String getName() { return name; }
    public String getDetail() { return detail; }

    public void setName(String name) { this.name = name; }
    public void setDetail(String detail) { this.detail = detail; }
}
