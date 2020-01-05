package com.tld.takenotes.viewmodel.notedetail;

import androidx.databinding.ObservableField;

import com.tld.takenotes.TakeNotes;
import com.tld.takenotes.domain.events.NoteTyped;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import lombok.Getter;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;

public class NoteDetailTextCountViewModel {
    private final CompositeDisposable disposable;
    @Getter
    private final ObservableField<String> textCount;

    public NoteDetailTextCountViewModel(final NoteDetailTextCountViewModel.NoteDetailTextListener listener) {
        this.textCount = new ObservableField<>();

        this.disposable = new CompositeDisposable();

        this.disposable.add(TakeNotes.getBusComponent().getNoteTyped().observeOn(mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                if (o instanceof NoteTyped) {
                    listener.NoteTyped((NoteTyped) o);
                }
            }
        }));
    }

    public void onDestroy() {
        this.disposable.clear();
    }

    public interface NoteDetailTextListener {
        void NoteTyped(NoteTyped noteTyped);
    }
}
