package com.tld.takenotes.viewmodel.main;

import com.tld.takenotes.TakeNotes;
import com.tld.takenotes.domain.events.NoteClickEvent;
import com.tld.takenotes.model.Option;
import com.tld.takenotes.model.entity.Note;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import lombok.Getter;
import lombok.Setter;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;


public class MainViewModel {
    private CompositeDisposable disposable;

    @Getter
    @Setter
    private boolean isTwoPane = false;

    @Getter
    @Setter
    private Option option;

    public MainViewModel(final MainListener listener) {

        this.disposable = new CompositeDisposable();

        this.disposable.add(TakeNotes.getBusComponent().getOnNoteClicked().observeOn(mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (o instanceof NoteClickEvent) {
                    listener.onNoteClicked(((NoteClickEvent) o).getNote());
                }
            }
        }));
    }

    public void onDestroy() {
        this.disposable.clear();
    }

    public interface MainListener {
        void onNoteClicked(Note note);
    }
}
