package com.tld.takenotes.inject.main;

import dagger.Component;

import com.tld.takenotes.inject.Activity;
import com.tld.takenotes.inject.app.AppComponent;
import com.tld.takenotes.view.main.MainActivity;

@Activity
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent
{
    void inject(MainActivity mainActivity);
}
