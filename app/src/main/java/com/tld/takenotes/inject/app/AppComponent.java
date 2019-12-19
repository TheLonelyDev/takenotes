package com.tld.takenotes.inject.app;

import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent
{
    SharedPreferences sharedPreferences();
}
