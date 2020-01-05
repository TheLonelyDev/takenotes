package com.tld.takenotes.viewmodel.note;

import android.content.Context;
import android.view.View;

import androidx.databinding.ObservableField;

import com.tld.takenotes.TakeNotes;
import com.tld.takenotes.domain.events.NoteClickEvent;
import com.tld.takenotes.model.entity.Note;

import lombok.Getter;

public class NoteListViewModel {
    @Getter
    private final ObservableField<String> name;

    @Getter
    private final ObservableField<String> detail;

    @Getter
    private final Note note;

    public NoteListViewModel(Context context, Note note) {
        this.note = note;

        this.name = new ObservableField<>(note.getName());
        this.detail = new ObservableField<>(note.getDetail());
    }

    public void onNoteClicked(View view) {
        TakeNotes.getBusComponent().getOnNoteClicked().onNext(new NoteClickEvent(this.note));
    }
}
