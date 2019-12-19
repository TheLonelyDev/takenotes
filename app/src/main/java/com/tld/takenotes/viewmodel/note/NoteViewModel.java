package com.tld.takenotes.viewmodel.note;

public class NoteViewModel
{
    public interface NoteListener { }

    private NoteListener listener;

    public NoteViewModel(NoteListener listener) { this.listener = listener; }
}
