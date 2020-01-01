package com.tld.takenotes.dagger2.notedetail;

import com.tld.takenotes.dagger2.Fragment;
import com.tld.takenotes.viewmodel.notedetail.NoteDetailTextCountViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class NoteDetailTextCountModule {
    NoteDetailTextCountViewModel.NoteDetailTextListener listener;

    public NoteDetailTextCountModule(NoteDetailTextCountViewModel.NoteDetailTextListener listener) {
        this.listener = listener;
    }

    @Fragment
    @Provides
    NoteDetailTextCountViewModel provideNoteDetailTextCountViewModel() {
        return new NoteDetailTextCountViewModel(listener);
    }
}