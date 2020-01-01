package com.tld.takenotes.view.notedetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.tld.takenotes.R;
import com.tld.takenotes.TakeNotes;
import com.tld.takenotes.dagger2.notedetail.DaggerNoteDetailTextCountComponent;
import com.tld.takenotes.dagger2.notedetail.NoteDetailTextCountModule;
import com.tld.takenotes.databinding.FragmentNoteDetailTextCountBinding;
import com.tld.takenotes.domain.Constants;
import com.tld.takenotes.domain.events.NoteTyped;
import com.tld.takenotes.model.entity.Note;
import com.tld.takenotes.viewmodel.notedetail.NoteDetailTextCountViewModel;

import org.parceler.Parcels;

import javax.inject.Inject;

public class NoteDetailTextCount extends Fragment implements NoteDetailTextCountViewModel.NoteDetailTextListener {
    @Inject
    protected NoteDetailTextCountViewModel viewModel;
    private FragmentNoteDetailTextCountBinding binding;

    public static NoteDetailTextCount newFragment(Note note) {
        NoteDetailTextCount fragment = new NoteDetailTextCount();
        Bundle arguments = new Bundle();

        arguments.putParcelable(Constants.NOTEDETAIL_TEXTCOUNT_PARCEL, Parcels.wrap(note));
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void NoteTyped(NoteTyped noteTyped) {
        viewModel.getTextCount().set(getContext().getString(R.string.text_count, noteTyped.getDetail().length()));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_detail_text_count, container, false);
        DaggerNoteDetailTextCountComponent.builder().appComponent(((TakeNotes) (getActivity().getApplication())).getAppComponent()).noteDetailTextCountModule(new NoteDetailTextCountModule(this)).build().inject(this);

        binding.setViewModel(viewModel);
        viewModel.getTextCount().set(getContext().getString(R.string.text_count, ((Note) Parcels.unwrap(getArguments().getParcelable(Constants.NOTEDETAIL_TEXTCOUNT_PARCEL))).getDetail().length()));

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        viewModel.onDestroy();
    }
}
