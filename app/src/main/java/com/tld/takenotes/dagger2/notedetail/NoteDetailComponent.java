package com.tld.takenotes.dagger2.notedetail;

import com.tld.takenotes.dagger2.Fragment;
import com.tld.takenotes.dagger2.app.AppComponent;
import com.tld.takenotes.view.notedetail.NoteDetailFragment;

import dagger.Component;

@Fragment
@Component(dependencies = AppComponent.class, modules = NoteDetailModule.class)
public interface NoteDetailComponent {
    void inject(NoteDetailFragment noteDetailFragment);
}
