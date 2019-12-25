package com.tld.takenotes.inject.splashscreen;

import com.tld.takenotes.inject.Activity;
import com.tld.takenotes.inject.app.AppComponent;
import com.tld.takenotes.view.splashscreen.SplashscreenActivity;

import dagger.Component;

@Activity
@Component(dependencies = AppComponent.class, modules = SplashscreenModule.class)
public interface SplashscreenComponent {
    void inject(SplashscreenActivity mainActivity);
}