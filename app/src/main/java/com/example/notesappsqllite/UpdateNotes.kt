package com.example.notesappsqllite

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notesappsqllite.databinding.ActivityUpdateNotesBinding

class UpdateNotes : AppCompatActivity() {
    private lateinit var binder: ActivityUpdateNotesBinding
    private lateinit var db:SqlliteDBhelper
    private var noteid:Int=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder=ActivityUpdateNotesBinding.inflate(layoutInflater)
        setContentView(binder.root)

        db=SqlliteDBhelper(this)
         noteid=intent.getIntExtra("id_col",-1)
        if (noteid==-1){
            finish()
            return
        }

        val note=db.getNoteById(noteid)
        binder.title.setText(note.title)
        binder.content.setText(note.content)
        binder.saveNote.setOnClickListener(){
            val newtitle=binder.title.text.toString()
            val newcontent=binder.content.text.toString()
            val updatenote=Note(noteid,newtitle,newcontent)
            db.update(updatenote)
            finish()
            Toast.makeText(this,"Note Updated",Toast.LENGTH_SHORT).show()
        }
    }
}