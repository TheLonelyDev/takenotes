package com.tld.takenotes.dagger2.note;

import com.tld.takenotes.dagger2.Fragment;
import com.tld.takenotes.dagger2.app.AppComponent;
import com.tld.takenotes.view.note.NoteFragment;

import dagger.Component;

@Fragment
@Component(dependencies = AppComponent.class, modules = NoteModule.class)
public interface NoteComponent {
    void inject(NoteFragment noteFragment);
}
