package com.tld.takenotes.viewmodel.tag;

public class TagViewModel {
    private TagListener listener;

    public TagViewModel(TagListener listener) {
        this.listener = listener;
    }

    public interface TagListener {
    }
}
