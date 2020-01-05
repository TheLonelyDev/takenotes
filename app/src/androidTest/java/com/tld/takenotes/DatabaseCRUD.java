package com.tld.takenotes;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.tld.takenotes.domain.repository.RoomDatabase;
import com.tld.takenotes.model.dao.NoteDao;
import com.tld.takenotes.model.entity.Note;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.UUID;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class DatabaseCRUD {
    private NoteDao noteDao;
    private RoomDatabase db;

    @Before
    public void onCreate() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, RoomDatabase.class).allowMainThreadQueries().build();
        noteDao = db.getNoteDao();
    }

    @After
    public void onDestroy() {
        db.close();
    }

    @Test
    public void writeNote() {
        Note note = new Note();
        note.setId(UUID.randomUUID().toString());
        note.setName("New note...");
        note.setDetail("");

        noteDao.newNote(note);

        assertThat(noteDao.getNote(note.getId()).getId(), equalTo(note.getId()));
    }

    @Test
    public void deleteNote() {
        Note note = new Note();
        note.setId(UUID.randomUUID().toString());
        note.setName("New note...");
        note.setDetail("");

        noteDao.newNote(note);

        assertThat(noteDao.getNote(note.getId()).getId(), equalTo(note.getId()));

        noteDao.deleteNote(note);

        assertThat(noteDao.getNote(note.getId()), equalTo(null));
    }

    @Test
    public void updateNote() {
        Note note = new Note();
        note.setId(UUID.randomUUID().toString());
        note.setName("New note...");
        note.setDetail("");

        noteDao.newNote(note);

        note.setName("Updated");

        noteDao.updateNote(note);

        assertThat(noteDao.getNote(note.getId()).getDetail(), equalTo(note.getDetail()));
    }
}