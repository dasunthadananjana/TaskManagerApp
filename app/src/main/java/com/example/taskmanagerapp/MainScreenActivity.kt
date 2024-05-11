package com.example.taskmanagerapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainScreenActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewTasks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TaskAdapter(emptyList())
        recyclerView.adapter = adapter

        val addButton: Button = findViewById(R.id.button3)
        addButton.setOnClickListener {
            showAddTaskDialog()
        }

        loadTasks()
    }

    private fun showAddTaskDialog() {
        val layoutInflater = LayoutInflater.from(this)
        val view = layoutInflater.inflate(R.layout.dialog_add_task, null)

        val titleEditText = view.findViewById<EditText>(R.id.editTextTitle)
        val descriptionEditText = view.findViewById<EditText>(R.id.editTextDescription)
        val dateEditText = view.findViewById<EditText>(R.id.editTextDate)
        val addButton = view.findViewById<Button>(R.id.buttonAddTask)

        val dialog = AlertDialog.Builder(this)
            .setView(view)
            .create()

        dateEditText.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(this, { _, year, month, dayOfMonth ->
                val dateString = "${dayOfMonth.toString().padStart(2, '0')}-${(month + 1).toString().padStart(2, '0')}-$year"
                dateEditText.setText(dateString)
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        addButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val date = dateEditText.text.toString()  // Convert this String to Date if your Task model expects a Date object



            dialog.dismiss()
        }

        dialog.show()
    }

    private fun loadTasks() {

        val sampleTasks = listOf(
            Task(1, "Sample Task 1", "Description of Task 1", 1, Date()),
            Task(2, "Sample Task 2", "Description of Task 2", 2, Date())
        )
        adapter = TaskAdapter(sampleTasks)
        recyclerView.adapter = adapter
    }
}
