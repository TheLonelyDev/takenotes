package com.tld.takenotes.dagger2.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.google.gson.Gson;
import com.tld.takenotes.dagger2.room.RoomModule;
import com.tld.takenotes.domain.api.bing.BingInterface;
import com.tld.takenotes.domain.repository.NoteRepository;
import com.tld.takenotes.domain.repository.RoomDatabase;
import com.tld.takenotes.model.dao.NoteDao;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, RoomModule.class, NetworkModule.class})
public interface AppComponent {
    NoteDao noteDao();

    RoomDatabase roomDatabase();

    NoteRepository noteRepository();

    BingInterface bingInterface();

    Gson gson();

    Application application();

    Context context();

    Resources resources();
}
