package com.tld.takenotes.viewmodel.notedetail;

import android.view.View;
import android.widget.EditText;

import androidx.lifecycle.MutableLiveData;

import com.tld.takenotes.MainActivityApp;
import com.tld.takenotes.events.CreateNewNote;
import com.tld.takenotes.events.DeleteCurrentNote;
import com.tld.takenotes.events.SaveCurrentNote;
import com.tld.takenotes.model.entity.Note;

import lombok.Getter;
import lombok.Setter;

public class NoteDetailViewModel {

    public MutableLiveData<Note> note = new MutableLiveData<>();
    private NoteDetailListener listener;

    public NoteDetailViewModel(NoteDetailListener listener) {
        this.listener = listener;
    }


    public void DeleteNote(View view)
    {
        MainActivityApp.getBusComponent().getDeleteCurrentNote().onNext(new DeleteCurrentNote(note.getValue()));
    }

    public void SaveNote(View view)
    {
        MainActivityApp.getBusComponent().getSaveCurrentNote().onNext(new SaveCurrentNote(note.getValue()));
    }

    public interface NoteDetailListener {
    }
}
