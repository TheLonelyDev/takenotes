package com.tld.takenotes.dagger2.splashscreen;

import com.tld.takenotes.dagger2.Activity;
import com.tld.takenotes.viewmodel.splashscreen.SplashscreenViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashscreenModule {
    private final SplashscreenViewModel.SplashscreenListener listener;

    public SplashscreenModule(SplashscreenViewModel.SplashscreenListener listener) {
        this.listener = listener;
    }

    @Provides
    @Activity
    SplashscreenViewModel provideMainViewModel() {
        return new SplashscreenViewModel(listener);
    }
}
