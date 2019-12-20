package com.tld.takenotes.repository;

import androidx.lifecycle.LiveData;

import com.tld.takenotes.model.entity.Note;

import java.util.List;

public interface NoteRepository {
    void newNote(Note note);

    void updateNote(Note note);

    void deleteNote(Note note);

    LiveData<List<Note>> getAll();

    LiveData<List<Note>> searchNotes(String keyword);
}

