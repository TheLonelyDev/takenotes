package com.tld.takenotes.view.notedetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.tld.takenotes.R;
import com.tld.takenotes.model.entity.Note;
import com.tld.takenotes.view.main.MainActivity;

import org.parceler.Parcels;

public class NoteDetailActivity extends AppCompatActivity {
    public static Intent newIntent(Context context, Note note) {
        Intent intent = new Intent(context, NoteDetailActivity.class);
        Bundle bundle = new Bundle();

        bundle.putParcelable("key_note", Parcels.wrap(note));

        intent.putExtras(bundle);

        return intent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        Note note = Parcels.unwrap(getIntent().getExtras().getParcelable("key_note"));

        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.item_detail_container, NoteDetailFragment.newFragment(note)).commitAllowingStateLoss();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
