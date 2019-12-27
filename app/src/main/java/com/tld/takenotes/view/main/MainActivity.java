package com.tld.takenotes.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.tld.takenotes.R;
import com.tld.takenotes.TakeNotes;
import com.tld.takenotes.dagger2.main.DaggerMainComponent;
import com.tld.takenotes.dagger2.main.MainModule;
import com.tld.takenotes.databinding.ActivityMainBinding;
import com.tld.takenotes.model.Option;
import com.tld.takenotes.model.entity.Note;
import com.tld.takenotes.view.note.NoteFragment;
import com.tld.takenotes.view.notedetail.NoteDetailActivity;
import com.tld.takenotes.view.notedetail.NoteDetailFragment;
import com.tld.takenotes.viewmodel.main.MainViewModel;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainViewModel.MainListener {
    @Inject
    protected MainViewModel viewModel;
    private ActivityMainBinding binding;

    public static Intent newIntent(Context context, Option option) {
        Intent intent = new Intent(context, MainActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString("key_option", option.name());

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

        DaggerMainComponent.builder().appComponent(((TakeNotes) getApplication()).getAppComponent()).mainModule(new MainModule(this)).build().inject(this);

        Option option = Option.valueOf(getIntent().getExtras().getString("key_option"));
        viewModel.setOption(option);


        viewModel.setTwoPane(findViewById(R.id.detail_container) != null);

        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.main_container, NoteFragment.newFragment(viewModel.getOption())).commitAllowingStateLoss();

        binding.setViewModel(viewModel);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
