package com.tld.takenotes.model.entity;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.firebase.firestore.DocumentId;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcel;

@Entity
@Parcel(
        value = Parcel.Serialization.BEAN,
        analyze = {Note.class})
public class Note extends BaseObservable {
    @NonNull
    @PrimaryKey
    public String id;
    public String name;

    public String detail;

    @DocumentId
    @Ignore
    public String documentId;

    public Note() {
    }

    @NotNull
    public String getId() {
        return id;
    }

    public void setId(@NotNull String id) {
        this.id = id;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDocumentId() {
        return this.documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
