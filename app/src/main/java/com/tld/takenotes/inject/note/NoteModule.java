package com.tld.takenotes.inject.note;

import com.tld.takenotes.inject.Fragment;
import com.tld.takenotes.view.note.NoteAdapter;
import com.tld.takenotes.viewmodel.note.NoteViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class NoteModule
{
    NoteViewModel.NoteListener listener;

    public NoteModule(NoteViewModel.NoteListener listener)
    {
        this.listener = listener;
    }

    @Fragment
    @Provides
    NoteViewModel provideNoteViewModel()
    {
        return new NoteViewModel(listener);
    }

    @Fragment
    @Provides
    NoteAdapter provideNoteAdapter() { return new NoteAdapter(); }
}
