package com.tld.takenotes.view.note;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.tld.takenotes.databinding.NoteListBinding;
import com.tld.takenotes.model.entity.Note;
import com.tld.takenotes.viewmodel.note.NoteListViewModel;
import com.tld.takenotes.viewmodel.note.NoteViewModel;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder>
{
    private List<Note> notes;

    public NoteAdapter()
    {
        notes = new ArrayList<Note>(){};
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public List<Note> getNotes(){
        return notes;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return NoteViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position)
    {
        holder.bind(new NoteListViewModel(holder.itemView.getContext(), notes.get(position)));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    static class NoteViewHolder extends RecyclerView.ViewHolder
    {
        NoteListBinding binding;

        static NoteAdapter.NoteViewHolder create(LayoutInflater inflater, ViewGroup parent)
        {
            NoteListBinding binding = NoteListBinding.inflate(inflater, parent, false);
            return new NoteAdapter.NoteViewHolder(binding);
        }

        public NoteViewHolder(NoteListBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(NoteListViewModel viewModel)
        {
            binding.setViewModel(viewModel);
            binding.executePendingBindings();
        }
    }
}
