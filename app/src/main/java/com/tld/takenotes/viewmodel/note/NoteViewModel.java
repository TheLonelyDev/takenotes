package com.tld.takenotes.viewmodel.note;

import android.view.View;

import com.tld.takenotes.MainActivityApp;
import com.tld.takenotes.events.CreateNewNote;
import com.tld.takenotes.events.NoteClickEvent;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class NoteViewModel
{
    public interface NoteListener { void CreateNewNote(); }

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
    }

    public void onDestroy()
    {
        disposable.clear();
    }

    public void CreateNewNote(View view)
    {
        MainActivityApp.getBusComponent().getCreateNewNote().onNext(new CreateNewNote());
    }
}
