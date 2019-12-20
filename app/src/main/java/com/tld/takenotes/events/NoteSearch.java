package com.tld.takenotes.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class NoteSearch
{
    @Getter
    @Setter
    private String keyword;
}
