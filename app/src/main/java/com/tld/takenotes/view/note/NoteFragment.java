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
import com.tld.takenotes.events.NoteClickEvent;
import com.tld.takenotes.events.NoteSearch;
import com.tld.takenotes.inject.note.DaggerNoteComponent;
import com.tld.takenotes.inject.note.NoteModule;
import com.tld.takenotes.model.entity.Note;
import com.tld.takenotes.repository.NoteRepository;
import com.tld.takenotes.viewmodel.note.NoteViewModel;

import java.util.List;

import javax.inject.Inject;

public class NoteFragment extends Fragment implements NoteViewModel.NoteListener {
    FragmentNoteBinding binding;

    @Inject
    NoteViewModel viewModel;

    @Inject
    NoteAdapter adapter;

    @Inject
    NoteRepository noteRepository;

    public static NoteFragment newFragment() {
        NoteFragment fragment = new NoteFragment();
        Bundle arguments = new Bundle();

        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void CreateNewNote() {
        Note note = new Note();
        note.setName("New note");
        note.setDetail("");

        noteRepository.newNote(note);

        MainActivityApp.getBusComponent().getOnNoteClicked().onNext(new NoteClickEvent(note));
    }

    @Override
    public void OnLoaded(List<Note> notes) {
        adapter.setNotes(notes);
    }

    @Override
    public void Search(NoteSearch noteSearch) {
        noteRepository.getAll().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                OnLoaded(notes);
            }
        });
        // OnLoaded((noteSearch.getKeyword().trim().isEmpty() ? noteRepository.getAll() : noteRepository.getAll()).getValue());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note, container, false);

        // Inject
        DaggerNoteComponent.builder().appComponent(((MainActivityApp) (getActivity().getApplication())).getAppComponent()).noteModule(new NoteModule(this)).build().inject(this);

        binding.setViewModel(viewModel);
        binding.recyclerView.setAdapter(adapter);

        Search(new NoteSearch(""));

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
}
