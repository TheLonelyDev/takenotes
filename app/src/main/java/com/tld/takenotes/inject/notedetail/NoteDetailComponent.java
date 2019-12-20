package com.tld.takenotes.inject.notedetail;

import com.tld.takenotes.inject.Fragment;
import com.tld.takenotes.inject.app.AppComponent;
import com.tld.takenotes.view.notedetail.NoteDetailFragment;

import dagger.Component;

@Fragment
@Component(dependencies = AppComponent.class, modules = NoteDetailModule.class)
public interface NoteDetailComponent {
    void inject(NoteDetailFragment noteDetailFragment);
}
