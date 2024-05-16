package com.vineet.tasks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator
import com.vineet.tasks.databinding.ActivityMainBinding
import com.vineet.tasks.databinding.DialogAddTaskBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pager.adapter = PagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = "Tasks"
        }.attach()

        binding.fab.setOnClickListener() {
            val dialogBinding = DialogAddTaskBinding.inflate(layoutInflater)
            MaterialAlertDialogBuilder(this).apply {
                setTitle("Add new task")
                setView(dialogBinding.root)
                setPositiveButton("Save") { _, _ ->
                    Toast.makeText(
                        context,
                        "Your task is: ${dialogBinding.textInputTasks.text}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                setNegativeButton("Cancel", null)
                show()
            }
        }

    }

    inner class PagerAdapter(activityFragment: FragmentActivity) :
        FragmentStateAdapter(activityFragment) {
        override fun getItemCount() = 1

        override fun createFragment(position: Int): Fragment {
            return TasksFragment()
        }

    }
}