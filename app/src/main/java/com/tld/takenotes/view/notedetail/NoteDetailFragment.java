package com.tld.takenotes.view.notedetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.tld.takenotes.TakeNotes;
import com.tld.takenotes.R;
import com.tld.takenotes.databinding.FragmentNoteDetailBinding;
import com.tld.takenotes.domain.events.DeleteCurrentNote;
import com.tld.takenotes.dagger2.notedetail.DaggerNoteDetailComponent;
import com.tld.takenotes.dagger2.notedetail.NoteDetailModule;
import com.tld.takenotes.model.entity.Note;
import com.tld.takenotes.viewmodel.notedetail.NoteDetailViewModel;

import org.parceler.Parcels;

import javax.inject.Inject;

public class NoteDetailFragment extends Fragment implements NoteDetailViewModel.NoteDetailListener {
    FragmentNoteDetailBinding binding;

    @Inject
    NoteDetailViewModel viewModel;

    public static NoteDetailFragment newFragment(Note note) {
        NoteDetailFragment fragment = new NoteDetailFragment();
        Bundle arguments = new Bundle();

        arguments.putParcelable("key_note", Parcels.wrap(note));
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void DeleteNote(DeleteCurrentNote deleteCurrentNote) {
        if (getActivity() != null)
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_detail, container, false);

        // Inject
        DaggerNoteDetailComponent.builder().appComponent(((TakeNotes) (getActivity().getApplication())).getAppComponent()).noteDetailModule(new NoteDetailModule(this)).build().inject(this);

        binding.setViewModel(viewModel);
        viewModel.note.setValue((Note) Parcels.unwrap(getArguments().getParcelable("key_note")));
        return binding.getRoot();
    }
}
