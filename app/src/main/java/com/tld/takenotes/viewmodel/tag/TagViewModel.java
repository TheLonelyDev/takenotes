package com.tld.takenotes.viewmodel.tag;

public class TagViewModel
{
    public interface TagListener { }

    private TagListener listener;

    public TagViewModel(TagListener listener) { this.listener = listener; }
}
