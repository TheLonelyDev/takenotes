package com.tld.takenotes.viewmodel.tag;

import com.tld.takenotes.model.entity.Note;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class TagClickEvent {
    @Getter
    @Setter
    private Note note;
}