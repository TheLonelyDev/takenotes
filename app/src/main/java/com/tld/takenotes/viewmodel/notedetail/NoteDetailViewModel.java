package com.tld.takenotes.viewmodel.notedetail;

public class NoteDetailViewModel
{
    public interface NoteDetailListener { }

    private NoteDetailListener listener;

    public NoteDetailViewModel(NoteDetailListener listener) { this.listener = listener; }
}
