package com.tld.takenotes;

import androidx.multidex.MultiDexApplication;

import com.tld.takenotes.dagger2.app.AppComponent;
import com.tld.takenotes.dagger2.app.AppModule;
import com.tld.takenotes.dagger2.app.DaggerAppComponent;
import com.tld.takenotes.dagger2.bus.BusComponent;
import com.tld.takenotes.dagger2.bus.DaggerBusComponent;
import com.tld.takenotes.dagger2.room.RoomModule;

import lombok.Getter;

public class MainActivityApp extends MultiDexApplication {

    @Getter
    private static BusComponent busComponent;
    @Getter
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        this.appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).roomModule(new RoomModule(this)).build();
        busComponent = DaggerBusComponent.create();
    }
}
