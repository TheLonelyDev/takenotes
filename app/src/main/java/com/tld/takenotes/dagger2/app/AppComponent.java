package com.tld.takenotes.inject.app;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.tld.takenotes.inject.room.RoomModule;
import com.tld.takenotes.model.dao.NoteDao;
import com.tld.takenotes.repository.NoteRepository;
import com.tld.takenotes.repository.RoomDatabase;

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
