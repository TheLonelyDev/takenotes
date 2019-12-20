package com.tld.takenotes;

import android.app.Application;

import com.tld.takenotes.inject.app.AppComponent;
import com.tld.takenotes.inject.app.AppModule;
import com.tld.takenotes.inject.app.DaggerAppComponent;
import com.tld.takenotes.inject.bus.BusComponent;
import com.tld.takenotes.inject.bus.DaggerBusComponent;

import lombok.Getter;

public class MainActivityApp extends Application {

    @Getter
    private AppComponent appComponent;

    @Getter
    private static BusComponent busComponent;

    @Override
    public void onCreate()
    {
        super.onCreate();

        this.appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        busComponent = DaggerBusComponent.create();
    }
}
