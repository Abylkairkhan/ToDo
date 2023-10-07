package com.example.todo.domain

import android.graphics.drawable.AnimatedVectorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.ItemCardBinding

class TaskAdapter(
    val listener: Listener
): RecyclerView.Adapter<TaskAdapter.TaskHolder>() {

    private var taskList = ArrayList<Task>()

    class TaskHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding: ItemCardBinding = ItemCardBinding.bind(item)
        fun bind(task: Task, listener: Listener) = with(binding) {
            trashIcon.setOnClickListener {
                val avd = trashIcon.drawable as  AnimatedVectorDrawable
                avd.start()
            }
            checkbox.isChecked = task.done
            titleTextview.text = task.title
            itemView.setOnClickListener {
                listener.onClick(task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        return TaskHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
            )
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind(taskList[position], listener)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun addTask(task: Task) {
        taskList.add(task)
        notifyDataSetChanged()
    }

    fun setTask(_taskList: ArrayList<Task>) {
        taskList = _taskList
        notifyDataSetChanged()
    }

    interface Listener {
        fun onClick(task: Task)
    }

}