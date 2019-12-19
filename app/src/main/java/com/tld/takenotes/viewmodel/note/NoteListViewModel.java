package com.tld.takenotes.viewmodel.note;

import android.content.Context;

import androidx.databinding.ObservableField;

import com.tld.takenotes.R;
import com.tld.takenotes.model.entity.Note;

import lombok.Getter;

public class NoteListViewModel
{
    @Getter
    private ObservableField<String> name;

    @Getter
    private ObservableField<String> detail;

    private Note note;

    public NoteListViewModel(Context context, Note note)
    {
        this.note = note;

        this.name = new ObservableField<>(context.getString(R.string.name, note.getName()));
        this.detail = new ObservableField<>(context.getString(R.string.detail, note.getDetail()));
    }
}
