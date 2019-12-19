package com.tld.takenotes.viewmodel.main;

import com.tld.takenotes.model.entity.Note;

import lombok.Getter;
import lombok.Setter;

public class MainViewModel
{
    public interface MainListener { void onNoteClicked(Note note); }

    @Getter @Setter
    private boolean isTwoPane = false;
    private MainListener listener;

    public MainViewModel(MainListener listener)
    {
        this.listener = listener;
    }
}
