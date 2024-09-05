package com.example.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todoapp.database.Todo
import com.example.todoapp.databinding.ItemTodoBinding

class ToDoAdapter(var TodosList: List<Todo>) :
    Adapter<ToDoAdapter.TodosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodosViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodosViewHolder(binding = binding)
    }

    override fun getItemCount(): Int = TodosList.size

    override fun onBindViewHolder(holder: TodosViewHolder, position: Int) {
        val todo = TodosList[position]
        holder.binding.todoItemTitle.text = todo.title
        holder.binding.todoItemDate.text = todo.description
        holder.binding.leftView.setOnClickListener {
            listeners?.onDeleteClick(todo)
        }
        holder.binding.isdoneButton.setOnClickListener {
            listeners?.onDoneClick(todo)
            todo.isDone = true
            holder.binding.doneText.visibility = if (todo.isDone) View.VISIBLE else View.GONE
            holder.binding.isdoneButton.visibility = if (todo.isDone) View.GONE else View.VISIBLE
            val doneColor = ContextCompat.getColor(holder.binding.root.context, R.color.green)
            holder.binding.todoItemTitle.setTextColor(doneColor)
            holder.binding.swipeLine.setBackgroundColor(doneColor)
        }
        holder.binding.todoItemCardView.setOnClickListener {
            listeners?.onItemClick(todo)

        }

    }

    class TodosViewHolder(val binding: ItemTodoBinding) : ViewHolder(binding.root)

    var listeners: onItemClickListeners? = null

    interface onItemClickListeners {
        fun onDeleteClick(todo: Todo)
        fun onDoneClick(todo: Todo)
        fun onItemClick(todo: Todo)
    }

    fun refreshList(newList: List<Todo>) {
        TodosList = newList
        notifyDataSetChanged()
    }
}