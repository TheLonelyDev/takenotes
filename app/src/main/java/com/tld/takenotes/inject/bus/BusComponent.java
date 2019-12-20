package com.tld.takenotes.inject.bus;

import com.tld.takenotes.events.CreateNewNote;
import com.tld.takenotes.events.NoteClickEvent;
import com.tld.takenotes.events.NoteSearch;

import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.subjects.PublishSubject;

@Component(modules = BusModule.class)
@Singleton
public interface BusComponent
{
    PublishSubject<NoteClickEvent> getOnNoteClicked();

    PublishSubject<CreateNewNote> getCreateNewNote();

    PublishSubject<NoteSearch> getNoteSearch();
}