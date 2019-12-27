package com.tld.takenotes.domain.repository;

import androidx.lifecycle.LiveData;

import com.tld.takenotes.model.entity.Note;

import java.util.List;

public interface NoteRepository {
    long newNote(Note note);

    void updateNote(Note note);

    void deleteNote(Note note);

    Note getNote(String id);

    LiveData<List<Note>> searchNotes(String keyword);
}

