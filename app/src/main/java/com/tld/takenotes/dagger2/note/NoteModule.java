package com.tld.takenotes.dagger2.note;

import android.content.Context;
import android.content.res.Resources;

import com.tld.takenotes.dagger2.Fragment;
import com.tld.takenotes.domain.repository.NoteRepository;
import com.tld.takenotes.view.note.NoteAdapter;
import com.tld.takenotes.viewmodel.note.NoteViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class NoteModule {
    private final NoteViewModel.NoteListener listener;

    public NoteModule(NoteViewModel.NoteListener listener) {
        this.listener = listener;
    }

    @Fragment
    @Provides
    NoteViewModel provideNoteViewModel(Context context, NoteRepository noteRepository, Resources resources) {
        return new NoteViewModel(context, this.listener, noteRepository, resources);
    }

    @Fragment
    @Provides
    NoteAdapter provideNoteAdapter() {
        return new NoteAdapter();
    }
}
