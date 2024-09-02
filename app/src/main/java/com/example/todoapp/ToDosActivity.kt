package com.example.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.databinding.ActivityToDosBinding

class ToDosActivity : AppCompatActivity() {
    lateinit var binding: ActivityToDosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToDosBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}