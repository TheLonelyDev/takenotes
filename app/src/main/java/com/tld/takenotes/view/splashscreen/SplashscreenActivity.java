package com.tld.takenotes.view.splashscreen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.tld.takenotes.MainActivityApp;
import com.tld.takenotes.R;
import com.tld.takenotes.databinding.ActivitySplashscreenBinding;
import com.tld.takenotes.inject.splashscreen.DaggerSplashscreenComponent;
import com.tld.takenotes.inject.splashscreen.SplashscreenModule;
import com.tld.takenotes.model.entity.Option;
import com.tld.takenotes.viewmodel.splashscreen.SplashscreenViewModel;

import javax.inject.Inject;

public class SplashscreenActivity extends AppCompatActivity implements SplashscreenViewModel.SplashscreenListener {
    @Inject
    SplashscreenViewModel viewModel;
    private ActivitySplashscreenBinding binding;

    @Override
    public void onOptionClicked(Option option) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        DaggerSplashscreenComponent.builder().appComponent(((MainActivityApp) getApplication()).getAppComponent()).splashscreenModule(new SplashscreenModule(this)).build().inject(this);

        binding.setViewModel(viewModel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
}
