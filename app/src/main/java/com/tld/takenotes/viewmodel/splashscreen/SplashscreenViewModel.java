package com.tld.takenotes.viewmodel.splashscreen;

import android.view.View;

import com.tld.takenotes.MainActivityApp;
import com.tld.takenotes.events.OptionClicked;
import com.tld.takenotes.model.Option;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class SplashscreenViewModel {
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

    public void LocalClicked(View view){
        MainActivityApp.getBusComponent().getOptionClicked().onNext(new OptionClicked(Option.LOCAL));
    }

    public void CloudClicked(View view){
        MainActivityApp.getBusComponent().getOptionClicked().onNext(new OptionClicked(Option.CLOUD));
    }

    public interface SplashscreenListener {
        void onOptionClicked(Option option);
    }
}
