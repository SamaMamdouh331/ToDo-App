package com.example.todoapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.databinding.ActivityToDoEditBinding

class ToDoEditActivity : AppCompatActivity() {
    companion object {
        const val TITLE = "title"
        const val DESCRIPTION = "description"
    }

    lateinit var title: String
    lateinit var description: String
    lateinit var binding: ActivityToDoEditBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToDoEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDetails()
        saveClik()

    }

    private fun saveClik() {
        binding.editSaveButton.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java).apply {
            }
            startActivity(intent)
        }
    }


    private fun initDetails() {
        title = intent.getStringExtra(TITLE)!!
        description = intent.getStringExtra(DESCRIPTION)!!
        var titleEditable = Editable.Factory.getInstance().newEditable(title)
        var descriptionEditable = Editable.Factory.getInstance().newEditable(description)
        binding.taskTitle.text = titleEditable
        binding.taskDetails.text = descriptionEditable
    }
}