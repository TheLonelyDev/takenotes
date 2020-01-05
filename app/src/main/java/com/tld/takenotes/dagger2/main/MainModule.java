package com.tld.takenotes.dagger2.main;

import com.tld.takenotes.dagger2.Activity;
import com.tld.takenotes.viewmodel.main.MainViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    private MainViewModel.MainListener listener;

    public MainModule(MainViewModel.MainListener listener) {
        this.listener = listener;
    }

    @Provides
    @Activity
    MainViewModel provideMainViewModel() {
        return new MainViewModel(listener);
    }
}
