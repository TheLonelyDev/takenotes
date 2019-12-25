package com.tld.takenotes.dagger2.bus;

import com.tld.takenotes.domain.events.CreateNewNote;
import com.tld.takenotes.domain.events.DeleteCurrentNote;
import com.tld.takenotes.domain.events.NoteClickEvent;
import com.tld.takenotes.domain.events.NoteSearch;
import com.tld.takenotes.domain.events.OptionClicked;
import com.tld.takenotes.domain.events.SaveCurrentNote;

import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.subjects.PublishSubject;

@Component(modules = BusModule.class)
@Singleton
public interface BusComponent {
    PublishSubject<NoteClickEvent> getOnNoteClicked();

    PublishSubject<CreateNewNote> getCreateNewNote();

    PublishSubject<NoteSearch> getNoteSearch();

    PublishSubject<DeleteCurrentNote> getDeleteCurrentNote();

    PublishSubject<SaveCurrentNote> getSaveCurrentNote();

    PublishSubject<OptionClicked> getOptionClicked();
}