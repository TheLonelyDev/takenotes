package com.tld.takenotes.repository;

import androidx.lifecycle.LiveData;

import com.tld.takenotes.model.entity.Note;

import java.util.List;

public class NoteFirebaseDataSource implements NoteRepository {
    @Override
    public long newNote(Note note) {
        return 1;
    }

    @Override
    public void updateNote(Note note) {

    }

    @Override
    public void deleteNote(Note note) {

    }

    @Override
    public LiveData<List<Note>> getAll() {
        return new LiveData<List<Note>>() { };
    }

    @Override
    public LiveData<List<Note>> searchNotes(String keyword) {
        return new LiveData<List<Note>>() { };
    }
}