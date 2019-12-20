package com.tld.takenotes.inject.note;

import com.tld.takenotes.inject.Fragment;
import com.tld.takenotes.inject.app.AppComponent;
import com.tld.takenotes.view.note.NoteFragment;

import dagger.Component;

@Fragment
@Component(dependencies = AppComponent.class, modules = NoteModule.class)
public interface NoteComponent {
    void inject(NoteFragment noteFragment);
}
