package com.tld.takenotes.view.splashscreen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.tld.takenotes.R;
import com.tld.takenotes.TakeNotes;
import com.tld.takenotes.dagger2.splashscreen.DaggerSplashscreenComponent;
import com.tld.takenotes.dagger2.splashscreen.SplashscreenModule;
import com.tld.takenotes.databinding.ActivitySplashscreenBinding;
import com.tld.takenotes.domain.api.bing.BingInterface;
import com.tld.takenotes.domain.api.bing.Entity.BingImageResponse;
import com.tld.takenotes.model.Option;
import com.tld.takenotes.view.main.MainActivity;
import com.tld.takenotes.viewmodel.splashscreen.SplashscreenViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashscreenActivity extends AppCompatActivity implements SplashscreenViewModel.SplashscreenListener {
    @SuppressWarnings("WeakerAccess")
    @Inject
    protected SplashscreenViewModel viewModel;

    @SuppressWarnings("WeakerAccess")
    @Inject
    protected BingInterface bingInterface;

    @Override
    public void onOptionClicked(Option option) {
        startActivity(MainActivity.newIntent(this, option));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySplashscreenBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_splashscreen);

        DaggerSplashscreenComponent.builder().appComponent(((TakeNotes) getApplication()).getAppComponent()).splashscreenModule(new SplashscreenModule(this)).build().inject(this);

        bingInterface.getImage().enqueue(new Callback<BingImageResponse>() {
            @Override
            public void onResponse(@NotNull Call<BingImageResponse> call, @NotNull Response<BingImageResponse> response) {
                BingImageResponse bingImageResponse = response.body();
                ((TakeNotes) getApplication()).getBingImage().setValue(Objects.requireNonNull(bingImageResponse).getImages().get(0).getUrl());
            }

            @Override
            public void onFailure(@NotNull Call<BingImageResponse> call, @NotNull Throwable t) {

            }
        });

        binding.setViewModel(viewModel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
}
