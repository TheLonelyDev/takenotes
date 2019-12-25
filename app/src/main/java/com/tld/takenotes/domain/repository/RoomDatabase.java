package com.tld.takenotes.domain.repository;

import androidx.room.Database;

import com.tld.takenotes.model.dao.NoteDao;
import com.tld.takenotes.model.entity.Note;

@Database(entities = {Note.class}, version = RoomDatabase.VERSION)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {
    static final int VERSION = 1;

    public abstract NoteDao getNoteDao();
}