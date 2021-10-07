package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.icu.text.CaseMap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class toDoAdapter (
    private val toDos: MutableList<toDo>
    ): RecyclerView.Adapter<toDoAdapter.toDoViewHolder>() {

    class toDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): toDoViewHolder {
        return toDoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addToDo(todo: toDo){
        toDos.add(todo )
        notifyItemInserted(toDos.size - 1)
    }

    fun deleteDoneToDos(){
        toDos.removeAll { toDo ->
            toDo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(textViewToDoTitle: TextView, isChecked: Boolean) {
        if (isChecked){
            textViewToDoTitle.paintFlags = textViewToDoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            textViewToDoTitle.paintFlags = textViewToDoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: toDoViewHolder, position: Int) {
        var currentToDo = toDos[position]
        holder.itemView.apply{
            textViewToDoTitle.text = currentToDo.title
            checkBoxDone.isChecked = currentToDo.isChecked
            toggleStrikeThrough(textViewToDoTitle, currentToDo.isChecked)
            checkBoxDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(textViewToDoTitle, isChecked)
                currentToDo.isChecked = !currentToDo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return toDos.size
    }
}