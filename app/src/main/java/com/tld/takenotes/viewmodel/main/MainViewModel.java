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
    private RxBus bus;

    public MainViewModel(MainListener listener, RxBus bus)
    {
        this.listener = listener;
        this.bus = bus;
    }

    
}
