package com.tld.takenotes.inject.bus;

import com.tld.takenotes.viewmodel.note.NoteClickEvent;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.subjects.PublishSubject;

@Component(modules = BusModule.class)
@Singleton
public interface BusComponent
{
    PublishSubject<NoteClickEvent> getOnNoteClicked();
}