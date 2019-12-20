package com.tld.takenotes.model.entity;

import org.parceler.Parcel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Parcel(
        value = Parcel.Serialization.BEAN, // <-- requires getters/setters if set
        analyze = { Note.class })
public class Note
{
    public Note() {}

    String name;

    String detail;

    public String getName() { return name; }
    public String getDetail() { return detail; }

    public void setName(String name) { this.name = name; }
    public void setDetail(String detail) { this.detail = detail; }
}
