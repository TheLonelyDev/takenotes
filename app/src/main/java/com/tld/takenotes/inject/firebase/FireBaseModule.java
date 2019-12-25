package com.tld.takenotes.inject.firebase;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;
import com.tld.takenotes.repository.NoteFirebaseDataSource;
import com.tld.takenotes.repository.NoteRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FireBaseModule {

    private FirebaseDatabase firebaseDatabase;

    public FireBaseModule(Application application) {
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    @Singleton
    @Provides
    FirebaseDatabase providesFireBaseDatabase() {
        return firebaseDatabase;
    }

    @Singleton
    @Provides
    NoteRepository firebaseRepository() {
        return new NoteFirebaseDataSource();
    }
}