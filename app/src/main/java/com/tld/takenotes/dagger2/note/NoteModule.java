package com.tld.takenotes.dagger2.note;

import com.tld.takenotes.dagger2.Fragment;
import com.tld.takenotes.domain.repository.NoteRepository;
import com.tld.takenotes.view.note.NoteAdapter;
import com.tld.takenotes.viewmodel.note.NoteViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class NoteModule {
    NoteViewModel.NoteListener listener;
    NoteRepository noteRepository;

    public NoteModule(NoteViewModel.NoteListener listener, NoteRepository noteRepository) {
        this.listener = listener;
        this.noteRepository = noteRepository;
    }

    @Fragment
    @Provides
    NoteViewModel provideNoteViewModel() {
        return new NoteViewModel(listener, noteRepository);
    }

    @Fragment
    @Provides
    NoteAdapter provideNoteAdapter() {
        return new NoteAdapter();
    }
}
