package com.tld.takenotes.dagger2.notedetail;

import com.tld.takenotes.dagger2.Fragment;
import com.tld.takenotes.viewmodel.notedetail.NoteDetailViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class NoteDetailModule {
    NoteDetailViewModel.NoteDetailListener listener;

    public NoteDetailModule(NoteDetailViewModel.NoteDetailListener listener) {
        this.listener = listener;
    }

    @Fragment
    @Provides
    NoteDetailViewModel provideNoteDetailViewModel() {
        return new NoteDetailViewModel(listener);
    }
}