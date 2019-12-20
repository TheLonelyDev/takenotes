package com.tld.takenotes.events;

import com.tld.takenotes.model.entity.Note;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public class SaveCurrentNote
{
    @Setter
    @Getter
    private Note note;
}
