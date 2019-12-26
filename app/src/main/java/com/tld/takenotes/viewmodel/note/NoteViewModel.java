package com.tld.takenotes.viewmodel.note;

import android.content.res.Resources;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.tld.takenotes.R;
import com.tld.takenotes.TakeNotes;
import com.tld.takenotes.domain.events.CreateNewNote;
import com.tld.takenotes.domain.events.DeleteCurrentNote;
import com.tld.takenotes.domain.events.NoteClickEvent;
import com.tld.takenotes.domain.events.NoteSearch;
import com.tld.takenotes.domain.events.SaveCurrentNote;
import com.tld.takenotes.domain.repository.NoteRepository;
import com.tld.takenotes.domain.util.TextChanged;
import com.tld.takenotes.model.Option;
import com.tld.takenotes.model.entity.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import lombok.Getter;
import lombok.Setter;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;

public class NoteViewModel {
    public MediatorLiveData<List<Note>> notes = new MediatorLiveData<List<Note>>();
    NoteRepository noteRepository;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String lastSearch = "";
    private NoteListener listener;
    private CompositeDisposable disposable;
    @Getter
    @Setter
    private Option option;

    public NoteViewModel(final NoteListener listener, NoteRepository noteRepository, Resources resources) {
        this.listener = listener;
        this.noteRepository = noteRepository;

        disposable = new CompositeDisposable();

        disposable.add(TakeNotes.getBusComponent().getCreateNewNote().observeOn(Schedulers.io()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (o instanceof CreateNewNote) {
                    Note note = new Note();
                    note.setId(UUID.randomUUID().toString());
                    note.setName(resources.getText(R.string.new_note).toString());
                    note.setDetail("");

                    if (option == Option.CLOUD) {
                        db.collection("notes").add(note).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Search();
                                note.setDocumentId(documentReference.getId());
                                TakeNotes.getBusComponent().getOnNoteClicked().onNext(new NoteClickEvent(note));
                            }
                        });
                    } else {
                        noteRepository.newNote(note);

                        Search();
                        TakeNotes.getBusComponent().getOnNoteClicked().onNext(new NoteClickEvent(note));
                    }
                }
            }
        }));

        disposable.add(TakeNotes.getBusComponent().getNoteSearch().observeOn(mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (o instanceof NoteSearch) {
                    lastSearch = ((NoteSearch) o).getKeyword();

                    if (option == Option.CLOUD)
                        db.collection("notes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    List<Note> result = task.getResult().toObjects(Note.class);

                                    if (!lastSearch.isEmpty()) {
                                        List<Note> toRemove = new ArrayList<Note>();

                                        for (Note note : result) {
                                            if (!(note.getName().contains(lastSearch) || note.getDetail().contains(lastSearch)))
                                                toRemove.add(note);
                                        }

                                        result.removeAll(toRemove);
                                    }

                                    notes.postValue(result);
                                }
                            }
                        });
                    else
                        notes.addSource(noteRepository.searchNotes(String.format("%%%s%%", lastSearch)), new Observer<List<Note>>() {
                            @Override
                            public void onChanged(@Nullable List<Note> value) {
                                notes.setValue(value);
                            }
                        });
                }
            }
        }));

        disposable.add(TakeNotes.getBusComponent().getDeleteCurrentNote().observeOn(Schedulers.io()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (o instanceof DeleteCurrentNote) {
                    if (option == Option.CLOUD)
                        db.collection("notes").document(((DeleteCurrentNote) o).getNote().documentId).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Search();
                            }
                        });
                    else {
                        noteRepository.deleteNote(((DeleteCurrentNote) o).getNote());

                        Search();
                    }
                }
            }
        }));

        disposable.add(TakeNotes.getBusComponent().getSaveCurrentNote().observeOn(Schedulers.io()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (o instanceof SaveCurrentNote) {
                    if (option == Option.CLOUD)
                        db.collection("notes").document(((SaveCurrentNote) o).getNote().documentId).set(((SaveCurrentNote) o).getNote()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Search();
                            }
                        });
                    else {
                        noteRepository.updateNote(((SaveCurrentNote) o).getNote());

                        Search();
                    }
                }
            }
        }));

        Search();
    }

    protected void Search() {
        TakeNotes.getBusComponent().getNoteSearch().onNext(new NoteSearch(lastSearch));
    }

    public void onDestroy() {
        disposable.clear();
    }

    public void CreateNewNote(View view) {
        TakeNotes.getBusComponent().getCreateNewNote().onNext(new CreateNewNote());
    }

    public TextChanged onTextChanged() {
        return new TextChanged() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TakeNotes.getBusComponent().getNoteSearch().onNext(new NoteSearch(s.toString().trim()));
            }
        };
    }

    public interface NoteListener {

        void OnLoaded(List<Note> notes);
    }
}
