package com.tld.takenotes.viewmodel.splashscreen;

import com.tld.takenotes.MainActivityApp;
import com.tld.takenotes.events.NoteClickEvent;
import com.tld.takenotes.events.OptionClicked;
import com.tld.takenotes.model.entity.Note;
import com.tld.takenotes.model.entity.Option;
import com.tld.takenotes.viewmodel.main.MainViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class SplashscreenViewModel
{
    private SplashscreenViewModel.SplashscreenListener listener;
    private CompositeDisposable disposable;

    public SplashscreenViewModel(final SplashscreenViewModel.SplashscreenListener listener) {
        this.listener = listener;

        disposable = new CompositeDisposable();

        disposable.add(MainActivityApp.getBusComponent().getOptionClicked().subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (o instanceof OptionClicked) {
                    listener.onOptionClicked(((OptionClicked) o).getOption());
                }
            }
        }));
    }

    public void onDestroy() {
        disposable.clear();
    }

    public interface SplashscreenListener {
        void onOptionClicked(Option option);
    }
}
