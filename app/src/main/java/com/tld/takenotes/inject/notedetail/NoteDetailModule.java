package com.tld.takenotes.inject.notedetail;

import com.tld.takenotes.inject.Fragment;
import com.tld.takenotes.viewmodel.notedetail.NoteDetailViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class NoteDetailModule
{
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