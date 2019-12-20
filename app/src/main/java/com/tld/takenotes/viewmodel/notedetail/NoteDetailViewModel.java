package com.tld.takenotes.viewmodel.notedetail;

import com.tld.takenotes.model.entity.Note;

import lombok.Getter;
import lombok.Setter;

public class NoteDetailViewModel
{
    public interface NoteDetailListener { }

    @Getter
    @Setter
    private Note note;

    private NoteDetailListener listener;

    public NoteDetailViewModel(NoteDetailListener listener) { this.listener = listener; }
}
