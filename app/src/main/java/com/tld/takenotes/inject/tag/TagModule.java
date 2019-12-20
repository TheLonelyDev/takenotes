package com.tld.takenotes.inject.tag;

import com.tld.takenotes.inject.Fragment;
import com.tld.takenotes.viewmodel.tag.TagViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class TagModule {
    TagViewModel.TagListener listener;

    public TagModule(TagViewModel.TagListener listener) {
        this.listener = listener;
    }

    @Fragment
    @Provides
    TagViewModel provideTagViewModel() {
        return new TagViewModel(listener);
    }
}
