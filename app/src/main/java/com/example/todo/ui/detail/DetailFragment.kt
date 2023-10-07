package com.example.todo.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todo.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private val viewModel by activityViewModel<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        if (args.save) saveTask() else deleteTask()
        return binding.root
    }

    private fun saveTask() {
        with(binding) {
            taskEt.visibility = View.VISIBLE
            descEt.visibility = View.VISIBLE
            saveOrDelete.text = "save"
            saveOrDelete.setOnClickListener {
                viewModel.saveTask(taskEt.text.toString(), descEt.text.toString())
                findNavController().popBackStack()
            }
        }
    }

    private fun deleteTask() {
        with(binding) {
            taskTv.visibility = View.VISIBLE
            taskTv.text = args.tsk?.title ?: "Error"
            descTv.visibility = View.VISIBLE
            descTv.text = args.tsk?.description ?: "Error"
            saveOrDelete.text = "delete"
            saveOrDelete.setOnClickListener {
                viewModel.deleteTask(args.tsk!!.id)
                findNavController().popBackStack()
            }
        }
    }
}