package com.tld.takenotes.inject.bus;

import com.tld.takenotes.viewmodel.note.NoteClickEvent;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.subjects.PublishSubject;

@Module
public class BusModule
{
    @Provides
    @Singleton
    static PublishSubject<NoteClickEvent> provideOnNoteClicked()
    {
        return PublishSubject.create();
    }
}