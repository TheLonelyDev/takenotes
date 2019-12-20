package com.tld.takenotes.inject.bus;

import com.tld.takenotes.events.CreateNewNote;
import com.tld.takenotes.events.DeleteCurrentNote;
import com.tld.takenotes.events.NoteClickEvent;
import com.tld.takenotes.events.NoteSearch;
import com.tld.takenotes.events.SaveCurrentNote;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.subjects.PublishSubject;

@Module
public class BusModule {
    @Provides
    @Singleton
    static PublishSubject<NoteClickEvent> provideOnNoteClicked() {
        return PublishSubject.create();
    }

    @Provides
    @Singleton
    static PublishSubject<CreateNewNote> provideCreateNewNote() {
        return PublishSubject.create();
    }

    @Provides
    @Singleton
    static PublishSubject<NoteSearch> provideNoteSearch() {
        return PublishSubject.create();
    }

    @Provides
    @Singleton
    static PublishSubject<DeleteCurrentNote> provideDeleteCurrentNote() {
        return PublishSubject.create();
    }

    @Provides
    @Singleton
    static PublishSubject<SaveCurrentNote> provideSaveCurrentNote() { return PublishSubject.create(); }
}