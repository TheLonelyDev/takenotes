package com.tld.takenotes.dagger2.app;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.tld.takenotes.dagger2.room.RoomModule;
import com.tld.takenotes.model.dao.NoteDao;
import com.tld.takenotes.domain.repository.NoteRepository;
import com.tld.takenotes.domain.repository.RoomDatabase;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, RoomModule.class, NetworkModule.class})
public interface AppComponent {
    SharedPreferences sharedPreferences();

    NoteDao noteDao();

    RoomDatabase roomDatabase();

    NoteRepository noteRepository();

    Gson gson();
}
