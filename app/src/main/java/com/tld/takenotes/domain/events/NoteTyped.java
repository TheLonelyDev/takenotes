package com.tld.takenotes.domain.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class NoteTyped {
    @Setter
    @Getter
    private String detail;
}
