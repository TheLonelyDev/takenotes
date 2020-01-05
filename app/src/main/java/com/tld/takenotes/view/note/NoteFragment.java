package com.tld.takenotes.view.note;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.tld.takenotes.R;
import com.tld.takenotes.TakeNotes;
import com.tld.takenotes.dagger2.note.DaggerNoteComponent;
import com.tld.takenotes.dagger2.note.NoteModule;
import com.tld.takenotes.databinding.FragmentNoteBinding;
import com.tld.takenotes.domain.Constants;
import com.tld.takenotes.model.Option;
import com.tld.takenotes.model.entity.Note;
import com.tld.takenotes.viewmodel.note.NoteViewModel;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

public class NoteFragment extends Fragment implements NoteViewModel.NoteListener {
    @SuppressWarnings("WeakerAccess")
    @Inject
    protected NoteViewModel viewModel;
    @SuppressWarnings("WeakerAccess")
    @Inject
    protected NoteAdapter adapter;
    private Toast toast;

    public static NoteFragment newFragment(Option option) {
        NoteFragment fragment = new NoteFragment();
        Bundle arguments = new Bundle();

        arguments.putString(Constants.NOTE_PARCEL, option.name());
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void OnLoaded(List<Note> notes) {
        adapter.setNotes(notes);
    }


    @Override
    public void ShowToast(int resourceId) {
        toast.setText(getResources().getString(resourceId));
        toast.show();
    }

    @SuppressLint("ShowToast")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentNoteBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note, container, false);

        DaggerNoteComponent.builder().appComponent(((TakeNotes) (Objects.requireNonNull(getActivity()).getApplication())).getAppComponent()).noteModule(new NoteModule(this)).build().inject(this);

        binding.setViewModel(viewModel);
        binding.recyclerView.setAdapter(adapter);

        Option option = Option.valueOf(Objects.requireNonNull(getArguments()).getString(Constants.NOTE_PARCEL));
        viewModel.setOption(option);

        toast = Toast.makeText(getActivity().getApplicationContext(), "", Toast.LENGTH_SHORT);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel.notes.observe(this, this::OnLoaded);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        viewModel.onDestroy();
    }
}
