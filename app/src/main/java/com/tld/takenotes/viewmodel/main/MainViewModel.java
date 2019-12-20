package com.tld.takenotes.viewmodel.main;

import android.os.SystemClock;
import android.util.Log;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.tld.takenotes.MainActivityApp;
import com.tld.takenotes.model.entity.Note;
import com.tld.takenotes.viewmodel.note.NoteClickEvent;
import com.tld.takenotes.viewmodel.tag.TagClickEvent;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.security.cert.Extension;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import lombok.Getter;
import lombok.Setter;


public class MainViewModel
{
    public interface MainListener { void onNoteClicked(Note note); }

    @Getter @Setter
    private boolean isTwoPane = false;
    private MainListener listener;
    private RxBus bus;
    private CompositeDisposable disposable;

    public MainViewModel(final MainListener listener)
    {
        this.listener = listener;
        this.bus = bus;

        disposable = new CompositeDisposable();

        MainActivityApp.getBusComponent().getOnNoteClicked().subscribe(new Consumer<Object>()
        {
            @Override
            public void accept(Object o) throws Exception
            {
                if (o instanceof NoteClickEvent)
                {
                    NoteClickEvent event = (NoteClickEvent) o;
                    Log.d("STATE", event.getNote().getName());
                }
            }
        });
    }

    protected void onDestroy()
    {
        disposable.clear();
    }

}
