package com.example.todo.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.R
import com.example.todo.data.TaskRepository
import com.example.todo.databinding.FragmentMainBinding
import com.example.todo.domain.Task
import com.example.todo.domain.TaskAdapter
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class MainFragment : Fragment(), TaskAdapter.Listener {

    private lateinit var binding: FragmentMainBinding
    private val viewModel by activityViewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        viewModel.execute()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TaskAdapter(this)
        adapter.setTask(viewModel.loadData() as ArrayList<Task>)
        binding.RecView.layoutManager = LinearLayoutManager(context)
        binding.RecView.adapter = adapter
        addTask()
    }

    private fun addTask() {
        binding.addButton.setOnClickListener {
            val action = MainFragmentDirections.actionMainToDetail(save = true)
            view?.findNavController()?.navigate(action)
        }
    }

    override fun onClick(task: Task) {
        val action = MainFragmentDirections.actionMainToDetail(save = false, tsk = task)
        view?.findNavController()?.navigate(action)
    }
}