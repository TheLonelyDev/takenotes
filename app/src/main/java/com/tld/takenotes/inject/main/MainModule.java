package com.tld.takenotes.inject.main;

import com.tld.takenotes.inject.Activity;
import com.tld.takenotes.viewmodel.main.MainViewModel;

import dagger.Module;
import dagger.Provides;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Module
public class MainModule
{
    MainViewModel.MainListener listener;

    public MainModule(MainViewModel.MainListener listener) {
        this.listener = listener;
    }

    @Provides
    @Activity
    MainViewModel provideMainViewModel()
    {
        return new MainViewModel(listener);
    }
}
