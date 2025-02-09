package com.tld.takenotes.domain.repository;

import androidx.lifecycle.LiveData;

import com.tld.takenotes.model.dao.NoteDao;
import com.tld.takenotes.model.entity.Note;

import java.util.List;

import javax.inject.Inject;

public class NoteDataSource implements NoteRepository {
    private final NoteDao noteDao;

    @Inject
    public NoteDataSource(NoteDao productDao) {
        this.noteDao = productDao;
    }

    @Override
    public long newNote(Note note) {
        return noteDao.newNote(note);
    }

    @Override
    public void updateNote(Note note) {
        noteDao.updateNote(note);
    }

    @Override
    public void deleteNote(Note note) {
        noteDao.deleteNote(note);
    }

    @Override
    public Note getNote(String id) {
        return noteDao.getNote(id);
    }

    @Override
    public LiveData<List<Note>> searchNotes(String keyword) {
        return noteDao.searchNotes(keyword);
    }
}