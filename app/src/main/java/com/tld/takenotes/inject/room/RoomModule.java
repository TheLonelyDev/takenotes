package com.tld.takenotes.inject.room;

import android.app.Application;

import androidx.room.Room;

import com.tld.takenotes.model.dao.NoteDao;
import com.tld.takenotes.repository.NoteDataSource;
import com.tld.takenotes.repository.NoteRepository;
import com.tld.takenotes.repository.RoomDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private RoomDatabase roomDatabase;

    public RoomModule(Application application) {
        roomDatabase = Room.databaseBuilder(application, RoomDatabase.class, "demo-db").allowMainThreadQueries().build();
    }

    @Singleton
    @Provides
    RoomDatabase providesRoomDatabase() {
        return roomDatabase;
    }

    @Singleton
    @Provides
    NoteDao providesProductDao(RoomDatabase roomDatabase) {
        return roomDatabase.getNoteDao();
    }

    @Singleton
    @Provides
    NoteRepository productRepository(NoteDao productDao) {
        return new NoteDataSource(productDao);
    }

}