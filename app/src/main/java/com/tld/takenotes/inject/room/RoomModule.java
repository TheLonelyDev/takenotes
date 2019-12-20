package com.tld.takenotes.inject.room;

import android.app.Application;

import androidx.room.Room;

import com.tld.takenotes.model.dao.NoteDao;
import com.tld.takenotes.repository.DemoDatabase;
import com.tld.takenotes.repository.NoteDataSource;
import com.tld.takenotes.repository.NoteRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private DemoDatabase demoDatabase;

    public RoomModule(Application application) {
        demoDatabase = Room.databaseBuilder(application, DemoDatabase.class, "demo-db").allowMainThreadQueries().build();
    }

    @Singleton
    @Provides
    DemoDatabase providesRoomDatabase() {
        return demoDatabase;
    }

    @Singleton
    @Provides
    NoteDao providesProductDao(DemoDatabase demoDatabase) {
        return demoDatabase.getNoteDao();
    }

    @Singleton
    @Provides
    NoteRepository productRepository(NoteDao productDao) {
        return new NoteDataSource(productDao);
    }

}