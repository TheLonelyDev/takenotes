package com.tld.takenotes.dagger2.bus;

import com.tld.takenotes.domain.events.CreateNewNote;
import com.tld.takenotes.domain.events.DeleteCurrentNote;
import com.tld.takenotes.domain.events.NoteClickEvent;
import com.tld.takenotes.domain.events.NoteSearch;
import com.tld.takenotes.domain.events.NoteTyped;
import com.tld.takenotes.domain.events.OptionClicked;
import com.tld.takenotes.domain.events.SaveCurrentNote;
import com.tld.takenotes.domain.events.TTSNote;
import com.tld.takenotes.domain.events.ToastEvent;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.subjects.PublishSubject;

@Module
class BusModule {
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
    static PublishSubject<SaveCurrentNote> provideSaveCurrentNote() {
        return PublishSubject.create();
    }

    @Provides
    @Singleton
    static PublishSubject<OptionClicked> provideOptionClicked() {
        return PublishSubject.create();
    }

    @Provides
    @Singleton
    static PublishSubject<TTSNote> provideTTSNote() {
        return PublishSubject.create();
    }

    @Provides
    @Singleton
    static PublishSubject<ToastEvent> provideToast() {
        return PublishSubject.create();
    }

    @Provides
    @Singleton
    static PublishSubject<NoteTyped> provideNoteTyped() {
        return PublishSubject.create();
    }
}