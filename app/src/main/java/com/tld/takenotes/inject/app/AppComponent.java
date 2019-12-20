package com.tld.takenotes.inject.app;

import android.content.SharedPreferences;

import com.tld.takenotes.inject.room.RoomModule;
import com.tld.takenotes.model.dao.NoteDao;
import com.tld.takenotes.repository.DemoDatabase;
import com.tld.takenotes.repository.NoteRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, RoomModule.class})
public interface AppComponent
{
    SharedPreferences sharedPreferences();

    NoteDao noteDao();
    DemoDatabase demoDatabase();
    NoteRepository noteRepository();
}
