package com.example.todoapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.entity.Note
import com.example.todoapp.ui.fragments.HomeFragmentDirections

class NoteRecyclerView: ListAdapter<Note, NoteRecyclerViewHolder>(NoteComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)

        return NoteRecyclerViewHolder(view)

    }

    override fun onBindViewHolder(holder: NoteRecyclerViewHolder, position: Int) {
        val note = getItem(position)
        holder.title.text = note.title
        holder.description.text = note.description
        holder.itemView.setOnClickListener{
            Navigation.findNavController(it)
                .navigate(
                    HomeFragmentDirections
                        .actionHomeFragmentToUpdateNoteFragment()
                )
        }
    }
}

class NoteRecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.title)
    val description: TextView = itemView.findViewById(R.id.description)



}

class NoteComparator: DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }

}
