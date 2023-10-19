package com.example.notesappsqllite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notesappsqllite.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db:SqlliteDBhelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db= SqlliteDBhelper(this)
        binding.saveNote.setOnClickListener(){
            var title=binding.title.text.toString()
            var  content=binding.content.text.toString()
            var note=Note(0,title,content)
            db.addNote(note)
            finish()
            Toast.makeText(this,"Note Saved",Toast.LENGTH_SHORT).show()
        }

    }
}