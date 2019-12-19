package com.tld.takenotes.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.tld.takenotes.MainActivityApp;
import com.tld.takenotes.R;
import com.tld.takenotes.databinding.ActivityMainBinding;
import com.tld.takenotes.inject.app.DaggerAppComponent;
import com.tld.takenotes.inject.main.MainComponent;
import com.tld.takenotes.inject.main.MainModule;
import com.tld.takenotes.model.entity.Note;
import com.tld.takenotes.viewmodel.main.MainViewModel;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainViewModel.MainListener
{
    private ActivityMainBinding binding;

    @Inject
    protected MainViewModel viewModel;

    public static Intent newIntent(Context context)
    {
        Intent intent = new Intent(context, MainActivity.class);
        Bundle bundle = new Bundle();

        intent.putExtras(bundle);

        return intent;
    }

    @Override
    public void onNoteClicked(Note note)
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setViewModel(viewModel);
    }
}
