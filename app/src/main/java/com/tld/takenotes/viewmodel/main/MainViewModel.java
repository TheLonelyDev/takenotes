package com.tld.takenotes.viewmodel.main;

import com.tld.takenotes.MainActivityApp;
import com.tld.takenotes.events.NoteClickEvent;
import com.tld.takenotes.model.entity.Note;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import lombok.Getter;
import lombok.Setter;


public class MainViewModel {
    @Getter
    @Setter
    private boolean isTwoPane = false;
    private MainListener listener;
    private CompositeDisposable disposable;
    public MainViewModel(final MainListener listener) {
        this.listener = listener;

        disposable = new CompositeDisposable();

        disposable.add(MainActivityApp.getBusComponent().getOnNoteClicked().subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (o instanceof NoteClickEvent) {
                    listener.onNoteClicked(((NoteClickEvent) o).getNote());
                }
            }
        }));
    }

    public void onDestroy() {
        disposable.clear();
    }

    public interface MainListener {
        void onNoteClicked(Note note);
    }
}
