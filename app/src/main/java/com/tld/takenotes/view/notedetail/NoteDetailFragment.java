package com.tld.takenotes.view.notedetail;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.squareup.picasso.Picasso;
import com.tld.takenotes.R;
import com.tld.takenotes.TakeNotes;
import com.tld.takenotes.dagger2.notedetail.DaggerNoteDetailComponent;
import com.tld.takenotes.dagger2.notedetail.NoteDetailModule;
import com.tld.takenotes.databinding.FragmentNoteDetailBinding;
import com.tld.takenotes.domain.Constants;
import com.tld.takenotes.domain.events.DeleteCurrentNote;
import com.tld.takenotes.domain.events.TTSNote;
import com.tld.takenotes.model.entity.Note;
import com.tld.takenotes.viewmodel.notedetail.NoteDetailViewModel;

import org.parceler.Parcels;

import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

public class NoteDetailFragment extends Fragment implements NoteDetailViewModel.NoteDetailListener, TextToSpeech.OnInitListener {
    @SuppressWarnings("WeakerAccess")
    @Inject
    protected NoteDetailViewModel viewModel;
    private FragmentNoteDetailBinding binding;
    private TextToSpeech tts;

    public static NoteDetailFragment newFragment(Note note) {
        NoteDetailFragment fragment = new NoteDetailFragment();
        Bundle arguments = new Bundle();

        arguments.putParcelable(Constants.NOTEDETAIL_PARCEL, Parcels.wrap(note));
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void DeleteNote(DeleteCurrentNote deleteCurrentNote) {
        if (getActivity() == null)
            return;

        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();

        if (getActivity().getClass().getName().equals(NoteDetailActivity.class.getName()))
            getActivity().onBackPressed();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(getContext(), getResources().getString(R.string.lang_not_supported), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void TTS(TTSNote ttsNote) {
        tts.setLanguage(Locale.US);
        tts.speak(ttsNote.getNote().getDetail(), TextToSpeech.QUEUE_FLUSH, null);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_detail, container, false);

        DaggerNoteDetailComponent.builder().appComponent(((TakeNotes) (Objects.requireNonNull(getActivity()).getApplication())).getAppComponent()).noteDetailModule(new NoteDetailModule(this)).build().inject(this);

        binding.setViewModel(viewModel);
        Note note = Parcels.unwrap(Objects.requireNonNull(getArguments()).getParcelable(Constants.NOTEDETAIL_PARCEL));
        viewModel.note.setValue(note);

        getChildFragmentManager().beginTransaction().replace(R.id.text_count_frame, NoteDetailTextCount.newFragment(note)).commitAllowingStateLoss();

        Picasso.get().load(String.format("%s%s", Constants.BING_API, ((TakeNotes) (getActivity().getApplication())).getBingImage().getValue())).placeholder(R.drawable.bg).fit().into(binding.noteBanner);
        ((TakeNotes) (getActivity().getApplication())).getBingImage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Picasso.get().load(String.format("%s%s", Constants.BING_API, s)).placeholder(R.drawable.bg).fit().into(binding.noteBanner);
            }
        });

        tts = new TextToSpeech(getContext(), this);

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        tts.stop();
        tts.shutdown();

        viewModel.onDestroy();
    }
}
