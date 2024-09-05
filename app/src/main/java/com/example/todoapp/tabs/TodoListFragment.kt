package com.example.todoapp.tabs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todoapp.ToDoAdapter
import com.example.todoapp.ToDoEditActivity
import com.example.todoapp.database.MyDataBase
import com.example.todoapp.database.Todo
import com.example.todoapp.databinding.FragmentTodoListBinding
import com.example.todoapp.utilitis.clearTime
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.Calendar

class TodoListFragment : Fragment() {
    lateinit var binding: FragmentTodoListBinding
    val todolist = mutableListOf<Todo>()
    lateinit var toDoAdapter: ToDoAdapter
    var selectedDay = CalendarDay.today()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        refreshTodoList()
        initCalenderSelectedListeners()
    }

    private fun initCalenderSelectedListeners() {
        binding.calendarView.selectedDate = CalendarDay.today()
        binding.calendarView.setOnDateChangedListener { widget, date, selected ->
            selectedDay = date
            refreshTodoList()
        }
    }

    fun refreshTodoList() {
        val calender = Calendar.getInstance()
        calender.set(Calendar.YEAR, selectedDay.year)
        calender.set(Calendar.MONTH, selectedDay.month - 1)
        calender.set(Calendar.DAY_OF_MONTH, selectedDay.day)
        calender.clearTime()
        val todoListdata = MyDataBase.getInstance(requireContext())
            .getTodosDao().getTodosByDate(calender.timeInMillis)
        toDoAdapter.refreshList(todoListdata)
    }

    private fun initRecyclerView() {
        toDoAdapter = ToDoAdapter(todolist)
        toDoAdapter.listeners = object : ToDoAdapter.onItemClickListeners {
            override fun onDeleteClick(todo: Todo) {
                MyDataBase.getInstance(requireContext()).getTodosDao().deleteTodo(todo)
                refreshTodoList()
            }

            override fun onDoneClick(todo: Todo) {
                MyDataBase.getInstance(requireContext()).getTodosDao().updateTodo(todo)
                todo.isDone = true
                refreshTodoList()
            }

            override fun onItemClick(todo: Todo) {
                val intent = Intent(context, ToDoEditActivity::class.java).apply {
                    putExtra(ToDoEditActivity.TITLE, todo.title)
                    putExtra(ToDoEditActivity.DESCRIPTION, todo.description)
                }
                startActivity(intent)
            }

        }
        binding.todosListRV.adapter = toDoAdapter
    }

}
