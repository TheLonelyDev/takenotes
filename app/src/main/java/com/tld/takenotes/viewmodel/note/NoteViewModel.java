package com.tld.takenotes.viewmodel.note;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.tld.takenotes.MainActivityApp;
import com.tld.takenotes.events.CreateNewNote;
import com.tld.takenotes.events.NoteClickEvent;
import com.tld.takenotes.events.NoteSearch;
import com.tld.takenotes.model.entity.Note;
import com.tld.takenotes.util.TextChanged;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class NoteViewModel
{
    public interface NoteListener
    {
        void CreateNewNote();
        void OnLoaded(List<Note> notes);
        void Search(NoteSearch noteSearch);
    }

    private NoteListener listener;
    private CompositeDisposable disposable;

    public NoteViewModel(final NoteListener listener)
    {
        this.listener = listener;

        disposable = new CompositeDisposable();

        disposable.add(MainActivityApp.getBusComponent().getCreateNewNote().subscribe(new Consumer<Object>()
        {
            @Override
            public void accept(Object o) throws Exception
            {
                if (o instanceof CreateNewNote)
                {
                    listener.CreateNewNote();
                }
            }
        }));

        disposable.add(MainActivityApp.getBusComponent().getNoteSearch().subscribe(new Consumer<Object>()
        {
            @Override
            public void accept(Object o) throws Exception
            {
                if (o instanceof NoteSearch)
                {
                    listener.Search((NoteSearch) o);
                }
            }
        }));
    }

    public void onDestroy()
    {
        disposable.clear();
    }

    public void CreateNewNote(View view)
    {
        MainActivityApp.getBusComponent().getCreateNewNote().onNext(new CreateNewNote());
    }

    public TextChanged onTextChanged() {
        return new TextChanged() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                //if(s.toString().trim().length() > 1)
                MainActivityApp.getBusComponent().getNoteSearch().onNext(new NoteSearch(s.toString().trim()));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        };
    }
}
