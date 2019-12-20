package com.tld.takenotes.view.tag;

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
import com.tld.takenotes.databinding.FragmentTagBinding;
import com.tld.takenotes.inject.tag.DaggerTagComponent;
import com.tld.takenotes.inject.tag.TagModule;
import com.tld.takenotes.viewmodel.tag.TagViewModel;

import javax.inject.Inject;

public class TagFragment extends Fragment implements TagViewModel.TagListener
{
    FragmentTagBinding binding;

    @Inject
    TagViewModel viewModel;

    public static TagFragment newFragment() {
        TagFragment fragment = new TagFragment();
        Bundle arguments = new Bundle();

        fragment.setArguments(arguments);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note, container, false);
        // Inject
        DaggerTagComponent.builder().appComponent(((MainActivityApp) (getActivity().getApplication())).getAppComponent()).tagModule(new TagModule(this)).build().inject(this);

        binding.setViewModel(viewModel);

        return binding.getRoot();
    }
}
