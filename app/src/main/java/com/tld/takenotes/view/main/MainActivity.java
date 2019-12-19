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
import com.tld.takenotes.inject.main.DaggerMainComponent;
import com.tld.takenotes.inject.main.MainComponent;
import com.tld.takenotes.inject.main.MainModule;
import com.tld.takenotes.model.entity.Note;
import com.tld.takenotes.view.note.NoteFragment;
import com.tld.takenotes.viewmodel.main.MainViewModel;
import com.tld.takenotes.viewmodel.note.NoteViewModel;

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

        // Tell dagger to set the main module & inject everything
        // tldr; injection
        DaggerMainComponent.builder().appComponent(((MainActivityApp) getApplication()).getAppComponent()).mainModule(new MainModule(this)).build().inject(this);

        // Init views
        // Do two pane detection by finding a specific view; grabbed from the MasterDetailFlow demo
        //viewModel.setTwoPane(findViewById(R.id.detail_container) != null);

        // Set the SavedInstance
        // We use this so to switch between the two view types (fragment will be added to the container automatically)
        // http://developer.android.com/guide/components/fragments.html

        //if (savedInstace == null)
         //   getSupportFragmentManager().beginTransaction().add(R.id.detail_container, NoteFragment.newInstance()).commit();


        binding.setViewModel(viewModel);
    }
}
