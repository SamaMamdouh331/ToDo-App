package com.example.todoapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.todoapp.database.MyDataBase
import com.example.todoapp.database.Todo
import com.example.todoapp.databinding.BottomSheetBinding
import com.example.todoapp.utilitis.clearTime
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar

class AddBottomSheet(val onAddClick: () -> Unit) : BottomSheetDialogFragment() {
    lateinit var binding: BottomSheetBinding
    var selectedDate = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSelectedDate()
        updateDateTextView()
        listeners()

    }

    private fun listeners() {
        binding.AddNewTodo.setOnClickListener {
            if (!isValidInputs()) return@setOnClickListener
            selectedDate.clearTime()

            val title = binding.enterYourTitle.editText!!.text.toString()
            val description = binding.description.editText!!.text.toString()

            val newTodo = Todo(
                title = title, description = description,
                date = selectedDate.timeInMillis,
                isDone = false
            )
            MyDataBase.getInstance(requireContext()).getTodosDao().addTodo(newTodo)
            onAddClick.invoke()
            dismiss()
        }

        binding.enterYourTitle.editText?.addTextChangedListener {
            isValidInputs()
        }
        binding.description.editText?.addTextChangedListener {
            isValidInputs()
        }
    }

    fun isValidInputs(): Boolean {
        val title = binding.enterYourTitle.editText!!.text
        val description = binding.description.editText!!.text
        var isValid = true
        if (title.isNullOrEmpty()) {
            binding.enterYourTitle.error = "please enter valid title"
            isValid = false
        } else {
            binding.enterYourTitle.error = null
        }
        if (description.isNullOrEmpty()) {
            binding.description.error = "please enter the description"
            isValid = false
        } else {
            binding.description.error = null
        }
        return isValid
    }

    private fun initSelectedDate() {
        binding.dateTV.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { p0, year, month, day ->
                    selectedDate.set(Calendar.YEAR, year)
                    selectedDate.set(Calendar.MONTH, month)
                    selectedDate.set(Calendar.DAY_OF_MONTH, day)
                    updateDateTextView()
                },
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }
    }

    private fun updateDateTextView() {
        val day = selectedDate.get(Calendar.DAY_OF_MONTH)
        val month = selectedDate.get(Calendar.MONTH)
        val year = selectedDate.get(Calendar.YEAR)
        binding.dateTV.text = "$day / ${month + 1} / $year "
    }
}