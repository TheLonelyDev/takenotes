package com.tld.takenotes.view.note;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.tld.takenotes.MainActivityApp;
import com.tld.takenotes.R;
import com.tld.takenotes.databinding.FragmentNoteBinding;
import com.tld.takenotes.inject.note.DaggerNoteComponent;
import com.tld.takenotes.inject.note.NoteModule;
import com.tld.takenotes.viewmodel.note.NoteViewModel;

import javax.inject.Inject;

public class NoteFragment extends Fragment implements NoteViewModel.NoteListener
{
    FragmentNoteBinding binding;

    @Inject
    NoteViewModel viewModel;

    @Inject
    NoteAdapter adapter;

    public static NoteFragment newFragment()
    {
        NoteFragment fragment = new NoteFragment();
        Bundle arguments = new Bundle();

        fragment.setArguments(arguments);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note, container, false);

        // Inject
        DaggerNoteComponent.builder().appComponent(((MainActivityApp) (getActivity().getApplication())).getAppComponent()).noteModule(new NoteModule(this)).build().inject(this);

        binding.setViewModel(viewModel);
        binding.recyclerView.setAdapter(adapter);

        //if (savedInstanceState != null)

        return binding.getRoot();
    }
}
