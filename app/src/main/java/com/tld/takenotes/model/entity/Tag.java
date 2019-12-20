package com.tld.takenotes.model.entity;

import android.graphics.Color;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Tag {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Color color;
}
