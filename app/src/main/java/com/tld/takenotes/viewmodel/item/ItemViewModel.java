package com.tld.takenotes.viewmodel.item;

import android.content.Context;

import androidx.databinding.ObservableField;

import com.tld.takenotes.R;
import com.tld.takenotes.model.entity.Note;

public class ItemViewModel
{
    public ObservableField<String> name;
    public ObservableField<String> detail;

    private Note note;

    public ItemViewModel(Context context, Note note)
    {
        this.note = note;

        this.name = new ObservableField<>(context.getString(R.string.name, note.getName()));
        this.detail = new ObservableField<>(context.getString(R.string.detail, note.getDetail()));
    }
}
