package com.tld.takenotes.model.entity;

import org.parceler.Parcel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Parcel
@AllArgsConstructor
public class Note
{
    @Getter @Setter
    public String name;

    @Getter @Setter
    public String detail;
}
