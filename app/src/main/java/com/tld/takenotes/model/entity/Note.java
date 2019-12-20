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
    private String name;

    @Getter @Setter
    private String detail;
}
