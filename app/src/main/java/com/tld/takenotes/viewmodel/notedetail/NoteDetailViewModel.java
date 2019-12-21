package com.tld.takenotes.viewmodel.notedetail;

import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;

import androidx.lifecycle.MutableLiveData;

import com.tld.takenotes.MainActivityApp;
import com.tld.takenotes.events.CreateNewNote;
import com.tld.takenotes.events.DeleteCurrentNote;
import com.tld.takenotes.events.SaveCurrentNote;
import com.tld.takenotes.model.entity.Note;

import java.util.Locale;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import lombok.Getter;
import lombok.Setter;

public class NoteDetailViewModel {

    public MutableLiveData<Note> note = new MutableLiveData<>();
    private NoteDetailListener listener;
    private CompositeDisposable disposable;

    public NoteDetailViewModel(final NoteDetailListener listener) {
        this.listener = listener;

        disposable = new CompositeDisposable();

        disposable.add(MainActivityApp.getBusComponent().getDeleteCurrentNote().subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (o instanceof DeleteCurrentNote) {
                    listener.DeleteNote((DeleteCurrentNote) o);
                }
            }
        }));
    }

    public void DeleteNote(View view)
    {

        MainActivityApp.getBusComponent().getDeleteCurrentNote().onNext(new DeleteCurrentNote(note.getValue()));
    }

    public void SaveNote(View view)
    {
        MainActivityApp.getBusComponent().getSaveCurrentNote().onNext(new SaveCurrentNote(note.getValue()));
    }

    public void TTS(View view)
    {
        MainActivityApp.getBusComponent().getSaveCurrentNote().onNext(new SaveCurrentNote(note.getValue()));
    }

    public interface NoteDetailListener
    {
        void DeleteNote(DeleteCurrentNote deleteCurrentNote);
    }
}
