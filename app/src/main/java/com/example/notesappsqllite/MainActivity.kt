package com.example.notesappsqllite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesappsqllite.databinding.ActivityAddNoteBinding
import com.example.notesappsqllite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var db:SqlliteDBhelper
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db= SqlliteDBhelper(this)
        noteAdapter= NoteAdapter(db.getNotes(),this)
        binding.recyclerview.layoutManager=LinearLayoutManager(this)
        binding.recyclerview.adapter=noteAdapter


        binding.Addnote.setOnClickListener(){
            startActivity(Intent(this,AddNoteActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        noteAdapter.refresh(db.getNotes())

    }
}