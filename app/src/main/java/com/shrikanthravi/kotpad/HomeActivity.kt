package com.shrikanthravi.kotpad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.shrikanthravi.kotpad.adapter.NotesAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.note_row_item.*
import org.jetbrains.anko.startActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        fakeAppbarRL.setOnClickListener{startActivity<NewNoteActivity>()}
        notesRV.layoutManager = GridLayoutManager(this, 2)
        notesRV.adapter = NotesAdapter(this)

        btnDelete.setOnClickListener {  }
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
