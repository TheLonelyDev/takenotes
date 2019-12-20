package com.tld.takenotes.repository;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.tld.takenotes.model.dao.NoteDao;
import com.tld.takenotes.model.entity.Note;

@Database(entities = {Note.class}, version = DemoDatabase.VERSION)
public abstract class DemoDatabase extends RoomDatabase {
    static final int VERSION = 1;

    public abstract NoteDao getNoteDao();
}