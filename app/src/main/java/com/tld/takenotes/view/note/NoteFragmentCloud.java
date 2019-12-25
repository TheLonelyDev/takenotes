package com.tld.takenotes.view.note;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.tld.takenotes.MainActivityApp;
import com.tld.takenotes.R;
import com.tld.takenotes.databinding.FragmentNoteBinding;
import com.tld.takenotes.events.DeleteCurrentNote;
import com.tld.takenotes.events.NoteClickEvent;
import com.tld.takenotes.events.NoteSearch;
import com.tld.takenotes.events.SaveCurrentNote;
import com.tld.takenotes.inject.note.DaggerNoteComponent;
import com.tld.takenotes.inject.note.NoteModule;
import com.tld.takenotes.model.entity.Note;
import com.tld.takenotes.repository.NoteRepository;
import com.tld.takenotes.viewmodel.note.NoteViewModel;

import java.util.List;

import javax.inject.Inject;

public class NoteFragmentCloud extends NoteFragment implements NoteViewModel.NoteListener {
    public static NoteFragmentCloud newFragment() {
        NoteFragmentCloud fragment = new NoteFragmentCloud();
        Bundle arguments = new Bundle();

        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void CreateNewNote() {
        Note note = new Note();
        note.setName("New note");
        note.setDetail("");

        MainActivityApp.getBusComponent().getOnNoteClicked().onNext(new NoteClickEvent(note));
    }

    @Override
    public void OnLoaded(List<Note> notes) {
        adapter.setNotes(notes);
    }

    @Override
    public void Search(NoteSearch noteSearch) {

    }

    @Override
    public void SaveNote(SaveCurrentNote saveCurrentNote) {
    }

    @Override
    public void DeleteNote(DeleteCurrentNote deleteCurrentNote) {

        Search(new NoteSearch(""));
        getActivity().finishActivity(1);
    }
}
