package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var toDoAdapter: toDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toDoAdapter = toDoAdapter(mutableListOf())

        recyclerViewToDoItems.adapter = toDoAdapter
        recyclerViewToDoItems.layoutManager = LinearLayoutManager(this)

        buttonAddToDo.setOnClickListener {
            val toDoTitle = editTextToDoTitle.text.toString()
            if(toDoTitle.isNotEmpty()) {
                val todo = toDo(toDoTitle)
                toDoAdapter.addToDo(todo)
                editTextToDoTitle.text.clear()
            }
        }

        buttonDeleteToDo.setOnClickListener {
            toDoAdapter.deleteDoneToDos()
        }
    }
}