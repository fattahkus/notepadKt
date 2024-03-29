package com.shrikanthravi.kotpad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shrikanthravi.kotpad.data.DataStore
import com.shrikanthravi.kotpad.data.Note
import kotlinx.android.synthetic.main.activity_new_note.*
import java.util.*

class NewNoteActivity : AppCompatActivity() {

    private var isNew = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        var note: Note? = null
        if(intent.extras!=null) {
            note = intent.extras.get("note") as Note?
            if(note!=null) {
                isNew = false
                titleET.setText(note.title)
                textET.setText(note.text)
            }

        }
        backIV.setOnClickListener{
            super.onBackPressed()
        }

        doneIV.setOnClickListener {
            println("testing isnew $isNew")
            if (isNew) {
                save()
            } else {
                if (note != null) {
                    update(note)
                }
            }
        }
    }

    private fun save() {
        DataStore.execute(Runnable {
            val note = updateNote()
            DataStore.notes.insert(note)
        })
        finish()
    }

    private fun update(note1: Note){
        DataStore.execute(Runnable {
            DataStore.notes.update(updateNote(note1))
        })
        finish()
    }

    private fun updateNote(): Note  {
        val note = Note()
        note.title = titleET.text.toString()
        note.text = textET.text.toString()
        note.updatedAt = Date()
        return note
    }

    private fun updateNote(note :Note): Note  {
        note.title = titleET.text.toString()
        note.text = textET.text.toString()
        note.updatedAt = Date()
        return note
    }
//    private fun delete(note1: Note){
//        DataStore.execute(Runnable {
//            DataStore.notes.delete((deleteNote(note1)))
//        })
//        finish()
//    }
//    private fun deleteNote():Note{
//        val note=Note()
//        note.title = titleET.text.toString()
//        note.text = textET.text.toString()
//        note.deletedAt = Date()
//        return note
//    }
//    private fun deleteNote(note: Note): Note{
//        note.title = titleET.text.toString()
//        note.text = textET.text.toString()
//        note.deletedAt = Date()
//        return note
//    }
}
