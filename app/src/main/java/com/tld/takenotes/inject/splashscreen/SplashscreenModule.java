package com.tld.takenotes.inject.splashscreen;

import com.tld.takenotes.inject.Activity;
import com.tld.takenotes.viewmodel.splashscreen.SplashscreenViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashscreenModule {
    SplashscreenViewModel.SplashscreenListener listener;

    public SplashscreenModule(SplashscreenViewModel.SplashscreenListener listener) {
        this.listener = listener;
    }

    @Provides
    @Activity
    SplashscreenViewModel provideMainViewModel() {
        return new SplashscreenViewModel(listener);
    }
}
