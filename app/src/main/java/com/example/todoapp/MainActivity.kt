package com.example.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.tabs.TodoListFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var listFragment = TodoListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showListFragment()
        binding.addButton.setOnClickListener {
            val addBottomSheet = AddBottomSheet {
                listFragment.refreshTodoList()
            }
            addBottomSheet.show(supportFragmentManager, null)
        }
        binding.bottomNaviBar.setOnItemSelectedListener {
            if (it.itemId == R.id.todosettings) {
                return@setOnItemSelectedListener false
            } else {
                showListFragment()
            }

            return@setOnItemSelectedListener true
        }
    }

    private fun showListFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, listFragment)
            .commit()
    }
}