package com.example.to_dolist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_dolist.databinding.FragmentListBinding

class ListFragment : Fragment(), MyAdapter.OnTaskActionListener {
    lateinit var binding: FragmentListBinding
    lateinit var adapter: MyAdapter
    private lateinit var taskViewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("ListFragment", "onViewCreated called")
//        val menuHost: MenuHost = requireActivity()
//
//        menuHost.addMenuProvider(object : MenuProvider {
//            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
//                Log.d("ListFragment", "MenuProvider: onCreateMenu called")
//                menuInflater.inflate(R.menu.card_menu, menu)
//            }
//            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
//                Log.d("ListFragment", "MenuProvider: onMenuItemSelected called")
//                return when (menuItem.itemId) {
//                    R.id.action_edit -> {
//                        Log.d("ListFragment", "Edit menu item selected")
//                        findNavController().navigate(R.id.action_list2_to_putInList3)
//                        true
//                    }
//                    R.id.action_delete -> {
//                        Log.d("ListFragment", "Unknown menu item selected")
//                        Toast.makeText(requireContext(), "Delete action selected", Toast.LENGTH_SHORT).show()
//                        true
//                    }
//                    else -> false
//                }
//            }
//        },viewLifecycleOwner)
        taskViewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
        adapter = MyAdapter(mutableListOf(),this)
        binding.recyclerinmain.layoutManager = LinearLayoutManager(context)
        binding.recyclerinmain.adapter= adapter
        taskViewModel.allTasks.observe(viewLifecycleOwner, Observer { tasks ->
            adapter.updateTasks(tasks)
        })
        binding.fab.setOnClickListener{
            findNavController().navigate(R.id.action_list2_to_putInList3)
        }
    }

    override fun onEditClick(task: TaskDataClass) {
        val builder = AlertDialog.Builder(requireContext())
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.alertdialouge, null)
        builder.setView(dialogView)
        val taskNameEditText = dialogView.findViewById<EditText>(R.id.edit_task_name)
        val taskDescriptionEditText = dialogView.findViewById<EditText>(R.id.edit_task_description)
        val taskDateEditText = dialogView.findViewById<EditText>(R.id.edit_task_date)
        taskNameEditText.setText(task.taskName)
        taskDescriptionEditText.setText(task.taskDescription)
        taskDateEditText.setText(task.taskDate)
        builder.setTitle("Edit Task")
        builder.setPositiveButton("Save") { dialog, _ ->
            // Update the task with new values
            val updatedTask = task.copy(
                taskName = taskNameEditText.text.toString(),
                taskDescription = taskDescriptionEditText.text.toString(),
                taskDate = taskDateEditText.text.toString()
            )
            taskViewModel.updatetask(updatedTask)
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    override fun onDeleteClick(task: TaskDataClass) {
        taskViewModel.deleteTask(task)
    }
}

