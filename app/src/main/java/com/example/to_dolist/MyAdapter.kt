package com.example.to_dolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dolist.databinding.ItemLayoutBinding

class MyAdapter(var taskDataClasses: MutableList<TaskDataClass>,    private val listener: OnTaskActionListener
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    interface OnTaskActionListener {
        fun onEditClick(task: TaskDataClass)
        fun onDeleteClick(task: TaskDataClass)
    }
    class MyViewHolder(val binding: ItemLayoutBinding):RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val Task = taskDataClasses[position]
        holder.binding.text1.text= Task.taskName
        holder.binding.text2.text= Task.taskDescription
        holder.binding.date.text= Task.taskDate
        holder.binding.menuoption.setOnClickListener { view ->
            showPopupMenu(view, Task)
        }
    }
    private fun showPopupMenu(view: View, task: TaskDataClass) {
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.menuInflater.inflate(R.menu.card_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_edit -> {
                    listener.onEditClick(task)
                    true
                }
                R.id.action_delete -> {
                    listener.onDeleteClick(task)
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
    override fun getItemCount()= taskDataClasses.size
    fun addItem(tasks1 : TaskDataClass) {
        taskDataClasses.add(tasks1)
        notifyItemInserted(taskDataClasses.size - 1)
    }
    fun updateTasks(newTasks: List<TaskDataClass>) {
        taskDataClasses = newTasks.toMutableList()
        notifyDataSetChanged()
    }
}