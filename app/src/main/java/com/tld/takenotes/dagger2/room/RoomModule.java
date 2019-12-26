package com.tld.takenotes.dagger2.room;

import android.app.Application;

import androidx.room.Room;

import com.tld.takenotes.domain.repository.NoteDataSource;
import com.tld.takenotes.domain.repository.NoteRepository;
import com.tld.takenotes.domain.repository.RoomDatabase;
import com.tld.takenotes.model.dao.NoteDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private RoomDatabase roomDatabase;

    public RoomModule(Application application) {
        roomDatabase = Room.databaseBuilder(application, RoomDatabase.class, "demo-db").build();
    }

    @Singleton
    @Provides
    RoomDatabase providesRoomDatabase() {
        return roomDatabase;
    }

    @Singleton
    @Provides
    NoteDao providesNoteDao(RoomDatabase roomDatabase) {
        return roomDatabase.getNoteDao();
    }

    @Singleton
    @Provides
    NoteRepository noteRepository(NoteDao noteDao) {
        return new NoteDataSource(noteDao);
    }

}