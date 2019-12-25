package com.tld.takenotes.domain.events;

import com.tld.takenotes.model.entity.Note;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class NoteClickEvent {
    @Setter
    @Getter
    private Note note;
}
