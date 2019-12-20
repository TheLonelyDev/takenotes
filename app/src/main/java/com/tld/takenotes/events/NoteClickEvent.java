package com.tld.takenotes.events;

import com.tld.takenotes.model.entity.Note;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class NoteClickEvent {
    @Getter
    @Setter
    private Note note;
}
