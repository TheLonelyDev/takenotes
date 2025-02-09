package com.tld.takenotes;

import androidx.lifecycle.MutableLiveData;
import androidx.multidex.MultiDexApplication;

import com.tld.takenotes.dagger2.app.AppComponent;
import com.tld.takenotes.dagger2.app.AppModule;
import com.tld.takenotes.dagger2.app.DaggerAppComponent;
import com.tld.takenotes.dagger2.app.NetworkModule;
import com.tld.takenotes.dagger2.bus.BusComponent;
import com.tld.takenotes.dagger2.bus.DaggerBusComponent;
import com.tld.takenotes.dagger2.room.RoomModule;

import lombok.Getter;

public class TakeNotes extends MultiDexApplication {
    @Getter
    private static BusComponent busComponent;
    @Getter
    private AppComponent appComponent;
    @Getter
    private MutableLiveData<String> bingImage;

    @Override
    public void onCreate() {
        super.onCreate();

        this.appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).roomModule(new RoomModule(this)).networkModule(new NetworkModule()).build();
        busComponent = DaggerBusComponent.create();

        this.bingImage = new MutableLiveData<>();
    }
}
