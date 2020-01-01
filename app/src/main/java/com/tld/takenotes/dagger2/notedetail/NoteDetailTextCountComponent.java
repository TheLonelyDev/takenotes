package com.tld.takenotes.dagger2.notedetail;

import com.tld.takenotes.dagger2.Fragment;
import com.tld.takenotes.dagger2.app.AppComponent;
import com.tld.takenotes.view.notedetail.NoteDetailTextCount;

import dagger.Component;

@Fragment
@Component(dependencies = AppComponent.class, modules = NoteDetailTextCountModule.class)
public interface NoteDetailTextCountComponent {
    void inject(NoteDetailTextCount noteDetailTextCount);
}