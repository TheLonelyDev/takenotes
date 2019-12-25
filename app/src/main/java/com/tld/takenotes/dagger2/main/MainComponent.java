package com.tld.takenotes.dagger2.main;

import com.tld.takenotes.dagger2.Activity;
import com.tld.takenotes.dagger2.app.AppComponent;
import com.tld.takenotes.view.main.MainActivity;

import dagger.Component;

@Activity
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
