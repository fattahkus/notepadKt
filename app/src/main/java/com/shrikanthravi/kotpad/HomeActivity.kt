package com.shrikanthravi.kotpad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.shrikanthravi.kotpad.adapter.NotesAdapter
import com.shrikanthravi.kotpad.data.DataStore
import com.shrikanthravi.kotpad.data.Note
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.note_row_item.*
import org.jetbrains.anko.startActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        fakeAppbarRL.setOnClickListener{
            startActivity<NewNoteActivity>()
        }
        val adapter = NotesAdapter(this)

        notesRV.layoutManager = GridLayoutManager(this, 2)
        notesRV.adapter = adapter

        adapter.setItemClickListener(object : NotesAdapter.ItemClickListener{
            override fun onItemClickListener(data: Note) {
                Snackbar.make(noteCV,"Note Has Been Deleted", Snackbar.LENGTH_SHORT).show()

                DataStore.notes.delete(data)
                adapter.refresh()
            }
        })
    }
//    override fun onSea

    override fun onResume() {
        super.onResume()
        refresh()
    }

    public override fun onDestroy() {
        super.onDestroy()
        notesRV!!.adapter = null
    }

    private fun refresh() {
        (notesRV!!.adapter as NotesAdapter).refresh()
    }


}
