package com.vineet.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vineet.tasks.databinding.FragmentTasksBinding
import kotlin.concurrent.thread

class TasksFragment : Fragment() {
    private lateinit var binding: FragmentTasksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTasksBinding.inflate(layoutInflater, container, false)
        return binding.root

        UpdateUIWithTasks()

    }

    fun UpdateUIWithTasks() {
        val database = GetItDoneDatabase.GetDatabase(requireContext())
        val dao = database.GetTaskDao()
        thread {
            val taskList: List<Task> = dao.GetAllDetails()
            requireActivity().runOnUiThread {
                val recyclerViewAdapter = RecyclerViewTasksAdapter(taskList)
                binding.recyclerView.adapter = recyclerViewAdapter
                binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            }

        }
    }
}