package com.tld.takenotes.events;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public class NoteSearch {
    @Setter
    private String keyword;

    public String getKeyword() {
        return String.format("%%%s%%", this.keyword);
    }
}
