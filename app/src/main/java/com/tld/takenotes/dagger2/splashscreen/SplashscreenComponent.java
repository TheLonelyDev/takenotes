package com.tld.takenotes.dagger2.splashscreen;

import com.tld.takenotes.dagger2.Activity;
import com.tld.takenotes.dagger2.app.AppComponent;
import com.tld.takenotes.view.splashscreen.SplashscreenActivity;

import dagger.Component;

@Activity
@Component(dependencies = AppComponent.class, modules = SplashscreenModule.class)
public interface SplashscreenComponent {
    void inject(SplashscreenActivity mainActivity);
}