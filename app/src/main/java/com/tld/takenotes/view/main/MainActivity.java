package com.tld.takenotes.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.tld.takenotes.MainActivityApp;
import com.tld.takenotes.R;
import com.tld.takenotes.databinding.ActivityMainBinding;
import com.tld.takenotes.inject.main.DaggerMainComponent;
import com.tld.takenotes.inject.main.MainModule;
import com.tld.takenotes.model.Option;
import com.tld.takenotes.model.entity.Note;
import com.tld.takenotes.view.note.NoteFragment;
import com.tld.takenotes.view.notedetail.NoteDetailActivity;
import com.tld.takenotes.view.notedetail.NoteDetailFragment;
import com.tld.takenotes.viewmodel.main.MainViewModel;

import org.parceler.Parcels;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainViewModel.MainListener {
    @Inject
    protected MainViewModel viewModel;
    private ActivityMainBinding binding;

    public static Intent newIntent(Context context, Option option) {
        Intent intent = new Intent(context, MainActivity.class);
        Bundle bundle = new Bundle();

        bundle.putParcelable("key_option", Parcels.wrap(option));

        intent.putExtras(bundle);

        return intent;
    }

    @Override
    public void onNoteClicked(Note note) {
        if (!viewModel.isTwoPane())
            startActivityForResult(NoteDetailActivity.newIntent(this, note), 1);
        else if (!isFinishing())
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_container, NoteDetailFragment.newFragment(note))
                    .commitAllowingStateLoss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Option option = Parcels.unwrap(getIntent().getExtras().getParcelable("key_option"));
        viewModel.setOption(option);

        // Tell dagger to set the main module & inject everything
        // tldr; injection
        DaggerMainComponent.builder().appComponent(((MainActivityApp) getApplication()).getAppComponent()).mainModule(new MainModule(this)).build().inject(this);

        // Init views
        // Do two pane detection by finding a specific view; grabbed from the MasterDetailFlow demo
        viewModel.setTwoPane(findViewById(R.id.detail_container) != null);

        // Set the SavedInstance
        // We use this so to switch between the two view types (fragment will be added to the container automatically)
        // http://developer.android.com/guide/components/fragments.html

        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.main_container, NoteFragment.newFragment()).commitAllowingStateLoss();

        binding.setViewModel(viewModel);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
}
