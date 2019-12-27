package com.tld.takenotes.domain.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class NoteSearch {
    @Setter
    @Getter
    private String keyword;
}
