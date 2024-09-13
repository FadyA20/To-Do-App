package com.example.to_dolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.to_dolist.databinding.FragmentPutInListBinding


class PutInList : Fragment() {
    lateinit var binding: FragmentPutInListBinding
    private lateinit var adapter: MyAdapter
    private lateinit var taskViewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPutInListBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskViewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
        binding.btn1.setOnClickListener {
        val newtaskname = binding.editText1.text.toString()
        val newtaskdesc = binding.editText2.text.toString()
        val newtaskdate = binding.Date.text.toString()
        if (newtaskname.isNotEmpty() && newtaskdesc.isNotEmpty() && newtaskdate.isNotEmpty()) {
            val newtask = TaskDataClass(taskName = newtaskname, taskDescription = newtaskdesc, taskDate = newtaskdate)
            taskViewModel.addTask(newtask)
            findNavController().navigate(R.id.action_putInList3_to_list2)
        }
        }
    }
}