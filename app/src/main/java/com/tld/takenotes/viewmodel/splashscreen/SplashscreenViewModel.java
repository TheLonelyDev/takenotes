package com.tld.takenotes.viewmodel.splashscreen;

import android.view.View;

import com.tld.takenotes.TakeNotes;
import com.tld.takenotes.domain.events.OptionClicked;
import com.tld.takenotes.model.Option;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;

public class SplashscreenViewModel {
    private final CompositeDisposable disposable;

    public SplashscreenViewModel(final SplashscreenViewModel.SplashscreenListener listener) {

        this.disposable = new CompositeDisposable();

        this.disposable.add(TakeNotes.getBusComponent().getOptionClicked().observeOn(mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                if (o instanceof OptionClicked) {
                    listener.onOptionClicked(((OptionClicked) o).getOption());
                }
            }
        }));
    }

    public void onDestroy() {
        this.disposable.clear();
    }

    public void LocalClicked(View view) {
        TakeNotes.getBusComponent().getOptionClicked().onNext(new OptionClicked(Option.LOCAL));
    }

    public void CloudClicked(View view) {
        TakeNotes.getBusComponent().getOptionClicked().onNext(new OptionClicked(Option.CLOUD));
    }

    public interface SplashscreenListener {
        void onOptionClicked(Option option);
    }
}
