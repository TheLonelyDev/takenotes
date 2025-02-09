package com.tld.takenotes.viewmodel.notedetail;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.tld.takenotes.TakeNotes;
import com.tld.takenotes.domain.events.DeleteCurrentNote;
import com.tld.takenotes.domain.events.NoteTyped;
import com.tld.takenotes.domain.events.SaveCurrentNote;
import com.tld.takenotes.domain.events.TTSNote;
import com.tld.takenotes.domain.util.TextChanged;
import com.tld.takenotes.model.entity.Note;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;

public class NoteDetailViewModel {
    public final MutableLiveData<Note> note = new MutableLiveData<>();
    private final CompositeDisposable disposable;

    public NoteDetailViewModel(final NoteDetailListener listener) {

        this.disposable = new CompositeDisposable();

        this.disposable.add(TakeNotes.getBusComponent().getDeleteCurrentNote().observeOn(mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                if (o instanceof DeleteCurrentNote) {
                    listener.DeleteNote((DeleteCurrentNote) o);
                }
            }
        }));

        this.disposable.add(TakeNotes.getBusComponent().getTTSNote().observeOn(mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                if (o instanceof TTSNote) {
                    listener.TTS((TTSNote) o);
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
        TakeNotes.getBusComponent().getTTSNote().onNext(new TTSNote(note.getValue()));
    }

    public TextChanged onTextChanged() {
        return new TextChanged() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TakeNotes.getBusComponent().getNoteTyped().onNext(new NoteTyped(s.toString().trim()));
            }
        };
    }

    public void onDestroy() {
        this.disposable.clear();
    }

    public interface NoteDetailListener {
        void DeleteNote(DeleteCurrentNote deleteCurrentNote);

        void TTS(TTSNote ttsNote);
    }
}
