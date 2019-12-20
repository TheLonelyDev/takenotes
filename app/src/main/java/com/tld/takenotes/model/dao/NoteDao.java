package com.tld.takenotes.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.tld.takenotes.model.entity.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void newNote(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Query("SELECT * FROM Note")
    LiveData<List<Note>> getAll();

    @Query("SELECT * FROM Note WHERE name LIKE :keyword OR detail LIKE :keyword")
    LiveData<List<Note>> searchNotes(String keyword);
}
