package com.tld.takenotes.viewmodel.note;

import android.content.Context;
import android.view.View;

import androidx.databinding.ObservableField;

import com.tld.takenotes.R;
import com.tld.takenotes.model.entity.Note;
import com.tld.takenotes.model.entity.Tag;
import com.tld.takenotes.viewmodel.tag.TagClickEvent;

import lombok.Getter;

public class NoteListViewModel
{
    @Getter
    private ObservableField<String> name;

    @Getter
    private ObservableField<String> detail;

    //@Getter
    //private ObservableField<Tag> tags;

    @Getter
    private Note note;

    private RxBus bus;

    public NoteListViewModel(Context context, Note note, RxBus bus)
    {
        this.note = note;
        this.bus = bus;

        this.name = new ObservableField<>(context.getString(R.string.name, note.getName()));
        this.detail = new ObservableField<>(context.getString(R.string.detail, note.getDetail()));
        //this.tags = new ObservableField<>(context.getString(R.string.detail, note.getTags()));
    }

    public void onNoteClicked(View view)
    {
        bus.send(new NoteClickEvent(note));
    }
}
