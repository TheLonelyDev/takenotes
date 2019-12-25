package com.tld.takenotes.domain.events;

import com.tld.takenotes.model.entity.Note;

import lombok.Getter;
import lombok.Setter;


public class NoteEvent {
    @Getter
    @Setter
    private Note note;
}
