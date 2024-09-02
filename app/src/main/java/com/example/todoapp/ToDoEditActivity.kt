package com.example.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.databinding.ActivityToDoEditBinding

class ToDoEditActivity : AppCompatActivity() {
    lateinit var binding: ActivityToDoEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToDoEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}