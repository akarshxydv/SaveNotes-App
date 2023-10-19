package com.example.notesappsqllite

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(var listnote:List<Note>,context:Context): RecyclerView.Adapter<NoteAdapter.viewHolder>() {
    private var db=SqlliteDBhelper(context)
    class viewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val title:TextView=itemView.findViewById(R.id.title)
        val content:TextView=itemView.findViewById(R.id.content)
        val update:ImageButton=itemView.findViewById(R.id.update)
        val delete:ImageButton=itemView.findViewById(R.id.delete)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.viewHolder {
      val view=LayoutInflater.from(parent.context).inflate(R.layout.noteitem,parent,false)
        return viewHolder(view)

    }

    override fun onBindViewHolder(holder: NoteAdapter.viewHolder, position: Int) {
            val note=listnote[position]
            holder.title.text=note.title
        holder.content.text=note.content
        holder.update.setOnClickListener(){
            var intent=Intent(holder.itemView.context,UpdateNotes::class.java).apply {
                putExtra("id_col",note.id)
            }
            holder.itemView.context.startActivity(intent)
        }
        holder.delete.setOnClickListener(){
            db.deleteNote(note.id)
            refresh(db.getNotes())
            Toast.makeText(holder.itemView.context,"Note deleted",Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return listnote.size
    }
    fun refresh(newnote:List<Note>){
        listnote=newnote
        notifyDataSetChanged()
    }

}