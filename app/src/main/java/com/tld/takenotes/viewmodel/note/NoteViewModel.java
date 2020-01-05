package com.tld.takenotes.viewmodel.note;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QuerySnapshot;
import com.tld.takenotes.R;
import com.tld.takenotes.TakeNotes;
import com.tld.takenotes.domain.events.CreateNewNote;
import com.tld.takenotes.domain.events.DeleteCurrentNote;
import com.tld.takenotes.domain.events.NoteClickEvent;
import com.tld.takenotes.domain.events.NoteSearch;
import com.tld.takenotes.domain.events.SaveCurrentNote;
import com.tld.takenotes.domain.events.ToastEvent;
import com.tld.takenotes.domain.repository.NoteRepository;
import com.tld.takenotes.domain.util.TextChanged;
import com.tld.takenotes.model.Option;
import com.tld.takenotes.model.entity.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import lombok.Getter;
import lombok.Setter;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;

public class NoteViewModel {
    public final MediatorLiveData<List<Note>> notes = new MediatorLiveData<>();
    private final FirebaseFirestore db;
    private String lastSearch;
    private final CompositeDisposable disposable;
    @Getter
    @Setter
    private Option option;
    @Getter
    private final ObservableBoolean loading;

    private final Context context;

    public NoteViewModel(Context context, final NoteListener listener, NoteRepository noteRepository, Resources resources) {
        this.context = context;
        this.loading = new ObservableBoolean();
        this.lastSearch = "";

        this.db = FirebaseFirestore.getInstance();

        this.db.setFirestoreSettings(new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(false)
                .build());

        this.loading.set(true);

        this.disposable = new CompositeDisposable();

        this.disposable.add(TakeNotes.getBusComponent().getCreateNewNote().observeOn(Schedulers.io()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                if (o instanceof CreateNewNote) {
                    Note note = new Note();
                    note.setId(UUID.randomUUID().toString());
                    note.setName(resources.getText(R.string.new_note).toString());
                    note.setDetail("");

                    if (option == Option.CLOUD) {
                        if (cmCheck())
                            db.collection("notes").add(note).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Search();
                                    note.setDocumentId(documentReference.getId());

                                    Toast(R.string.note_created);

                                    TakeNotes.getBusComponent().getOnNoteClicked().onNext(new NoteClickEvent(note));
                                }
                            });
                    } else {
                        noteRepository.newNote(note);

                        Search();

                        Toast(R.string.note_created);

                        TakeNotes.getBusComponent().getOnNoteClicked().onNext(new NoteClickEvent(note));
                    }
                }
            }
        }));

        this.disposable.add(TakeNotes.getBusComponent().getNoteSearch().observeOn(mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                if (o instanceof NoteSearch) {
                    loading.set(true);
                    lastSearch = ((NoteSearch) o).getKeyword().toLowerCase();

                    if (option == Option.CLOUD) {
                        if (cmCheck())
                            db.collection("notes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        List<Note> result = Objects.requireNonNull(task.getResult()).toObjects(Note.class);

                                        if (!lastSearch.isEmpty()) {
                                            List<Note> toRemove = new ArrayList<>();

                                            for (Note note : result) {
                                                if (!(note.getName().toLowerCase().contains(lastSearch) || note.getDetail().toLowerCase().contains(lastSearch)))
                                                    toRemove.add(note);
                                            }

                                            result.removeAll(toRemove);
                                        }

                                        notes.postValue(result);
                                        loading.set(false);
                                    }
                                }
                            });
                    }
                    else
                        notes.addSource(noteRepository.searchNotes(String.format("%%%s%%", lastSearch)), new Observer<List<Note>>() {
                            @Override
                            public void onChanged(@Nullable List<Note> value) {
                                notes.setValue(value);
                                loading.set(false);
                            }
                        });
                }
            }
        }));

        this.disposable.add(TakeNotes.getBusComponent().getDeleteCurrentNote().observeOn(Schedulers.io()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                if (o instanceof DeleteCurrentNote) {
                    if (option == Option.CLOUD) {
                        if (cmCheck())
                            db.collection("notes").document(((DeleteCurrentNote) o).getNote().documentId).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Search();

                                    Toast(R.string.note_deleted);
                                }
                            });
                    }
                    else {
                        noteRepository.deleteNote(((DeleteCurrentNote) o).getNote());

                        Search();

                        Toast(R.string.note_deleted);
                    }
                }
            }
        }));

        this.disposable.add(TakeNotes.getBusComponent().getSaveCurrentNote().observeOn(Schedulers.io()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                if (o instanceof SaveCurrentNote) {
                    if (option == Option.CLOUD) {
                        if (cmCheck())
                            db.collection("notes").document(((SaveCurrentNote) o).getNote().documentId).set(((SaveCurrentNote) o).getNote()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Search();

                                    Toast(R.string.note_saved);
                                }
                            });
                    }
                    else {
                        noteRepository.updateNote(((SaveCurrentNote) o).getNote());

                        Search();

                        Toast(R.string.note_saved);
                    }
                }
            }
        }));

        this.disposable.add(TakeNotes.getBusComponent().getToast().observeOn(mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                if (o instanceof ToastEvent) {
                    listener.ShowToast(((ToastEvent) o).getResourceId());
                }
            }
        }));

        if (cmCheck())
            Search();
    }

    private boolean cmCheck() {
        if (option == Option.CLOUD) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            // API 16 support
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

            if (!isConnected)
                TakeNotes.getBusComponent().getToast().onNext(new ToastEvent(R.string.no_network));

            return isConnected;
        }

        return true;
    }

    private void Search() {
        TakeNotes.getBusComponent().getNoteSearch().onNext(new NoteSearch(lastSearch));
    }

    private void Toast(int resourceId) {
        TakeNotes.getBusComponent().getToast().onNext(new ToastEvent(resourceId));
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

        void ShowToast(int resourceId);
    }
}
