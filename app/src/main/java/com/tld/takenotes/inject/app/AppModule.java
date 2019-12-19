package com.tld.takenotes.inject.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Module
public class AppModule
{
    private Application application;

    public AppModule(Application app) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication()
    {
        return this.application;
    }

    @Provides
    @Singleton
    Context provideContext() { return this.application.getApplicationContext(); }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Application application)
    {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }


}
