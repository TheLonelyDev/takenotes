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
    @Getter @Setter
    private List<Note> notes;

    public NoteAdapter()
    {
        Note n1 = new Note();
        n1.setName("Brainstorming");
        n1.setDetail("TODO, IDEAS");

        Note n2 = new Note();
        n2.setName("Shoppinglist");
        n2.setDetail("SHOPPING");

        notes = new ArrayList<Note>(){};
        notes.add(n1);
        notes.add(n2);
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
