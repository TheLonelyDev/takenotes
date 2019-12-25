package com.tld.takenotes.view.note;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.tld.takenotes.MainActivityApp;
import com.tld.takenotes.R;
import com.tld.takenotes.databinding.FragmentNoteBinding;
import com.tld.takenotes.events.DeleteCurrentNote;
import com.tld.takenotes.events.NoteClickEvent;
import com.tld.takenotes.events.NoteSearch;
import com.tld.takenotes.events.SaveCurrentNote;
import com.tld.takenotes.inject.note.DaggerNoteComponent;
import com.tld.takenotes.inject.note.NoteModule;
import com.tld.takenotes.model.Option;
import com.tld.takenotes.model.entity.Note;
import com.tld.takenotes.repository.NoteRepository;
import com.tld.takenotes.viewmodel.note.NoteViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

public class NoteFragment extends Fragment implements NoteViewModel.NoteListener {
    FragmentNoteBinding binding;

    @Inject
    NoteViewModel viewModel;

    @Inject
    NoteAdapter adapter;

    @Inject
    NoteRepository noteRepository;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static NoteFragment newFragment(Option option) {
        NoteFragment fragment = new NoteFragment();
        Bundle arguments = new Bundle();

        arguments.putString("key_option", option.name());

        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void CreateNewNote() {
        Note note = new Note();
        note.setId(UUID.randomUUID().toString());
        note.setName("New note");
        note.setDetail("");

        if (viewModel.getOption() == Option.CLOUD)
            db.collection("notes").add(note);
        else
            noteRepository.newNote(note);

        Search(new NoteSearch(""));
        MainActivityApp.getBusComponent().getOnNoteClicked().onNext(new NoteClickEvent(note));
    }

    @Override
    public void OnLoaded(List<Note> notes) {
        adapter.setNotes(notes);
    }

    @Override
    public void Search(NoteSearch noteSearch) {
        if (viewModel.getOption() == Option.CLOUD)
            db.collection("notes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful())
                        OnLoaded(task.getResult().toObjects(Note.class));
                }
            });
        else
            noteRepository.searchNotes(noteSearch.getKeyword()).observe(this, new Observer<List<Note>>() {
                @Override
                public void onChanged(@Nullable List<Note> notes) {
                    OnLoaded(notes);
                }
            });
    }

    @Override
    public void SaveNote(SaveCurrentNote saveCurrentNote) {
        if (viewModel.getOption() == Option.CLOUD)
            db.collection("notes").document(saveCurrentNote.getNote().documentId).set(saveCurrentNote.getNote());
        else
            noteRepository.updateNote(saveCurrentNote.getNote());
    }

    @Override
    public void DeleteNote(DeleteCurrentNote deleteCurrentNote) {
        if (viewModel.getOption() == Option.CLOUD)
            db.collection("notes").document(deleteCurrentNote.getNote().documentId).delete();
        else
            noteRepository.deleteNote(deleteCurrentNote.getNote());

        Search(new NoteSearch(""));
        getActivity().finishActivity(1);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note, container, false);

        // Inject
        DaggerNoteComponent.builder().appComponent(((MainActivityApp) (getActivity().getApplication())).getAppComponent()).noteModule(new NoteModule(this)).build().inject(this);

        binding.setViewModel(viewModel);
        binding.recyclerView.setAdapter(adapter);

        Option option = Option.valueOf(getArguments().getString("key_option"));
        viewModel.setOption(option);

        Search(new NoteSearch(""));

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
}
