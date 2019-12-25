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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.tld.takenotes.TakeNotes;
import com.tld.takenotes.R;
import com.tld.takenotes.databinding.FragmentNoteBinding;
import com.tld.takenotes.domain.events.DeleteCurrentNote;
import com.tld.takenotes.domain.events.NoteClickEvent;
import com.tld.takenotes.domain.events.NoteSearch;
import com.tld.takenotes.domain.events.SaveCurrentNote;
import com.tld.takenotes.dagger2.note.DaggerNoteComponent;
import com.tld.takenotes.dagger2.note.NoteModule;
import com.tld.takenotes.model.Option;
import com.tld.takenotes.model.entity.Note;
import com.tld.takenotes.domain.repository.NoteRepository;
import com.tld.takenotes.viewmodel.note.NoteViewModel;

import java.util.ArrayList;
import java.util.List;
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

    String lastSearch = "";

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
        TakeNotes.getBusComponent().getOnNoteClicked().onNext(new NoteClickEvent(note));
    }

    @Override
    public void OnLoaded(List<Note> notes) {
        adapter.setNotes(notes);
    }

    protected void Search() {
        Search(new NoteSearch(lastSearch));
    }

    @Override
    public void Search(NoteSearch noteSearch) {
        lastSearch = noteSearch.getKeyword();

        if (viewModel.getOption() == Option.CLOUD)
            db.collection("notes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        List<Note> notes = task.getResult().toObjects(Note.class);

                        if (!lastSearch.isEmpty()) {
                            List<Note> toRemove = new ArrayList<Note>();

                            for (Note note : notes) {
                                if (!(note.getName().contains(lastSearch) || note.getDetail().contains(lastSearch)))
                                    toRemove.add(note);
                            }

                            notes.removeAll(toRemove);
                        }

                        OnLoaded(notes);
                    }
                }
            });
        else
            noteRepository.searchNotes(String.format("%%%s%%", lastSearch)).observe(this, new Observer<List<Note>>() {
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

        Search();
    }

    @Override
    public void DeleteNote(DeleteCurrentNote deleteCurrentNote) {
        if (viewModel.getOption() == Option.CLOUD)
            db.collection("notes").document(deleteCurrentNote.getNote().documentId).delete();
        else
            noteRepository.deleteNote(deleteCurrentNote.getNote());

        Search();
        getActivity().finishActivity(1);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note, container, false);

        // Inject
        DaggerNoteComponent.builder().appComponent(((TakeNotes) (getActivity().getApplication())).getAppComponent()).noteModule(new NoteModule(this)).build().inject(this);

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
