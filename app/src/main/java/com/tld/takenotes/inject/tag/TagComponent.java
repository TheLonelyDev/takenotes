package com.tld.takenotes.inject.tag;

import com.tld.takenotes.inject.Fragment;
import com.tld.takenotes.inject.app.AppComponent;
import com.tld.takenotes.view.tag.TagFragment;

import dagger.Component;

@Fragment
@Component(dependencies = AppComponent.class, modules = TagModule.class)
public interface TagComponent {
    void inject(TagFragment tagFragment);
}
