package com.tld.takenotes.viewmodel.notedetail;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.tld.takenotes.TakeNotes;
import com.tld.takenotes.domain.events.DeleteCurrentNote;
import com.tld.takenotes.domain.events.SaveCurrentNote;
import com.tld.takenotes.model.entity.Note;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class NoteDetailViewModel {

    public MutableLiveData<Note> note = new MutableLiveData<>();
    private NoteDetailListener listener;
    private CompositeDisposable disposable;

    public NoteDetailViewModel(final NoteDetailListener listener) {
        this.listener = listener;

        disposable = new CompositeDisposable();

        disposable.add(TakeNotes.getBusComponent().getDeleteCurrentNote().subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (o instanceof DeleteCurrentNote) {
                    listener.DeleteNote((DeleteCurrentNote) o);
                }
            }
        }));
    }

    public void DeleteNote(View view) {

        TakeNotes.getBusComponent().getDeleteCurrentNote().onNext(new DeleteCurrentNote(note.getValue()));
    }

    public void SaveNote(View view) {
        TakeNotes.getBusComponent().getSaveCurrentNote().onNext(new SaveCurrentNote(note.getValue()));
    }

    public void TTS(View view) {
        TakeNotes.getBusComponent().getSaveCurrentNote().onNext(new SaveCurrentNote(note.getValue()));
    }

    public interface NoteDetailListener {
        void DeleteNote(DeleteCurrentNote deleteCurrentNote);
    }
}
