package com.tld.takenotes.view.note;

import androidx.fragment.app.Fragment;

import com.tld.takenotes.viewmodel.note.NoteViewModel;

import javax.inject.Inject;

public class NoteFragment extends Fragment implements NoteViewModel.NoteListener
{


    @Inject
    NoteViewModel viewModel;
}
